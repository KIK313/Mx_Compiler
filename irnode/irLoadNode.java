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
    public void printIr() {
//        System.out.print(resId);
//        System.out.print(" = load ");
//        irFuncNode.printType(tp);
//        System.out.print(", ptr ");
//        System.out.println(ptrId);
    }
}
