package irnode;

abstract public class irInsNode {
    abstract public String printIr();
    abstract public void accept(irVisitor visitor);
}
