package irnode;

public class irBiExprIns extends irInsNode {
    public irEntity lhs, rhs;
    public String op;
    public irType tp;
    public irRegister res;
    public irBiExprIns(irRegister res, String op, irType tp, irEntity ls, irEntity rs) {
        super();
        this.res = res;
        this.op = op;
        this.tp = tp;
        this.lhs = ls;
        this.rhs = rs;
    }
    @Override
    public String printIr() {
        String s = res.printIr();
        s = s + " = " + op + " " + lhs.printIr() + " " + rhs.printIr() + "\n";
        return s;
    }
}
