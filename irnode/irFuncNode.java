package irnode;

import java.util.ArrayList;
import java.util.List;

public class irFuncNode {
    public String funcName;
    public irType returnType;
    public List<irType> arType;
    public List<irEntity> arName;
    public List<irBlock> blockLis;
    public irFuncNode(String name) {
        this.funcName = name;
        this.arType = new ArrayList<>();
        this.arName = new ArrayList<>();
        blockLis = new ArrayList<>();
    }
    public void appendBlock(irBlock blk) {
        blockLis.add(blk);
    }
    public void accept(irVisitor visitor) {visitor.visit(this);}
    public String printIr() {
        //System.out.println(funcName + " -100");
        String s = "define " + irFuncNode.printType(returnType) + " @" + funcName + "(";
        for (int i = 0; i < arType.size(); i++) {
            if (i > 0) s += ", ";
            s += irFuncNode.printType(arType.get(i)) + " " + arName.get(i).printIr();
        }
        s += ") {\n";
        for (irBlock blockLi : blockLis) s += blockLi.printIr();
        s += "}\n";
        return s;
    }
    static String printType(irType nd) {
        switch (nd) {
            case I32 -> {
                return "i32";
            }
            case I1 -> {
                return "i1";
            }
            case PTR -> {
                return "ptr";
            }
            case VOID -> {
                return "void";
            }
        }
        return null;
    }
}
