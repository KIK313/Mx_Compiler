package irnode;

abstract public class irEntity {
    public irType tp;
    public irEntity(irType tp) {
        this.tp = tp;
    }
    public abstract String printIr();
}
