package irnode;

import java.util.ArrayList;
import java.util.List;

public class irClassDef {
    String name;
    List<irType> ls;
    public irClassDef(String name) {
        this.name = name;
        ls = new ArrayList<>();
    }
    public void appendType(irType o) {
        ls.add(o);
    }
    public String printIr() {
        String s = "%class." + name + " = type {";
        for (int i = 0; i < ls.size(); i++) {
            if (i > 0) s += ",";
            s += irFuncNode.printType(ls.get(i));
        }
        s += "}\n";
        return s;
    }
}
