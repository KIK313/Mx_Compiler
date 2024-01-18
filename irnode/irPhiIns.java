package irnode;

import java.util.ArrayList;
import java.util.List;

public class irPhiIns extends irInsNode {
    public irType tp;
    public irRegister res = null;
    public List<irEntity> valLis;
    public List<irBlock> blkLis;
    public irPhiIns(irType tp) {
        this.tp = tp;
        valLis = new ArrayList<>();
        blkLis = new ArrayList<>();
    }
    @Override
    public void printIr() {
//        System.out.print(resId);
//        System.out.print(" = phi ");
//        irFuncNode.printType(tp);
//        for (int i = 0; i < valLis.size(); i++) {
//            if (i > 0) System.out.print(", ");
//            System.out.print("[");
//            System.out.print(valLis.get(i));
//            System.out.print(", ");
//            System.out.print(labelLis.get(i));
//            System.out.print("]");
//        }
//        System.out.print("\n");
    }
}
