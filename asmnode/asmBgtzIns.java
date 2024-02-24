package asmnode;

public class asmBgtzIns extends asmInsNode {
    public asmBlock desBlk;
    public String con;
    public asmBgtzIns(String con, asmBlock desBlk) {
        this.con = con;
        this.desBlk = desBlk;
    }
    public String printASM() {
        String s = "bgtz " + con + ", " + desBlk.asmBlockName + "\n";
        return s;
    }
}
