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
    public void accept(irVisitor visitor) {visitor.visit(this);}
    @Override
    public String printIr() {
        String s = res.printIr() + " = icmp " + cmpOp + " " + irFuncNode.printType(tp) + " " + ls.printIr() + ", " + rs.printIr() + "\n";
        return s;
    }
}
