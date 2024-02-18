package irnode;

abstract public class irTerIns extends irInsNode {
    abstract public String printIr();
    abstract public void accept(irVisitor visitor);
}
