package ast;

public interface ASTVisitor {
    void visit(ProgramNode it);
    void visit(class_defNode it);
    void visit(variable_defNode it);
    void visit(main_defNode it);
    void visit(class_constructorNode it);
    void visit(func_defNode it);
    void visit(suiteNode it);
    void visit(exp_statNode it);
    void visit(def_statNode it);
    void visit(continue_statNode it);
    void visit(break_statNode it);
    void visit(return_statNode it);
    void visit(deffor_statNode it);
    void visit(expfor_statNode it);
    void visit(if_statNode it);
    void visit(while_statNode it);
    void visit(paralistNode it);
    void visit(empty_statNode it);
    void visit(varinitNode it);
    void visit(iden_exprNode it);
    void visit(literal_exprNode it);
    void visit(class_call_exprNode it);
    void visit(class_exprNode it);
    void visit(array_exprNode it);
    void visit(func_exprNode it);
    void visit(paren_exprNode it);
    void visit(typeNode it);
    void visit(rightAddExprNode it);
    void visit(leftAddExprNode it);
    void visit(not_exprNode it);
    void visit(new_exprNode it);
    void visit(biexprNode it);
    void visit(tri_exprNode it);
    void visit(assign_exprNode it);
    void visit(new_typeNode it);
    void visit(func_call_listNode it);
}
