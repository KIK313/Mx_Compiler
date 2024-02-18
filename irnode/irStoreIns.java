package irnode;

public class irStoreIns extends irInsNode {
    public irEntity val;
    public irRegister desPtr;
    public irType tp;
    public irStoreIns(irType tp, irEntity val, irRegister desPtr) {
        this.tp = tp;
        this.val = val;
        this.desPtr = desPtr;
    }
    @Override
    public void accept(irVisitor visitor) {visitor.visit(this);}
    @Override
    public String printIr() {
        String s = "store " + irFuncNode.printType(tp) + " " + val.printIr() + ", ptr " + desPtr.printIr() + "\n";
        return s;
    }
}
