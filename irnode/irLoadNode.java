package irnode;

public class irLoadNode extends irInsNode {
    public irRegister res;
    public irType tp;
    public irRegister ptr;
    public irLoadNode(irRegister resId, irType tp, irRegister ptrId) {
        this.res = resId;
        this.tp = tp;
        this.ptr = ptrId;
    }
    @Override
    public void accept(irVisitor visitor) {visitor.visit(this);}
    @Override
    public String printIr() {
        String s = res.printIr() + " = load " + irFuncNode.printType(tp) + ", ptr " + ptr.printIr() + "\n";
        return s;
    }
}
