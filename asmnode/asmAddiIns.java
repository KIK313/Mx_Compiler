package asmnode;

public class asmAddiIns extends asmInsNode{
    public String op;
    public int imm;
    public String res,r;
    public asmAddiIns(String op, int imm, String res, String r) {
        this.op = op; this.imm = imm; this.res = res; this.r = r;
    }
    @Override
    public String printASM() {
        String s = "addi " + res + ", " + r + ", " + imm + "\n";
        return s;
    }
}
