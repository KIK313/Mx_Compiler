package irnode;

public class allocNode extends irInsNode {
    public irRegister reg;
    public irType tp;
    public allocNode(irRegister reg, irType tp) {
        this.reg = reg;
        this.tp = tp;
    }
    @Override
    public void printIr() {
//        System.out.print("%");
//        System.out.print(name);
//        System.out.print(" = alloca ");
//        irFuncNode.printType(tp);
    }
}
