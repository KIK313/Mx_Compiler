package irnode;

public class biExprNode extends exprNode {
    public exprNode lhs, rhs;
    public biExprNode(exprNode ls, exprNode rs) {
        super();
        this.lhs = ls;
        this.rhs = rs;
    }
}
