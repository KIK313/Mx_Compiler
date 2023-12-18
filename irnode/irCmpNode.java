package irnode;

public class irCmpNode extends irInsNode {
    public String resId, cmpOp, op1, op2;
    public irType tp;
    public irCmpNode(String resId, String cmpOp, irType tp, String op1, String op2) {
        this.resId = resId;
        this.cmpOp = cmpOp;
        this.tp = tp;
        this.op1 = op1;
        this.op2 = op2;
    }
    @Override
    public void printIr() {
        System.out.print(resId);
        System.out.print(" = icmp ");
        irFuncNode.printType(tp);
        System.out.print(op1);
        System.out.print(", ");
        System.out.println(op2);
    }
}
