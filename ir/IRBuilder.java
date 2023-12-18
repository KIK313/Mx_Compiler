package ir;
import ast.*;
import irnode.*;
import utl.Scope;
import utl.Type;
import irnode.*;

import java.util.HashMap;
import java.util.Objects;

public class IRBuilder implements ASTVisitor {
    private HashMap<String, Integer> classNameToId = new HashMap<>();
    public irBlock curBlk;
    public irProgramNode pr;
    public irScope currentScope = new irScope(null);
    public irFuncNode curIrFuncNode;
    public irMainNode mainNode;
    public String curClass;
    public int blkId = 0;
    public Type intTp = new Type("int");
    public Type boolTp = new Type("bool");
    public Type voidTp = new Type("void");
    public void glbVarInit(Type tp, varinitNode nd, irGlobalVar def, irBlock ins) {
        if (nd.nd == null) {
            return;
        }

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
        for (int i = 0; i < it.lis.size(); i++) {
            ProgramComp u = it.lis.get(i);
            if (u instanceof class_defNode) {
                irClassDef cd = new irClassDef("class." + ((class_defNode) u).name);
                for (int j = 0; j < ((class_defNode) u).varLis.size(); ++j) {
                    variable_defNode vd = ((class_defNode) u).varLis.get(j);
                    irType tp;
                    if (vd.tp.equal(new Type("int") ) || vd.tp.equal(new Type("bool"))) {
                        tp = irType.I32;
                    } else tp = irType.PTR;
                    for (int k = 0; k < vd.lis.size(); ++k) {
                        cd.appendType(tp);
                        classNameToId.put(((class_defNode) u).name + "." + vd.lis.get(i).name ,k);
                    }
                }
                pr.classLis.add(cd);
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
        // no class taken into consideration
        currentScope = new irScope(currentScope);
        irFuncNode fn = new irFuncNode(it.name);
        pr.funcLis.add(fn);
        curIrFuncNode = fn;
        fn.returnType = trans2Irtype(it.tp);
        curBlk = new irBlock("entry");
        fn.blockLis.add(curBlk);
        if (it.ls != null) it.ls.accept(this);
        irBlock retBlk = new irBlock("retBlk");
        currentScope.retBlk = retBlk;
        if (curIrFuncNode.returnType == irType.VOID) retBlk.appendIns(new retNode());
        else {
            irRegister retVal = new irRegister("", curIrFuncNode.returnType);
            irRegister retPtr = new irRegister("", irType.PTR);
            retBlk.appendIns(new irLoadNode(retVal, curIrFuncNode.returnType, retPtr));
            retBlk.appendIns(new retNode(curIrFuncNode.returnType, retVal));
            currentScope.retPtr = retPtr;
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
        u.appendBlock(curBlk);
        irBlock retBlk = new irBlock("retBlk");
        irRegister retVal = new irRegister("", irType.I32);
        irRegister retPtr = new irRegister("", irType.PTR);
        currentScope.retBlk = retBlk;
        currentScope.retPtr = retPtr;
        retBlk.appendIns(new irLoadNode(retVal, irType.I32, retPtr));
        retBlk.appendIns(new retNode(irType.I32, retVal));
        curBlk.appendIns(new storeNode(new irConstInt(1), retPtr));
        curBlk.terminalIns = new irBrNode(retBlk);
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

    }
    @Override
    public void visit(variable_defNode it) {

    }
    @Override
    public void visit(class_constructorNode it) {

    }
    @Override
    public void visit(exp_statNode it) {

    }
    @Override
    public void visit(def_statNode it) {

    }
    @Override
    public void visit(continue_statNode it) {
        irBlock toBlk = currentScope.findContinueBlk();
        curBlk.appendIns(new irBrNode(toBlk));
    }
    @Override
    public void visit(break_statNode it) {
        irBlock toBlk = currentScope.findBreakBlk();
        curBlk.appendIns(new irBrNode(toBlk));
    }
    @Override
    public void visit(return_statNode it) {

    }
    @Override
    public void visit(deffor_statNode it) {
        currentScope = new irScope(currentScope);
        if (it.nd!= null) {
            it.nd.accept(this);
        }
        irBlock for_body = new irBlock("if_body" + Integer.toString(++blkId));
        irBlock for_con = new irBlock("if_con" + Integer.toString(++blkId));
        irBlock for_stp = new irBlock("if_stp" + Integer.toString(++blkId));

        if (it.con != null) {
        //    irBlock
        } else {

        }


        currentScope = currentScope.parentScope;
    }
    @Override
    public void visit(expfor_statNode it) {
        currentScope = new irScope(currentScope);
        if (it.stat != null) {
            it.stat.accept(this);
        }
      //  if
    }
    @Override
    public void visit(if_statNode it) {
        // it.con.accept(this); it needs to be fixed;
        irBlock bk = curBlk;
        irBlock trBlk = new irBlock("if_true" + Integer.toString(++blkId));
        irBlock netBlk = new irBlock("if_next" + Integer.toString(++blkId));
        netBlk.terminalIns = curBlk.terminalIns;
        trBlk.terminalIns = new irBrNode(netBlk);
        if (it.elsest == null) {
            curBlk.terminalIns = new irConBrNode(it.con.irVal, trBlk, netBlk);
            curBlk = trBlk;
            currentScope = new irScope(currentScope);
            it.thenst.accept(this);
            currentScope = currentScope.parentScope;
        } else {
            irBlock faBlk = new irBlock("if_false" + Integer.toString(++blkId));
            faBlk.terminalIns = new irBrNode(netBlk);
            curBlk.terminalIns = new irConBrNode(it.con.irVal, trBlk, faBlk);
            curBlk = trBlk;
            currentScope = new irScope(currentScope);
            it.thenst.accept(this);
            currentScope = currentScope.parentScope;
            curBlk = faBlk;
            currentScope = new irScope(currentScope);
            it.elsest.accept(this);
            currentScope = currentScope.parentScope;
        }
        curBlk = netBlk;
    }
    @Override
    public void visit(while_statNode it) {
        irBlock netBlk = new irBlock("while_net" + Integer.toString(++blkId));
        netBlk.terminalIns = curBlk.terminalIns;
        irBlock conBlk = new irBlock("while_cond" + Integer.toString(++blkId));
        curBlk.terminalIns = new irBrNode(conBlk);
        curBlk = conBlk;
        it.con.accept(this);
        curBlk = new irBlock("while_body" + Integer.toString(++blkId));
        curBlk.terminalIns = new irBrNode(conBlk);
        currentScope = new irScope(currentScope);
        currentScope.continue2Blk = conBlk;
        currentScope.break2Blk = netBlk;
        it.nd.accept(this);
        currentScope = currentScope.parentScope;
        curBlk = netBlk;
    }
    @Override
    public void visit(paralistNode it) {
        for (int i = 0 ;i < it.name.size(); i++) {
            irRegister reg = new irRegister("", trans2Irtype(it.tpLis.get(i)));
            irRegister pr = new irRegister("", irType.PTR);
            curBlk.appendIns(new allocNode(pr, reg.tp));
            curBlk.appendIns(new storeNode(reg, pr));
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

    }
    @Override
    public void visit(literal_exprNode it) {

    }
    @Override
    public void visit(class_call_exprNode it) {

    }
    @Override
    public void visit(class_exprNode it) {

    }
    @Override
    public void visit(array_exprNode it) {

    }
    @Override
    public void visit(func_exprNode it) {

    }
    @Override
    public void visit(paren_exprNode it) {

    }
    @Override
    public void visit(typeNode it) {

    }
    @Override
    public void visit(rightAddExprNode it) {

    }
    @Override
    public void visit(leftAddExprNode it) {

    }
    @Override
    public void visit(not_exprNode it) {

    }
    @Override
    public void visit(new_exprNode it) {

    }
    @Override
    public void visit(biexprNode it) {

    }
    @Override
    public void visit(tri_exprNode it) {

    }
    @Override
    public void visit(assign_exprNode it) {

    }
    @Override
    public void visit(new_typeNode it) {

    }
    @Override
    public void visit(func_call_listNode it) {

    }

}
