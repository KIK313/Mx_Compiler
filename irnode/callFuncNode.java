package irnode;

import java.awt.desktop.SystemSleepEvent;
import java.util.List;

public class callFuncNode extends irInsNode {
    public String funcName, resId;
    public irType tp;
    public List<irType> arType;
    public List<String> arName;
    public callFuncNode(String resId, irType tp, String name, List<irType> arType, List<String> arName) {
        this.resId = resId;
        this.tp = tp;
        this.funcName = name;
        this.arType = arType;
        this.arName = arName;
    }
    @Override
    public void printIr() {
        System.out.print(resId);
        System.out.print(" = call ");
        irFuncNode.printType(tp);
        System.out.print("@");
        System.out.print(funcName);
        System.out.print("(");
        for (int i = 0; i < arName.size(); i++) {
            if (i > 0) System.out.print(" , ");
            irFuncNode.printType(arType.get(i));
            System.out.print(arName.get(i));
        }
        System.out.println(")");
    }

}
