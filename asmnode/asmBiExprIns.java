package asmnode;

public class asmBiExprIns extends asmInsNode {

    public String op;
    public int resId, id1, id2;
    public asmBiExprIns(String s) {
        op = s;
    }
    @Override
    public String printASM() {
        String s = "";
        return s;
    }
}