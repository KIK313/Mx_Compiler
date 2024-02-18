package irnode;

import java.util.ArrayList;
import java.util.List;

public class irPhiIns extends irInsNode {
    public irType tp;
    public irRegister res = null;
    public List<irEntity> valLis;
    public List<irBlock> blkLis;
    public irPhiIns(irType tp) {
        this.tp = tp;
        valLis = new ArrayList<>();
        blkLis = new ArrayList<>();
    }
    @Override
    public void accept(irVisitor visitor) {visitor.visit(this);}
    @Override
    public String printIr() {
        String s = res.printIr() + " = phi " +  irFuncNode.printType(tp) + " ";
        for (int i = 0; i < valLis.size(); i++) {
            if (i > 0) s += ", ";
            s += "[ " + valLis.get(i).printIr() + ", " + blkLis.get(i).printName() + " ]";
        }
        s += "\n";
        return s;
    }
}
