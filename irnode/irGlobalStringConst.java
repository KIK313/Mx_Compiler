package irnode;

public class irGlobalStringConst{
    public irRegister reg;
    public String s;
    public String irS;
    public int len;
    public irGlobalStringConst(String s) {
        this.reg = new irRegister(".str_", irType.PTR);
        this.s = s;
        this.irS = trans(s);
        this.len = this.irS.length() + 1;
    }
    public String trans(String s) {
        // to be done
        return s;
    }
}
