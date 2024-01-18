package irnode;

import java.util.ArrayList;
import java.util.List;

public class irCallFuncIns extends irInsNode {
    public String funcName;
    public irRegister res = null;
    public irType tp;
    public List<irEntity> paraLis = null;
    public irCallFuncIns(irRegister res, irType tp, String funcName) {
        this.res = res;
        this.tp = tp;
        this.funcName = funcName;
        this.paraLis = new ArrayList<>();
    }
    @Override
    public void printIr() {
//        System.out.print(resId);
//        System.out.print(" = call ");
//        irFuncNode.printType(tp);
//        System.out.print("@");
//        System.out.print(funcName);
//        System.out.print("(");
//        for (int i = 0; i < arName.size(); i++) {
//            if (i > 0) System.out.print(" , ");
//            irFuncNode.printType(arType.get(i));
//            System.out.print(arName.get(i));
//        }
//        System.out.println(")");
    }

}
