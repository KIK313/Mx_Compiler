package irnode;

public class storeNode extends irInsNode {
    public irEntity val;
    public irRegister desPtr;
    public storeNode(irEntity val, irRegister desPtr) {
        this.val = val;
        this.desPtr = desPtr;
    }
    @Override
    public void printIr() {
//        System.out.print("store ");
//        irFuncNode.printType(tp);
//        System.out.print(val);
//        System.out.print(", ptr ");
//        System.out.println(ptrId);
    }
}
