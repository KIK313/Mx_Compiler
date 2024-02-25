package asm;
import irnode.*;
import asmnode.*;

import java.util.HashMap;
import java.util.Stack;

public class asmBuilder implements irVisitor{
    public asmProgramNode pr = null;
    public asmBlock curAsmBlk = null;
    public asmFuncNode curAsmFunc = null;

    public HashMap<irRegister, asmReg> irReg2AsmReg;
    public HashMap<irBlock, asmBlock> irBlk2AsmBlk;
    public asmReg retPtr = null;
    asmReg newAsmReg() {
        asmReg u = new asmReg();
        u.idInFunc = curAsmFunc.regCnt++;
        u.spSiz = curAsmFunc.siz;
        return u;
    }
    @Override
    public void visit(irAllocIns it) {
        asmReg u = newAsmReg();
        irReg2AsmReg.put(it.reg, u);
        asmReg r = newAsmReg();
        asmAddiIns h = new asmAddiIns("addi", curAsmFunc.siz - (r.idInFunc + 1) * 4 , "t0", "sp");
        curAsmBlk.Lis.add(h);
        asmStoreIns o = new asmStoreIns(1);
        o.storeValId = "t0"; o.target = u;
        curAsmBlk.Lis.add(o);
    }
    void getDone(irEntity o, String d) {
        if (o instanceof irConst) {
            if (o instanceof irConstInt) {
                asmLiIns u = new asmLiIns();
                u.imm = ((irConstInt) o).val; u.rd = d;
                curAsmBlk.Lis.add(u);
            }
            if (o instanceof irConstBool) {
                asmLiIns u = new asmLiIns();
                if (((irConstBool) o).tf) u.imm = 1; else u.imm = 0;
                u.rd = d;
                curAsmBlk.Lis.add(u);
            }
            if (o instanceof irConstNull) {
                asmLiIns u = new asmLiIns();
                u.imm = 0; u.rd = d;
                curAsmBlk.Lis.add(u);
            }
        } else {
            if (o instanceof irRegister) {
                if (!((irRegister) o).isGlobal) {
                    asmReg u = irReg2AsmReg.get((irRegister) o);
                    asmLoadIns ld = new asmLoadIns();
                    ld.addrReg = u;
                    ld.desReg = d;
                    curAsmBlk.Lis.add(ld);
                } else {
                    //                  if (((irRegister) o).getName().charAt(0) == '.') {
                        curAsmBlk.Lis.add(new asmLaIns(((irRegister) o).getName(), d));
//                    } else {
//                        curAsmBlk.Lis.add(new asmLaIns(((irRegister) o).getName(), "t4"));
//                        asmLoadIns h = new asmLoadIns(); h.addr = "t4"; h.desReg = d;
//                        curAsmBlk.Lis.add(h);
//                    }
                }
            }
        }
    }
    @Override
    public void visit(irBiExprIns it) {
        asmReg u = newAsmReg();
        irReg2AsmReg.put(it.res, u);
        getDone(it.lhs, "t1"); getDone(it.rhs, "t2");
        String name = it.op;
        if (name.equals("sdiv")) name = "div";
        else if (name.equals("srem")) name = "rem";
        else if (name.equals("shl")) name = "sll";
        else if (name.equals("ashr")) name = "sra";
        asmBiExprIns o = new asmBiExprIns(name);
        o.id1 = 1; o.id2 = 2;
        o.resId = 0;
        curAsmBlk.Lis.add(o);
        asmStoreIns oo = new asmStoreIns(1);
        oo.target = u;
        oo.storeValId = "t0";
        curAsmBlk.Lis.add(oo);
    }
    @Override
    public void visit(irBrIns it) {
        if (irBlk2AsmBlk.get(it.desBlk) == null) {
            asmBlock u = new asmBlock();
            irBlk2AsmBlk.put(it.desBlk, u);
        }
        asmBlock des = irBlk2AsmBlk.get(it.desBlk);
        des.asmBlockName = it.desBlk.labelName;
        curAsmBlk.Lis.add(new asmJIns(des));
    }
    @Override
    public void visit(irBlock it) {
        for (var u : it.ls) u.accept(this);
        it.terminalIns.accept(this);
    }
    @Override
    public void visit(irCallFuncIns it) {
        for (int i = 0; i < it.paraLis.size(); i++) {
            if (i < 7) {
                getDone(it.paraLis.get(i), "a" + i);
//                asmLoadIns u = new asmLoadIns();
//                u.desReg = "a" + Integer.toString(i);
//                curAsmBlk.Lis.add(u);
            }
            getDone(it.paraLis.get(i), "t0");
            asmStoreIns u = new asmStoreIns(3); u.storeId = i; u.storeValId = "t0";
            curAsmBlk.Lis.add(u);
        }
        curAsmBlk.Lis.add(new asmCallFuncIns(it.funcName));
        if (it.tp != irType.VOID) {
            asmReg u = newAsmReg();
            irReg2AsmReg.put(it.res, u);
            asmStoreIns o = new asmStoreIns(1);
            o.storeValId = "a0"; o.target = u;
            curAsmBlk.Lis.add(o);
        }
    }
    @Override
    public void visit(irCmpIns it) {
        // eq ne slt sgt sle sge
        getDone(it.ls, "t0"); getDone(it.rs, "t1");
        asmReg u = newAsmReg();
        irReg2AsmReg.put(it.res, u);
        if (it.cmpOp.equals("slt")) {
            curAsmBlk.Lis.add(new asmCmpIns("slt", "t2", "t0", "t1"));
        }
        else if (it.cmpOp.equals("sgt")) {
            curAsmBlk.Lis.add(new asmCmpIns("slt", "t2", "t1", "t0"));
        }
        else if (it.cmpOp.equals("sle")) {
            curAsmBlk.Lis.add(new asmCmpIns("slt", "t2", "t1", "t0"));
            curAsmBlk.Lis.add(new asmSeqzIns("seqz", "t2", "t2"));
        }
        else if (it.cmpOp.equals("sge")) {
            curAsmBlk.Lis.add(new asmCmpIns("slt", "t2", "t0", "t1"));
            curAsmBlk.Lis.add(new asmSeqzIns("seqz", "t2", "t2"));
        } else if (it.cmpOp.equals("eq")) {
            asmBiExprIns o = new asmBiExprIns("sub"); o.id1 = 0; o.id2 = 1; o.resId = 2;
            curAsmBlk.Lis.add(o);
            curAsmBlk.Lis.add(new asmSeqzIns("seqz", "t2", "t2"));
        } else if (it.cmpOp.equals("ne")) {
            asmBiExprIns o = new asmBiExprIns("sub"); o.id1 = 0; o.id2 = 1; o.resId = 2;
            curAsmBlk.Lis.add(o);
            curAsmBlk.Lis.add(new asmSeqzIns("snez", "t2", "t2"));
        }
        asmStoreIns o = new asmStoreIns(1);
        o.target = u; o.storeValId = "t2";
        curAsmBlk.Lis.add(o);
    }
    @Override
    public void visit(irConBrIns it) {
        getDone(it.cond, "t0");
        if (irBlk2AsmBlk.get(it.trBlk) == null) {
            asmBlock u = new asmBlock();
            irBlk2AsmBlk.put(it.trBlk, u);
        }
        if (irBlk2AsmBlk.get(it.falBlk) == null) {
            asmBlock u = new asmBlock();
            irBlk2AsmBlk.put(it.falBlk, u);
        }
        asmBlock tr = irBlk2AsmBlk.get(it.trBlk);
        tr.asmBlockName = it.trBlk.labelName;
        asmBlock fa = irBlk2AsmBlk.get(it.falBlk);
        fa.asmBlockName = it.falBlk.labelName;
        asmBgtzIns u = new asmBgtzIns("t0", tr);
        curAsmBlk.Lis.add(u);
        curAsmBlk.Lis.add(new asmJIns(fa));
    }

