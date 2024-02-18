package irnode;

import java.util.ArrayList;
import java.util.List;

public class irBlock {
    public String labelName;
    public List<irInsNode> ls;
    public irInsNode terminalIns;
    static public int blkCnt = 0;
    public boolean isEnd = false;
    public irBlock(String name) {
        this.labelName = name + Integer.toString(++blkCnt);
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
    public void accept(irVisitor visitor) {visitor.visit(this);}
    public String printIr() {
        //System.out.println(labelName);
        String s = labelName + ":\n";
        for (var u : ls) {
            s += "  " + u.printIr();
        }
        s += "  " + terminalIns.printIr();
        return s;
    }
    public String printName() {
        String s = "%";
        s += labelName;
        return s;
    }
}
