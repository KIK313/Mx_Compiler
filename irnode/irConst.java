package irnode;

abstract public class irConst extends irEntity {
    public irConst(irType tp) {
        super(tp);
    }
    abstract public String printIr();
}