    @Override
    public void visit(irFuncNode it) {
        asmFuncNode u = new asmFuncNode();
        pr.funcLis.add(u);
        int siz = 0;
        for (var blk : it.blockLis) {
            for (var v : blk.ls) {
                if (v instanceof irAllocIns) siz += 2;
                else if (!(v instanceof irBrIns || v instanceof irConBrIns)) siz++;
                if (v instanceof irCallFuncIns && u.maxParaCnt < ((irCallFuncIns) v).paraLis.size())
                    u.maxParaCnt = ((irCallFuncIns) v).paraLis.size();
            }
        }
        int h = (u.maxParaCnt + siz + 1) * 4;
        if (h % 16 != 0) siz = (h / 16 + 1) * 16;
        else siz = h;
        u.siz = siz;
        curAsmFunc = u;
        u.funcName = it.funcName;
        retPtr = newAsmReg();
        irReg2AsmReg = new HashMap<>();
        irBlk2AsmBlk = new HashMap<>();
        for (int i = 0; i < it.blockLis.size(); i++) {
            irBlock cur = it.blockLis.get(i);
            if (irBlk2AsmBlk.get(cur) == null) {
                asmBlock g = new asmBlock();
                irBlk2AsmBlk.put(cur, g);
            }
            curAsmBlk = irBlk2AsmBlk.get(cur);
            curAsmFunc.blkLis.add(curAsmBlk);
            if (i == 0) {
                asmAddiIns tt = new asmAddiIns("addi", -u.siz, "sp", "sp");
                curAsmBlk.Lis.add(tt);
                asmStoreIns o = new asmStoreIns(1);
                o.storeValId = "ra";
                o.target = retPtr;
                curAsmBlk.Lis.add(o);
                for (int j = 0; j < it.arName.size(); j++) {
                    asmReg r = new asmReg();
                    r.idInFunc = -(1 + j); r.spSiz = curAsmFunc.siz;
                    irReg2AsmReg.put((irRegister) it.arName.get(j), r);
                }
            }
            cur.accept(this);
        }
        curAsmBlk = null;
    }
    @Override
    public void visit(irGetEleIns it) {
        getDone(it.index, "t0");
        getDone(it.ptrReg, "t1");
        asmBiExprIns r1 = new asmBiExprIns("add"); r1.id1 = r1.id2 = r1.resId = 0;
        asmBiExprIns r2 = new asmBiExprIns("add"); r2.id1 = r2.id2 = r2.resId = 0;
        curAsmBlk.Lis.add(r1); curAsmBlk.Lis.add(r2);
        asmBiExprIns o = new asmBiExprIns("add");
        o.resId = 2; o.id1 = 0; o.id2 = 1;
        curAsmBlk.Lis.add(o);
        asmReg u = newAsmReg(); irReg2AsmReg.put(it.res, u);
        asmStoreIns oo = new asmStoreIns(1);
        oo.target = u; oo.storeValId = "t2";
        curAsmBlk.Lis.add(oo);
    }

