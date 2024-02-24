package asmnode;

import java.util.ArrayList;

public class asmFuncNode {
    public String funcName;
    public ArrayList<asmBlock> blkLis;
    public int regCnt = 0;
    public int maxParaCnt = 0;
    public int siz;
    public asmFuncNode() {
        blkLis = new ArrayList<>();
    }
    public String printASM() {
        String s = "  .globl " + funcName + "\n" + funcName + ":\n";
        for (var u : blkLis) s += u.printASM();
        return s;
    }
}