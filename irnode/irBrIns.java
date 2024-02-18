package irnode;

public class irBrIns extends irTerIns {
    public irBlock desBlk = null;
    public irBrIns(irBlock desBlk) {
        super();
        this.desBlk = desBlk;
    }
    @Override
    public void accept(irVisitor visitor) {visitor.visit(this);}
    @Override
    public String printIr() {
        return "br label " + desBlk.printName() + "\n";
    }
}
