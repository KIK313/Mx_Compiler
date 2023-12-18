package irnode;

public class irBiNode extends irInsNode {
    public String resId, opt, op1, op2;
    public irType tp;
    public irBiNode(String resId, String opt, irType tp, String op1, String op2) {
        this.resId = resId;
        this.opt = opt;
        this.tp = tp;
        this.op1 = op1;
        this.op2 = op2;
    }
    @Override
    public void printIr() {
        System.out.print(resId);
        System.out.print(" = ");
        System.out.print(opt);
        irFuncNode.printType(tp);
        System.out.print(op1);
        System.out.print(", ");
        System.out.println(op2);
    }
}
