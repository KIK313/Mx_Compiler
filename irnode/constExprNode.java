package irnode;

public class constExprNode extends exprNode {
    public int val;
    public boolean is;
    public String s;
    public constExprNode(String s) {
        super();
        this.s = s;
    }
    public constExprNode(boolean is) {
        this.is = is;
    }
    public constExprNode(int val) {
        this.val = val;
    }
}
