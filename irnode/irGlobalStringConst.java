package irnode;

public class irGlobalStringConst{
    public irRegister reg;
    public String OriStr;
    public String s;
    public String irS;
    public int len;
    public irGlobalStringConst(String s) {
        this.reg = new irRegister(".str_", irType.PTR);
        this.reg.isGlobal = true;
        this.OriStr = "";
        for (int i = 1; i + 1 < s.length(); i++) this.OriStr += s.charAt(i);
        this.s = trs(this.OriStr);
        this.irS = trans(this.s);
        this.len = this.s.length() + 1;
    }
    public String trs(String s) {
        return s.replace("\\\\", "\\")
                .replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace("\\\"", "\"");
    }
    public String trans(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
           // System.out.println(c);
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
