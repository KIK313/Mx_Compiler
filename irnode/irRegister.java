package irnode;

public class irRegister extends irEntity {
    String name;
    public irRegister(String name, irType tp) {
        super(tp);
        this.name = name;
    }
}
