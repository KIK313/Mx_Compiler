package ir;
import ast.*;
import irnode.*;
import utl.Type;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class IRBuilder implements ASTVisitor {
    private HashMap<String, Integer> classNameToId = new HashMap<>();
    public irBlock curBlk;
    public irProgramNode pr;
    public irScope currentScope = new irScope(null);
    public irFuncNode curIrFuncNode;
    public Set<String> curClassMethod;
    public HashMap<String, irRegister> irString2Reg = new HashMap<>();
    public irBlock irFuncRetBlk;
    public irRegister irFuncRetPtr;
    public irRegister irThisPtr = null;
    public HashMap<String, irClassNode> className2ClassNode = new HashMap<>();
    public irMainNode mainNode = null;
    public irClassNode curClass;
    public int blkId = 0;
    public Type intTp = new Type("int");
    public Type boolTp = new Type("bool");
    public Type voidTp = new Type("void");
    public Type stringTp = new Type("string");
    public void glbVarInit(Type tp, varinitNode nd, irGlobalVar def, irBlock ins) {
        if (nd.nd == null) {
            return;
        }

    }
    public void appendBlk(irBlock blk) {
        if (curIrFuncNode != null) curIrFuncNode.appendBlock(blk);
        else mainNode.appendBlock(blk);
    }
    public irType trans2Irtype(Type tp) {
        if (tp.equal(intTp)) return irType.I32;
        if (tp.equal(boolTp)) return irType.I1;
        if (tp.equal(voidTp)) return irType.VOID;
        return irType.PTR;
    }
    @Override
    public void visit(ProgramNode it) {
        pr = new irProgramNode();
        irBlock entryBlk = new irBlock("entry");
        for (var u : it.lis) {
            if (u instanceof class_defNode) {
                irClassNode o = new irClassNode(((class_defNode) u).name);
                int cnt = 0;
                for (var v: ((class_defNode) u).varLis) {
                    irType tp;
                    if (v.tp.equal(intTp)) tp = irType.I32;
                        else if (v.tp.equal(boolTp)) tp = irType.I1;
                            else tp = irType.PTR;
                    for (int k = 0; k < v.lis.size(); ++k) {
                        o.tp.add(tp);
                        o.name2Id.put(v.lis.get(k).name , cnt);
                        cnt++;
                    }
                }
                pr.classLis.add(o);
            }
        }
        for (int i = 0; i < it.lis.size(); i++) {
            ProgramComp u = it.lis.get(i);
            if (u instanceof variable_defNode) {
                Type tp = ((variable_defNode) u).tp;
                irType g;
                if (tp.equal(new Type("bool"))) g = irType.I1;
                else if (tp.equal(new Type("int"))) g = irType.I32;
                else g = irType.PTR;
                for (int j = 0; j < ((variable_defNode) u).lis.size(); ++j) {
                    irGlobalVar o = new irGlobalVar(g, ((variable_defNode) u).lis.get(i).name);
                    glbVarInit(tp, ((variable_defNode) u).lis.get(j), o, entryBlk);
                    pr.glbVarLis.add(o);
                }
            }
        }

        // build function ir
        for (int i = 0; i < it.lis.size(); i++) {
            ProgramComp u = it.lis.get(i);
            if (u instanceof func_defNode) {
//                irFuncNode fn = new irFuncNode(((func_defNode) u).name);
//                curIrFuncNode = fn;
//                if (((func_defNode) u).tp.equal(new Type("void"))) fn.returnType = irType.VOID;
//                    else if (((func_defNode) u).tp.equal(new Type("int"))) fn.returnType = irType.I32;
//                        else if (((func_defNode) u).tp.equal(new Type("bool"))) fn.returnType = irType.I1;
//                            else fn.returnType = irType.PTR;
//                visit((func_defNode)u);
//                pr.funcLis.add(fn);
                u.accept(this);
            }
        }

        // build main function ir
        for (int i = 0; i < it.lis.size(); i++) {
            ProgramComp u = it.lis.get(i);
            if (u instanceof main_defNode) {
                curBlk = entryBlk;
                u.accept(this);
            }
        }
    }
    @Override
    public void visit(func_defNode it) {
        irFuncRetBlk = null; irFuncRetPtr = null;
        mainNode = null;
        // no class taken into consideration
        currentScope = new irScope(currentScope);
        irFuncNode fn = new irFuncNode(it.name);
        pr.funcLis.add(fn);
        curIrFuncNode = fn;
        fn.returnType = trans2Irtype(it.tp);
        curBlk = new irBlock("entry"); fn.appendBlock(curBlk);
        if (it.ls != null) it.ls.accept(this);
        irBlock retBlk = new irBlock("retBlk");
        irFuncRetBlk = retBlk;
        if (curIrFuncNode.returnType == irType.VOID) retBlk.appendIns(new retNode());
        else {
            irRegister retVal = new irRegister("", curIrFuncNode.returnType);
            irRegister retPtr = new irRegister("", irType.PTR);
            irFuncRetPtr = retPtr;
            retBlk.appendIns(new irLoadNode(retVal, curIrFuncNode.returnType, retPtr));
            retBlk.appendIns(new retNode(curIrFuncNode.returnType, retVal));
        }
        it.nd.accept(this);
        fn.appendBlock(retBlk);
        currentScope = currentScope.parentScope;
    }
    @Override
    public void visit(main_defNode it) {
        currentScope = new irScope(currentScope);
        curIrFuncNode = null; curClass = null;
        irMainNode u = new irMainNode();
        pr.mainNd = u;
        mainNode = u;
        u.appendBlock(curBlk);
        irBlock retBlk = new irBlock("retBlk");
        irRegister retVal = new irRegister("", irType.I32);
        irRegister retPtr = new irRegister("", irType.PTR);
        irFuncRetBlk = retBlk;
        irFuncRetPtr = retPtr;
        retBlk.appendIns(new irLoadNode(retVal, irType.I32, retPtr));
        retBlk.appendIns(new retNode(irType.I32, retVal));
        curBlk.appendIns(new irStoreIns(irType.I32, new irConstInt(0), retPtr)); // i don't know what the ret val should be
        curBlk.terminalIns = new irBrIns(retBlk);
        it.nd.accept(this);
        u.appendBlock(retBlk);
    }
    @Override
    public void visit(suiteNode it) {
        currentScope = new irScope(currentScope);
        for (statementNode u : it.lis) {
            u.accept(this);
        }
        currentScope = currentScope.parentScope;
    }
    @Override
    public void visit(class_defNode it) {
        curClass = new irClassNode(it.name);
        className2ClassNode.put(it.name, curClass);
        for (int i = 0; i < it.varLis.size(); i++) {
            variable_defNode u = it.varLis.get(i);
            u.accept(this);
        }


        curClass = null;
    }
    @Override
    public void visit(variable_defNode it) {

    }
    @Override
    public void visit(class_constructorNode it) {

    }
    @Override
    public void visit(exp_statNode it) {
        it.nd.accept(this);
    }
    @Override
    public void visit(def_statNode it) {

    }
    @Override
    public void visit(continue_statNode it) {
        irBlock toBlk = currentScope.findContinueBlk();
        curBlk.appendIns(new irBrIns(toBlk));
    }
    @Override
    public void visit(break_statNode it) {
        irBlock toBlk = currentScope.findBreakBlk();
        curBlk.appendIns(new irBrIns(toBlk));
    }
    @Override
    public void visit(return_statNode it) {
        if (it.nd != null) {
            it.nd.accept(this);
            curBlk.appendIns(new irStoreIns(curIrFuncNode.returnType, it.nd.irVal, irFuncRetPtr));
        }
        curBlk.appendIns(new irBrIns(irFuncRetBlk));
    }
    @Override
    public void visit(deffor_statNode it) {
        currentScope = new irScope(currentScope);
        if (it.nd != null) {
            it.nd.accept(this);
        }
        int Id = ++blkId;
        irBlock for_body = new irBlock("if_body" + Integer.toString(Id));
        irBlock for_con = new irBlock("if_con" + Integer.toString(Id));
        irBlock for_stp = new irBlock("if_stp" + Integer.toString(Id));
        irBlock for_next = new irBlock("if_next" + Integer.toString(Id));
        for_next.terminalIns = curBlk.terminalIns;
        if (it.con != null) {
            curBlk.appendIns(new irBrIns(for_con));
            curBlk = for_con; appendBlk(curBlk);
            it.accept(this);
            curBlk.appendIns(new irConBrIns(it.con.irVal, for_body, for_next));
        } else {
            curBlk.appendIns(new irBrIns(for_con));
            for_con.terminalIns = new irBrIns(for_body); appendBlk(for_con);
        }
        curBlk = for_stp; appendBlk(curBlk);
        curBlk.terminalIns = new irBrIns(for_con);
        if (it.stp != null) it.stp.accept(this);
        curBlk = for_body; appendBlk(curBlk);
        curBlk.terminalIns = new irBrIns(for_stp);
        currentScope = new irScope(currentScope);
        currentScope.break2Blk = for_next;
        currentScope.continue2Blk = for_stp;
        if (it.stat != null) it.stat.accept(this);
        currentScope = currentScope.parentScope;
        curBlk = for_next; appendBlk(curBlk);
        currentScope = currentScope.parentScope;
    }
    @Override
    public void visit(expfor_statNode it) {
        currentScope = new irScope(currentScope);
        if (it.nd != null) {
            it.nd.accept(this);
        }
        int Id = ++blkId;
        irBlock for_body = new irBlock("if_body" + Integer.toString(Id));
        irBlock for_con = new irBlock("if_con" + Integer.toString(Id));
        irBlock for_stp = new irBlock("if_stp" + Integer.toString(Id));
        irBlock for_next = new irBlock("if_next" + Integer.toString(Id));
        for_next.terminalIns = curBlk.terminalIns;
        if (it.con != null) {
            curBlk.appendIns(new irBrIns(for_con));
            curBlk = for_con; appendBlk(curBlk);
            it.accept(this);
            curBlk.appendIns(new irConBrIns(it.con.irVal, for_body, for_next));
        } else {
            curBlk.appendIns(new irBrIns(for_con));
            for_con.terminalIns = new irBrIns(for_body); appendBlk(for_con);
        }
        curBlk = for_stp; appendBlk(curBlk);
        curBlk.terminalIns = new irBrIns(for_con);
        if (it.stp != null) it.stp.accept(this);
        curBlk = for_body; appendBlk(curBlk);
        curBlk.terminalIns = new irBrIns(for_stp);
        currentScope = new irScope(currentScope);
        currentScope.break2Blk = for_next;
        currentScope.continue2Blk = for_stp;
        if (it.stat != null) it.stat.accept(this);
        currentScope = currentScope.parentScope;
        curBlk = for_next; appendBlk(curBlk);
        currentScope = currentScope.parentScope;
    }
    @Override
    public void visit(if_statNode it) {
        // it.con.accept(this); it needs to be fixed;
        irBlock bk = curBlk;
        int Id = ++blkId;
        irBlock conBlk = new irBlock("if_con" + Integer.toString(Id));
        irBlock trBlk = new irBlock("if_true" + Integer.toString(Id));
        irBlock netBlk = new irBlock("if_next" + Integer.toString(Id));
        netBlk.terminalIns = curBlk.terminalIns;
        trBlk.terminalIns = new irBrIns(netBlk);
        curBlk.terminalIns = new irBrIns(conBlk);
        curBlk = conBlk; appendBlk(conBlk);
        it.con.accept(this);
        if (it.elsest == null) {
            curBlk.terminalIns = new irConBrIns(it.con.irVal, trBlk, netBlk);
            curBlk = trBlk; appendBlk(trBlk);
            currentScope = new irScope(currentScope);
            it.thenst.accept(this);
            currentScope = currentScope.parentScope;
        } else {
            irBlock faBlk = new irBlock("if_false" + Integer.toString(Id));
            faBlk.terminalIns = new irBrIns(netBlk);
            curBlk.terminalIns = new irConBrIns(it.con.irVal, trBlk, faBlk);
            curBlk = trBlk; appendBlk(trBlk);
            currentScope = new irScope(currentScope);
            it.thenst.accept(this);
            currentScope = currentScope.parentScope;
            curBlk = faBlk; appendBlk(faBlk);
            currentScope = new irScope(currentScope);
            it.elsest.accept(this);
            currentScope = currentScope.parentScope;
        }
        curBlk = netBlk; appendBlk(netBlk);
    }
    @Override
    public void visit(while_statNode it) {
        int Id = ++blkId;
        irBlock conBlk = new irBlock("while_cond" + Integer.toString(Id));
        irBlock bodyBlk = new irBlock("while_body" + Integer.toString(Id));
        irBlock netBlk = new irBlock("while_net" + Integer.toString(Id));
        netBlk.terminalIns = curBlk.terminalIns;
        curBlk.terminalIns = new irBrIns(conBlk);
        curBlk = conBlk; appendBlk(conBlk);
        it.con.accept(this);
        curBlk.appendIns(new irConBrIns(it.con.irVal, bodyBlk, netBlk));
        curBlk = bodyBlk; appendBlk(bodyBlk);
        curBlk.terminalIns = new irBrIns(conBlk);
        currentScope = new irScope(currentScope);
        currentScope.continue2Blk = conBlk;
        currentScope.break2Blk = netBlk;
        it.nd.accept(this);
        currentScope = currentScope.parentScope;
        curBlk = netBlk; appendBlk(netBlk);
    }
    @Override
    public void visit(paralistNode it) {
        for (int i = 0 ;i < it.name.size(); i++) {
            irRegister reg = new irRegister("", trans2Irtype(it.tpLis.get(i)));
            irRegister pr = new irRegister("", irType.PTR);
            curBlk.appendIns(new irAllocIns(pr, reg.tp));
            curBlk.appendIns(new irStoreIns(reg.tp, reg, pr));
            currentScope.Name2Reg.put(it.name.get(i), pr);
        }
    }
    @Override
    public void visit(empty_statNode it) {} // done
    @Override
    public void visit(varinitNode it) {

    }
    @Override
    public void visit(iden_exprNode it) {
        irRegister o = currentScope.findReg(it.name);
        it.irPtr = o;
        irRegister oo = new irRegister("", trans2Irtype(it.nodeType));
        it.irVal = oo;
        curBlk.appendIns(new irLoadNode(oo, oo.tp, o));
    }
    @Override
    public void visit(literal_exprNode it) {
        if (it.s.equals("true")) {
            it.irVal = new irConstBool(true);
        } else if (it.s.equals("false")) {
            it.irVal = new irConstBool(false);
        } else if (it.s.equals("this")) {
            irRegister o = new irRegister("ths", irType.PTR);
            it.irVal = o;
            curBlk.appendIns(new irLoadNode(o, irType.PTR, irThisPtr));
        } else if (it.s.equals("null")) {
            it.irVal = new irConstNull();
        } else if (it.s.charAt(0) == '"') {
            if (irString2Reg.containsKey(it.s)) {
                it.irVal = irString2Reg.get(it.s);
            } else {
                irGlobalStringConst o = new irGlobalStringConst(it.s);
                it.irVal = o.reg;
                irString2Reg.put(it.s, o.reg);
                pr.strLis.add(o);
            }
        } else {
            it.irVal = new irConstInt(Integer.parseInt(it.s));
        }
    }
    @Override
    public void visit(class_call_exprNode it) {

    }
    @Override
    public void visit(class_exprNode it) {
        it.nd.accept(this);
//        classNameToId
//        it.name;
    }
    @Override
    public void visit(array_exprNode it) {
        it.nd.accept(this);
        it.id.accept(this);
        irRegister o = new irRegister("", irType.PTR);
        it.irPtr = o;
        curBlk.appendIns(new irGetEleIns(o, (irRegister) it.nd.irVal, it.id.irVal));
        irRegister oo = new irRegister("", trans2Irtype(it.nodeType));
        curBlk.appendIns(new irLoadNode(oo, oo.tp, o));
        it.irVal = oo;
    }
    @Override
    public void visit(func_exprNode it) {
        boolean is_method = false;
        if (curClass != null) {
            if (curClassMethod.contains(it.funcname)) {
                is_method = true;
            }
        }
        String name;
        irRegister o = null;
        if (is_method) {
            name = curClass.name + "." + it.funcname;
        }
        else {
            if (it.nodeType.equal(stringTp)) {
                name = "string." + it.funcname;
            } else name = it.funcname;
        }
        irType tp = trans2Irtype(it.nodeType);
        if (tp != irType.VOID) {
            o = new irRegister("fc", tp);
            it.irVal = o;
        }
        irCallFuncIns call = new irCallFuncIns(o, tp, name);
        if (is_method) {
            irRegister oo = new irRegister("tt", irType.PTR);
            curBlk.appendIns(new irLoadNode(oo, irType.PTR, irThisPtr));
            call.paraLis.add(oo);
        }
        if (it.nd != null) {
            for (var u : it.nd.lis) {
                u.accept(this);
                call.paraLis.add(u.irVal);
            }
        }
        curBlk.appendIns(call);
    }
    @Override
    public void visit(paren_exprNode it) {
        it.son.accept(this);
        it.irPtr = it.son.irPtr;
        it.irVal = it.son.irVal;
    }
    @Override
    public void visit(typeNode it) {} // done??
    @Override
    public void visit(rightAddExprNode it) {
        it.nd.accept(this);
        irRegister o = new irRegister("", it.nd.irVal.tp);
        it.irVal = o;
        int val;
        if (it.op.equals("++")) val = 1;
        else val = -1;
        curBlk.appendIns(new irBiExprIns(o, "add", irType.I32, it.nd.irVal, new irConstInt(val)));
    }
    @Override
    public void visit(leftAddExprNode it) {
        it.nd.accept(this);
        irRegister o = new irRegister("", it.nd.irVal.tp);
        it.irVal = o;
        it.irPtr = it.nd.irPtr;
        int val;
        if (it.op.equals("++")) val = 1;
        else val = -1;
        curBlk.appendIns(new irBiExprIns(o, "add", irType.I32, it.nd.irVal, new irConstInt(val)));
        curBlk.appendIns(new irStoreIns(irType.I32, o, it.irPtr));
    }
    @Override
    public void visit(not_exprNode it) {
        it.nd.accept(this);
        if (it.op.equals("!")) {
            irRegister o = new irRegister("", irType.I1);
            it.irVal = o;
            curBlk.appendIns(new irBiExprIns(o,"xor", irType.I1, it.nd.irVal, new irConstBool(true)));
        } else if(it.op.equals("-")) {
            irRegister o = new irRegister("", irType.I32);
            it.irVal = o;
            curBlk.appendIns(new irBiExprIns(o, "sub", irType.I32, new irConstInt(0), it.nd.irVal));
        } else { // ~
            irRegister o = new irRegister("", irType.I32);
            it.irVal = o;
            curBlk.appendIns(new irBiExprIns(o, "xor",irType.I32, it.nd.irVal, new irConstInt(-1)));
        }
    }
    @Override
    public void visit(new_exprNode it) {

    }
    @Override
    public void visit(biexprNode it) {
        if (it.op.equals("&&") || it.op.equals("||")) {
            it.ls.accept(this);
            int Id = ++blkId;
            irBlock fr = curBlk;
            irBlock bi_r = new irBlock("bi_rhs" + Integer.toString(Id));
            irBlock bi_net = new irBlock("bi_net" + Integer.toString(Id));
            bi_net.terminalIns = curBlk.terminalIns;
            //curBlk.appendIns(new irConBrNode());
            if (it.op.equals("&&")) {
                curBlk.appendIns(new irConBrIns(it.irVal, bi_r, bi_net));
            } else curBlk.appendIns(new irConBrIns(it.irVal, bi_net, bi_r));
            curBlk = bi_net; appendBlk(bi_net);
            bi_net.terminalIns = new irBrIns(bi_net);
            it.rs.accept(this);
            curBlk = bi_net; appendBlk(bi_net);
            irRegister o = new irRegister("bi", irType.I1);
            it.irVal = o;
            irPhiIns phi = new irPhiIns(irType.I1);
            curBlk.appendIns(phi);
            phi.blkLis.add(fr); phi.blkLis.add(bi_r);
            if (it.op.equals("&&")) {
                phi.valLis.add(new irConstBool(false));
                phi.valLis.add(it.rs.irVal);
            } else {
                phi.valLis.add(new irConstBool(true));
                phi.valLis.add(it.rs.irVal);
            }
            return;
        }
        it.ls.accept(this);
        it.rs.accept(this);
        irType tp = trans2Irtype(it.nodeType);
        if(tp == irType.I32) {
            irRegister o = new irRegister("int", irType.I32);
            it.irVal = o;
            if (it.op.equals("*")) curBlk.appendIns(new irBiExprIns(o, "mul", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals("/")) curBlk.appendIns(new irBiExprIns(o, "sdiv", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals("%")) curBlk.appendIns(new irBiExprIns(o, "srem", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals("+")) curBlk.appendIns(new irBiExprIns(o, "add", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals("-")) curBlk.appendIns(new irBiExprIns(o, "sub", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals("<<")) curBlk.appendIns(new irBiExprIns(o, "shl", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals(">>")) curBlk.appendIns(new irBiExprIns(o, "ashr", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals("&")) curBlk.appendIns(new irBiExprIns(o, "and", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals("^")) curBlk.appendIns(new irBiExprIns(o, "xor", irType.I32, it.ls.irVal, it.rs.irVal));
            else if (it.op.equals("|")) curBlk.appendIns(new irBiExprIns(o, "or", irType.I32, it.ls.irVal, it.rs.irVal));
        } else if (tp == irType.I1) {
            irRegister o = new irRegister("bool", irType.I1);
            it.irVal = o;
            if (it.ls.nodeType.equal(stringTp)) {
                String funcName = null;
                if (it.op.equals("<")) funcName = "string.lt";
                else if (it.op.equals(">")) funcName = "string.gt";
                else if (it.op.equals("<=")) funcName = "string.le";
                else if (it.op.equals(">=")) funcName = "string.ge";
                else if (it.op.equals("==")) funcName = "string.eq";
                else if (it.op.equals("!=")) funcName = "string.neq";
                irCallFuncIns call = new irCallFuncIns(o, irType.I1, funcName);
                curBlk.appendIns(call);
                call.paraLis.add(it.ls.irVal); call.paraLis.add(it.rs.irVal);
            } else if (it.ls.nodeType.equal(intTp) || it.ls.nodeType.equal(boolTp)) {
                String op = null;
                if (it.op.equals("<")) op = "slt";
                else if (it.op.equals(">")) op = "sgt";
                else if (it.op.equals("<=")) op = "sle";
                else if (it.op.equals(">=")) op = "sge";
                else if (it.op.equals("==")) op = "eq";
                else if (it.op.equals("!=")) op = "ne";
                irCmpIns cmp = new irCmpIns(o, op, it.ls.irVal.tp, it.ls.irVal, it.rs.irVal);
                curBlk.appendIns(cmp);
            } else {
                // ptr
                String op = null;
                if (it.op.equals("==")) op = "eq";
                else if (it.op.equals("!=")) op = "ne";
                irCmpIns cmp = new irCmpIns(o, op, irType.PTR, it.ls.irVal, it.rs.irVal);
                curBlk.appendIns(cmp);
            }
        } else {
            irRegister o = new irRegister("str", irType.PTR);
            it.irVal = o;
            irCallFuncIns call = new irCallFuncIns(o, irType.PTR, "string.add");
            call.paraLis.add(it.ls.irVal); call.paraLis.add(it.rs.irVal);
            it.irVal = o;
            curBlk.appendIns(call);
        }
    }
    @Override
    public void visit(tri_exprNode it) {
        it.con.accept(this);
        int Id = ++blkId;
        irBlock tr = new irBlock("tri_tr" + Integer.toString(Id));
        irBlock fa = new irBlock("tri_fa" + Integer.toString(Id));
        irBlock net = new irBlock("tri_net" + Integer.toString(Id));
        net.terminalIns = curBlk.terminalIns;
        curBlk.terminalIns = new irConBrIns(it.con.irVal, tr, fa);
        curBlk = tr; appendBlk(tr);
        tr.terminalIns = new irBrIns(net);
        it.ls.accept(this);
        curBlk = fa; appendBlk(fa);
        fa.terminalIns = new irBrIns(net);
        it.rs.accept(this);
        curBlk = net; appendBlk(net);
        irRegister o =new irRegister("tri", it.ls.irVal.tp);
        it.irVal = o;
        irPhiIns phi = new irPhiIns(it.ls.irVal.tp);
        curBlk.appendIns(phi);
        phi.valLis.add(it.ls.irVal); phi.valLis.add(it.rs.irVal);
        phi.blkLis.add(tr); phi.blkLis.add(fa);
    }
    @Override
    public void visit(assign_exprNode it) {
        it.rs.accept(this);
        it.ls.accept(this);
        curBlk.appendIns(new irStoreIns(it.rs.irVal.tp, it.rs.irVal, it.ls.irPtr));
        it.irPtr = it.ls.irPtr;
        it.irVal = it.rs.irVal;
    }
    @Override
    public void visit(new_typeNode it) {} // done
    @Override
    public void visit(func_call_listNode it) {} // done
}
