package irnode;

public class irRegister extends irEntity {
    public String name;
    public boolean isGlobal = false;
    public int id = -1;
    public static int regCnt = 0;
    public irRegister(String name, irType tp) {
        super(tp);
        this.name = name;
    }
    public String getName() {
        if (id == -1) id = ++regCnt;
        return name + Integer.toString(id);
    }
    @Override
    public String printIr() {
        String s;
        if (isGlobal) s = "@";
        else s = "%";
        if (id == -1) id = ++regCnt;
        return s + "_" + name + Integer.toString(id);
    }
}
