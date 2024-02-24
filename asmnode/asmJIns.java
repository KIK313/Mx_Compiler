package asmnode;

public class asmJIns extends asmInsNode {
    public asmBlock desBlk;
    public asmJIns(asmBlock desBlk) {
        this.desBlk = desBlk;
    }
    public String printASM() {
        String s = "j " + desBlk.asmBlockName + "\n";
        return s;
    }
}
