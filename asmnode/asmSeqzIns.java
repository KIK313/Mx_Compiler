package asmnode;

public class asmSeqzIns extends asmInsNode {
    public String op;
    public String res,r;
    public asmSeqzIns(String op, String res, String r) {
        this.op = op;
        this.res = res;
        this.r = r;
    }
    @Override
    public String printASM() {
        String s = op + " " + res + ", " + r + "\n";
        return s;
    }
}
