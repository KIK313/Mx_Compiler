package asm;
import irnode.*;
import asmnode.*;

import java.util.HashMap;

public class asmBuilder implements irVisitor{
    public asmProgramNode pr = null;
    public asmBlock curAsmBlk = null;
    public asmFuncNode curAsmFunc = null;
    public HashMap<irRegister, asmReg> irReg2AsmReg;
    @Override
    public void visit(irAllocIns it) {
        asmReg u = new asmReg();
        u.idInFunc = curAsmFunc.regCnt++;
        irReg2AsmReg.put(it.reg, u);
    }
    void getDone(irEntity o, int id) {
        if (o instanceof irConst) {
            if (o instanceof irConstInt) {
                asmLiIns u = new asmLiIns();
                u.imm = ((irConstInt) o).val; u.rd = "t" + Integer.toString(id);
                curAsmBlk.Lis.add(u);
            }
        } else {
            if (o instanceof irRegister) {
                asmReg u = irReg2AsmReg.get((irRegister) o);
                asmLoadIns ld = new asmLoadIns();
                ld.addrReg = u;
                ld.desReg = "t" + Integer.toString(id);
                curAsmBlk.Lis.add(ld);
            }
        }
    }
    @Override
    public void visit(irBiExprIns it) {
        asmReg u = new asmReg();
        u.idInFunc = curAsmFunc.regCnt++;
        irReg2AsmReg.put(it.res, u);
        getDone(it.lhs, 1); getDone(it.rhs, 2);
        String name = it.op;
        if (name.equals("sdiv")) name = "div";
        else if (name.equals("srem")) name = "rem";
        else if (name.equals("shl")) name = "sll";
        else if (name.equals("ashr")) name = "sra";
        asmBiExprIns o = new asmBiExprIns(name);
        o.id1 = 1; o.id2 = 2;
        o.resId = 0;
        curAsmBlk.Lis.add(o);
        asmStoreIns oo = new asmStoreIns();
        oo.target = u;
        oo.storeValId = 0;
        curAsmBlk.Lis.add(oo);
    }
    @Override
    public void visit(irBrIns it) {

    }
    @Override
    public void visit(irBlock it) {

    }
    @Override
    public void visit(irCallFuncIns it) {
        if (it.tp == irType.VOID) {

        } else {

        }
    }
    @Override
    public void visit(irCmpIns it) {

    }
    @Override
    public void visit(irConBrIns it) {

    }

    @Override
    public void visit(irFuncNode it) {
        asmFuncNode u = new asmFuncNode();
        curAsmFunc = u;
        u.funcName = it.funcName;

        curAsmBlk = null;
    }
    @Override
    public void visit(irGetEleIns it) {

    }

    @Override
    public void visit(irStoreIns it) {
       // it.val; it.
    }
    @Override
    public void visit(irRetNode it) {
        if (it.tp == irType.VOID) {

        } else {

        }
    }
    @Override
    public void visit(irProgramNode it) {
        pr = new asmProgramNode();
        for (var u: it.strLis) u.accept(this);
        for (var u: it.glbVarLis) u.accept(this);
        for (var u: it.funcLis) u.accept(this);
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

    }
    @Override
    public void visit(irLoadNode it) {
        asmLoadIns u = new asmLoadIns();
        u.offset = 0; u.desReg = "t0";
        curAsmBlk.Lis.add(u);
        asmStoreIns v = new asmStoreIns();
        asmReg r = new asmReg();
        r.idInFunc = curAsmFunc.regCnt++;
        irReg2AsmReg.put(it.res, r);
        v.storeValId = 0; v.target = r;
        curAsmBlk.Lis.add(v);
    }
}