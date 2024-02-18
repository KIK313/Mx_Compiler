package asmnode;

public class asmGlobalDef {
    public int initVal;
    public String name;
    public asmGlobalDef() {
    }
    public String printASM() {
        String s = "  .globl " + name + "\n" + name + ":\n" + ".word " + Integer.toString(initVal) + "\n";
        return s;
    }
}