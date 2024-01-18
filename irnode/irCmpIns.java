package irnode;

public class irCmpIns extends irInsNode {
    public String cmpOp;
    public irRegister res;
    public irEntity ls, rs;
    public irType tp;
    public irCmpIns(irRegister res, String cmpOp, irType tp, irEntity ls, irEntity rs) {
        this.res = res;
        this.cmpOp = cmpOp;
        this.tp = tp;
        this.ls = ls;
        this.rs = rs;
    }
    @Override
    public void printIr() {
//        System.out.print(resId);
//        System.out.print(" = icmp ");
//        irFuncNode.printType(tp);
//        System.out.print(op1);
//        System.out.print(", ");
//        System.out.println(op2);
    }
}
