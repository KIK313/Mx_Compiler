package irnode;

public class irConBrNode extends irTerIns {
    public irEntity cond;
    public irBlock trBlk, falBlk;
    public irConBrNode(irEntity cond, irBlock trBlk, irBlock falBlk) {
        this.cond = cond;
        this.trBlk = trBlk;
        this.falBlk = trBlk;
    }

    @Override
    public void printIr() {
//        System.out.print("br i1 %");
//        System.out.print(conId);
//        System.out.print(", label %");
//        System.out.print(trLabel);
//        System.out.print(", label %");
//        System.out.println(faLabel);
    }
}
