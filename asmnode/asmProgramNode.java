package asmnode;

import java.util.ArrayList;

public class asmProgramNode {
    public ArrayList<asmGlobalDef> glbDefLis;
    public ArrayList<asmGlobalString> glbStrLis;
    public ArrayList<asmFuncNode> funcLis;

    public asmProgramNode() {
        glbDefLis = new ArrayList<>();
        glbStrLis = new ArrayList<>();
        funcLis = new ArrayList<>();
    }
    public String printASM() {
        String s = "";
        s += "  .section text\n";
        for (var u : funcLis) s += u.printASM();
        if (glbDefLis.size() > 0) s += "  .section data\n";
        for (var u : glbDefLis) s += u.printASM();
        if (glbStrLis.size() > 0) s += "  .section rodata\n";
        for (var u : glbStrLis) s += u.printASM();
        return s;
    }
}
