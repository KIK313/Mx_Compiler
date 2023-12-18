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
    public void printIr() {
        System.out.print("%class.");
        System.out.print(name);
        System.out.print(" = ");
        System.out.print("type ");
        System.out.print("{");
        for (int i = 0; i < ls.size(); i++) {
            if (i > 0) System.out.print(",");
            irFuncNode.printType(ls.get(i));
        }
        System.out.println("}");
    }
}
