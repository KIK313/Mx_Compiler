package irnode;

public class irBrNode extends irTerIns {
    public irBlock desBlk = null;
    public irBrNode(irBlock desBlk) {
        super();
        this.desBlk = desBlk;
    }
    @Override
    public void printIr() {

    }
}
