package irnode;

public class irConstNull extends irConst {
    public irConstNull() {
        super(irType.PTR);
    }
    @Override
    public String printIr() {
        return "null";
    }
}
