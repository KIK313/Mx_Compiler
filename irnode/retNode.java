package irnode;

public class retNode extends irTerIns {
    public irType tp;
    public boolean isVoid = false;
    public irEntity retId;
    public retNode() {
        isVoid = true;
    }
    public retNode(irType tp, irEntity ret) {
        this.tp = tp;
        this.retId = ret;
    }
    @Override
    public void printIr() {
//        if (isVoid) {
//            System.out.println("ret void");
//            return;
//        }
//        System.out.print("ret ");
//        irFuncNode.printType(tp);
//        System.out.println(retId);
    }
}
