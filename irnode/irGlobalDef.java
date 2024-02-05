package irnode;

public class irGlobalDef {
    public irType pointTp;
    public irRegister glbVar;
    public irEntity initVal;

    public irGlobalDef(irRegister glbVar, irType pointTp, irEntity initVal) {
        this.glbVar = glbVar;
        this.pointTp = pointTp;
        this.initVal = initVal;
    }
    public void printIr() {
//        System.out.print("@");
//        System.out.print(varName);
//        System.out.print("-1 = global ");
//        irFuncNode.printType(tp);
//        switch (tp) {
//            case I32 -> {
//                System.out.print(intVal);
//            }
//            case I1 -> {
//                if (is) System.out.print("true");
//                else System.out.print("false");
//            }
//            case PTR -> {
//                System.out.print("null");
//            }
//            case VOID -> {}
//        }
//        System.out.println();
    }
}
