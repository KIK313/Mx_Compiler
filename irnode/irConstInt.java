package irnode;

public class irConstInt extends irConst {
    public int val;
    public irConstInt(int val) {
        super(irType.I32);
        this.val = val;
    }
    @Override
    public String printIr() {
        return Integer.toString(val);
    }
}
