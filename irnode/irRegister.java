package irnode;

public class irRegister extends irEntity {
    public String name;
    public boolean isGlobal = false;
    public irRegister(String name, irType tp) {
        super(tp);
        this.name = name;
    }
}
