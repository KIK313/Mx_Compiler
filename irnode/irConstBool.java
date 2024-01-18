package irnode;

public class irConstBool extends irConst {
    public boolean tf;
    public irConstBool(boolean tf) {
        super(irType.I1);
        this.tf = tf;
    }
}
