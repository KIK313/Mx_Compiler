package irnode;

public class irGlobalStringConst{
    public irRegister reg;
    public String s;
    public String irS;
    public int len;
    public irGlobalStringConst(String s) {
        this.reg = new irRegister(".str_", irType.PTR);
        this.reg.isGlobal = true;
        this.s = "";
        for (int i = 1; i + 1 < s.length(); i++) this.s += s.charAt(i);
        this.irS = trans(this.s);
        this.len = this.irS.length();
    }
    public String trans(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case '\n': res += "\\0A"; break;
                case '\"': res += "\\22"; break;
                case '\\': res += "\\\\"; break;
                default: res += c;
            }
        }
        res += "\\00";
        return res;
    }
    public String printIr() {
        String s = reg.printIr() + " = private unnamed_addr constant [" + Integer.toString(len) + " x i8] c\"" + irS + "\"\n";
        return s;
    }
}
