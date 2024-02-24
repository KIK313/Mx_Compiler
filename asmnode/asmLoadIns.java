package asmnode;

public class asmLoadIns extends asmInsNode {
    public int offset;
    public String desReg;
    public asmReg addrReg;
    public String addr = null;
    public asmLoadIns() {

    }
    public String printASM() {
        String s = "";
        if (addr == null) {
            s = "lw " + desReg + ", " + (addrReg.spSiz - (addrReg.idInFunc + 1) * 4) + "(sp)" + "\n";
        } else {
            s = "lw " + desReg + ", " + "0(" + addr + ")\n";
        }
        return s;
    }
}
