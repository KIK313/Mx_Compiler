package asmnode;

public class asmCmpIns extends asmInsNode {
    public String op;
    String res, lhs, rhs;
    public asmCmpIns(String op, String res, String lhs, String rhs) {
        this.op = op;
        this.res = res;
        this.lhs = lhs;
        this.rhs = rhs;
    }
    @Override
    public String printASM() {
        String s = op + " " + res + ", " + lhs + ", " + rhs + "\n";
        return s;
    }
}
