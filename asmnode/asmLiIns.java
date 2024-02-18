package asmnode;

public class asmLiIns extends asmInsNode{
    public String rd;
    public int imm;
    public asmLiIns() {

    }
    @Override
    public String printASM() {
        String s = "li " + rd + ", " + Integer.toString(imm) + "\n";
        return s;
    }
}
