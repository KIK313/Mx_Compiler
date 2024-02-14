package irnode;

public class irConBrIns extends irTerIns {
    public irEntity cond;
    public irBlock trBlk, falBlk;
    public irConBrIns(irEntity cond, irBlock trBlk, irBlock falBlk) {
        this.cond = cond;
        this.trBlk = trBlk;
        this.falBlk = falBlk;
    }
    @Override
    public String printIr() {
        return "br i1 " + cond.printIr() + ", label " + trBlk.printName() + ", label " + falBlk.printName() + "\n";
    }
}
