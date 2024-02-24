package asmnode;

import java.util.ArrayList;

public class asmBlock {
    public String asmBlockName = null;
    public ArrayList<asmInsNode> Lis;
    public asmBlock() {
        Lis = new ArrayList<>();
    }
    public String printASM() {
        String s = "";
        if (asmBlockName != null) s = asmBlockName + ":\n";
        for (var u : Lis) s += "  " + u.printASM();
        return s;
    }
}