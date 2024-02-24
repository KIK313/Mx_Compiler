package asmnode;

public class asmStoreIns extends asmInsNode {
    public asmReg target;
    public int tp;
    public int storeId;
    public String storeValId;
    public asmStoreIns(int tp) {
        this.tp = tp;
    }
    @Override
    public String printASM() {
        String s;
        if (tp == 1) {
            s = "sw " + storeValId + ", " + (target.spSiz - (target.idInFunc + 1) * 4) + "(sp)\n";
        } else {
            s = "sw " + storeValId + ", " + (storeId * 4) + "(sp)\n";
        }
        return s;
    }
}
