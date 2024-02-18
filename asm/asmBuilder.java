package asm;
import irnode.*;
import asmnode.*;
public class asmBuilder implements irVisitor{
    public asmProgramNode pr = null;
    @Override
    public void visit(irAllocIns it) {

    }
    @Override
    public void visit(irBiExprIns it) {

    }
    @Override
    public void visit(irBrIns it) {

    }
    @Override
    public void visit(irBlock it) {

    }
    @Override
    public void visit(irCallFuncIns it) {

    }
    @Override
    public void visit(irCmpIns it) {

    }
    @Override
    public void visit(irConBrIns it) {

    }

    @Override
    public void visit(irFuncNode it) {

    }
    @Override
    public void visit(irGetEleIns it) {

    }

    @Override
    public void visit(irStoreIns it) {

    }
    @Override
    public void visit(irRetNode it) {

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

    }
    @Override
    public void visit(irConstBool it) {

    }
    @Override
    public void visit(irConstInt it) {

    }
    @Override
    public void visit(irConstNull it) {

    }
}
