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
    public void printIr() {
//        System.out.print("store ");
//        irFuncNode.printType(tp);
//        System.out.print(val);
//        System.out.print(", ptr ");
//        System.out.println(ptrId);
    }
}
