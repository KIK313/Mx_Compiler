package irnode;

public interface irVisitor {
    void visit(irAllocIns it);
    void visit(irBiExprIns it);
    void visit(irBlock it);
    void visit(irBrIns it);
    void visit(irCallFuncIns it);
    void visit(irCmpIns it);
    void visit(irConBrIns it);
    void visit(irConstBool it);
    void visit(irConstInt it);
    void visit(irConstNull it);
    void visit(irFuncNode it);
    void visit(irGetEleIns it);
    void visit(irGlobalDef it);
    void visit(irGlobalStringConst it);
    void visit(irLoadNode it);
    void visit(irPhiIns it);
    void visit(irProgramNode it);
    void visit(irRetNode it);
    void visit(irStoreIns it);
}
