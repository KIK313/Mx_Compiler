package irnode;

import java.util.ArrayList;
import java.util.List;

public class irBlock {
    public String labelName;
    public List<irInsNode> ls;
    public irInsNode terminalIns;
    public boolean isEnd = false;
    public irBlock(String name) {
        this.labelName = name;
        ls  = new ArrayList<>();
        isEnd = false;
    }
    public void appendIns(irInsNode nd) {
        if (isEnd) return;
        if (nd instanceof irTerIns) {
            terminalIns = nd;
            isEnd = true;
        } else ls.add(nd);
    }
    public void printIr() {
        System.out.print(labelName);
        System.out.println(":");
        for (int i = 0; i < ls.size(); i++) {
            ls.get(i).printIr();
        }
    }
}