    @Override
    public void visit(irStoreIns it) {
//       if (it.val instanceof irConst) {
//            int val = 0;
//            if (it.val instanceof irConstInt) val = ((irConstInt) it.val).val;
//            else if (it.val instanceof irConstBool) val = ((irConstBool) it.val).tf ? 1 : 0;
//            asmLiIns o = new asmLiIns();
//            o.imm = val; o.rd = "t0";
//            curAsmBlk.Lis.add(o);
//            asmReg h = irReg2AsmReg.get(it.desPtr);
//            asmStoreIns u = new asmStoreIns(1);
//            u.target = h; u.storeValId = 0;
//            curAsmBlk.Lis.add(u);
//       } else {
//           if (it.val.tp == irType.PTR) {
//                asmStoreIns u = new asmStoreIns(2);
//                asmReg h = irReg2AsmReg.get(it.val);
//                u.storeValId = h.idInFunc;
//                u.target = irReg2AsmReg.get(it.desPtr);
//                curAsmBlk.Lis.add(u);
//           } else {
//                getDone(it.val, "t0");
//                asmStoreIns u = new asmStoreIns(1);
//                asmReg h = irReg2AsmReg.get(it.desPtr);
//                u.target = h; u.storeValId = 0;
//                curAsmBlk.Lis.add(u);
//           }
//       }
        getDone(it.val, "t0");
        if (it.desPtr.isGlobal) {
            curAsmBlk.Lis.add(new asmLaIns(it.desPtr.getName(), "t3"));
            asmStoreIns o = new asmStoreIns(2); o.tg = "t3"; o.storeValId = "t0";
            curAsmBlk.Lis.add(o);
        } else {
            getDone(it.desPtr, "t1");
            asmStoreIns o = new asmStoreIns(2); o.tg = "t1"; o.storeValId = "t0";
            curAsmBlk.Lis.add(o);
        }
    }
    @Override
    public void visit(irRetNode it) {
        if (!it.isVoid) {
            getDone(it.retId,"t0");
            curAsmBlk.Lis.add(new asmAddiIns("addi", 0, "a0", "t0"));
        }
        asmLoadIns e = new asmLoadIns(); e.addrReg = retPtr; e.desReg = "ra";
        curAsmBlk.Lis.add(e);
        curAsmBlk.Lis.add(new asmAddiIns("addi", curAsmFunc.siz, "sp", "sp"));
        curAsmBlk.Lis.add(new asmRetIns());
    }
    @Override
    public void visit(irProgramNode it) {
        pr = new asmProgramNode();
        for (var u: it.strLis) u.accept(this);
        for (var u: it.glbVarLis) u.accept(this);
        for (var u: it.funcLis) u.accept(this);
        it.mainNd.accept(this);
    }
    @Override
    public void visit(irGlobalDef it) {
        asmGlobalDef u = new asmGlobalDef();
        if (it.pointTp == irType.I32) {
            if (it.initVal instanceof irConstInt) u.initVal = ((irConstInt) it.initVal).val;
            else u.initVal = 0;
        } else if (it.pointTp == irType.I1) {
            if (it.initVal instanceof irConstBool)  {
                if (((irConstBool) it.initVal).tf) u.initVal = 1;
                else u.initVal = 0;
            }
            else u.initVal = 0;
        } else { // ptr;
            u.initVal = 0;
        }
        u.name = it.glbVar.getName();
        pr.glbDefLis.add(u);
    }
    @Override
    public void visit(irGlobalStringConst it) {
        asmGlobalString u = new asmGlobalString();
        u.strName = it.reg.getName();
        u.str = it.OriStr;
        pr.glbStrLis.add(u);
    }
    @Override
    public void visit(irPhiIns it) {
        asmReg r = newAsmReg();
        irReg2AsmReg.put(it.res, r);
        for (int i = 0; i < it.blkLis.size(); i++) {
            irBlock blk = it.blkLis.get(i);
            asmBlock u = irBlk2AsmBlk.get(blk);
            Stack<asmInsNode> stk = new Stack<>();
            asmInsNode o = u.Lis.get(u.Lis.size()-1);
            while(u.Lis.size() > 0 && (o instanceof asmJIns || o instanceof asmBgtzIns)) {
                u.Lis.remove(o);
                stk.add(o);
                if (u.Lis.size() > 0) o = u.Lis.get(u.Lis.size()-1);
            }
            asmBlock cur = curAsmBlk;
            curAsmBlk = u;
            getDone(it.valLis.get(i), "t3");
            curAsmBlk = cur;
            asmStoreIns w = new asmStoreIns(1);
            w.target = r; w.storeValId = "t3";
            u.Lis.add(w);
            while(!stk.empty()) {
                u.Lis.add(stk.peek()); stk.pop();
            }
        }
    }
    @Override
    public void visit(irLoadNode it) {
        asmLoadIns u = new asmLoadIns();
        u.offset = 0; u.desReg = "t0"; u.addr = "t1";
        getDone(it.ptr, "t1");
        curAsmBlk.Lis.add(u);
        asmStoreIns v = new asmStoreIns(1);
        asmReg r = newAsmReg();
        irReg2AsmReg.put(it.res, r);
        v.storeValId = "t0"; v.target = r;
        curAsmBlk.Lis.add(v);
    }
}