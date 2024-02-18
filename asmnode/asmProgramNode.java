package asmnode;

import java.util.ArrayList;

public class asmProgramNode {
    public ArrayList<asmGlobalDef> glbDefLis;
    public ArrayList<asmGlobalString> glbStrLis;
    public asmProgramNode() {
        glbDefLis = new ArrayList<>();
        glbStrLis = new ArrayList<>();

    }
}
