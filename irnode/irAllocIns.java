package irnode;

public class irAllocIns extends irInsNode {
    public irRegister reg;
    public irType tp;
    public irAllocIns(irRegister reg, irType tp) {
        this.reg = reg;
        this.tp = tp;
    }
    @Override
    public String printIr() {
        String s = reg.printIr() + " = alloca " + irFuncNode.printType(tp) + "\n";
        return s;
    }
}
