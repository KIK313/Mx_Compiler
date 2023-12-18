package irnode;

import java.util.ArrayList;
import java.util.List;

public class irMainNode {
    public List<irBlock> blockLis;
    public irMainNode() {
        blockLis = new ArrayList<>();
    }
    public void appendBlock(irBlock blk) {
        blockLis.add(blk);
    }
    public void printIr() {
        System.out.println("define i32 @main() {");
        for (int i = 0; i < blockLis.size(); i++) {
            blockLis.get(i).printIr();
        }
        System.out.println("}");
    }
}
