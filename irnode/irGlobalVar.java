package irnode;

public class irGlobalVar {
    public irType tp;
    public String varName;
    public int intVal;
    public boolean is;

    public irGlobalVar(irType tp, String name) {
        this.tp = tp;
        this.varName = name;
        this.intVal = 0;
        this.is = false;
    }
    public void printIr() {
        System.out.print("@");
        System.out.print(varName);
        System.out.print("-1 = global ");
        irFuncNode.printType(tp);
        switch (tp) {
            case I32 -> {
                System.out.print(intVal);
            }
            case I1 -> {
                if (is) System.out.print("true");
                else System.out.print("false");
            }
            case PTR -> {
                System.out.print("null");
            }
            case VOID -> {}
        }
        System.out.println();
    }
}
