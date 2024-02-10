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
    public String printIr() {
        String s;
        if (tp == irType.VOID) s = "call void @" + funcName + "(";
            else s = res.printIr() + " = call " + irFuncNode.printType(tp) + " @" + funcName + "(";
        for (int i = 0; i < paraLis.size(); i++) {
            if (i > 0) s += ", ";
            s += irFuncNode.printType(paraLis.get(i).tp) + " " + paraLis.get(i).printIr();
        }
        s += ")\n";
        return s;
    }
}
