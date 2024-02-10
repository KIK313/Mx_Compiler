package irnode;

public class irRetNode extends irTerIns {
    public irType tp;
    public boolean isVoid = false;
    public irEntity retId;
    public irRetNode() {
        isVoid = true;
    }
    public irRetNode(irType tp, irEntity ret) {
        this.tp = tp;
        this.retId = ret;
    }
    @Override
    public String printIr() {
        if (isVoid) {
            return "ret void\n";
        }
        return "ret " + irFuncNode.printType(tp) + " " + retId.printIr() + "\n";
    }
}
