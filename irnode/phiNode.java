package irnode;

import java.util.List;

public class phiNode extends irInsNode {
    public String resId;
    public irType tp;
    public List<String> valLis, labelLis;
    @Override
    public void printIr() {
        System.out.print(resId);
        System.out.print(" = phi ");
        irFuncNode.printType(tp);
        for (int i = 0; i < valLis.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print("[");
            System.out.print(valLis.get(i));
            System.out.print(", ");
            System.out.print(labelLis.get(i));
            System.out.print("]");
        }
        System.out.print("\n");
    }
}
