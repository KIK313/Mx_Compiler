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
    public void printIr() {
        System.out.print("define ");
        printType(returnType);
        System.out.print("@");
        System.out.print(funcName);
        System.out.print("(");
        for (int i = 0; i < arType.size(); i++) {
            if (i > 0) System.out.print(", ");
            printType(arType.get(i));
            System.out.print("%");
            System.out.print(arName.get(i));
        }
        System.out.println(") {");
        for (irBlock blockLi : blockLis) {
            blockLi.printIr();
        }
        System.out.println("}");
    }
    static void printType(irType nd) {
        switch (nd) {
            case I32 -> {
                System.out.print("i32 ");
            }
            case I1 -> {
                System.out.print("i1 ");
            }
            case PTR -> {
                System.out.print("ptr ");
            }
            case VOID -> {
                System.out.print("void ");
            }
        }
    }
}
