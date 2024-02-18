package asmnode;

public class asmGlobalString {
    public String strName;
    public String str;
    public asmGlobalString() {

    }
    public String printASM() {
        String s = strName + ":\n" + "  .asciz " + "\"" + str + "\"\n";
        return s;
    }
}
