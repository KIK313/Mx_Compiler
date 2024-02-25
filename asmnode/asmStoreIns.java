package asmnode;

public class asmStoreIns extends asmInsNode {
    public asmReg target;
    public int tp;
    public int storeId;
    public String tg;
    public String storeValId;
    public asmStoreIns(int tp) {
        this.tp = tp;
    }
    @Override
    public String printASM() {
        String s;
        if (tp == 1) {
            s = "sw " + storeValId + ", " + (target.spSiz - (target.idInFunc + 1) * 4) + "(sp)\n";
        } else if (tp == 3) {
            s = "sw " + storeValId + ", " + (storeId * 4) + "(sp)\n";
        } else {
            s = "sw " + storeValId + ", 0(" + tg + ")\n";
        }
        return s;
    }
}
