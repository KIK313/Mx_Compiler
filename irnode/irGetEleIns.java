package irnode;

import java.util.ArrayList;
import java.util.List;

public class irGetEleIns extends irInsNode {
    public irRegister res;
    public irType tp;
    public irRegister ptrReg;
    public irEntity index;

    public irGetEleIns(irRegister res, irRegister ptrReg, irEntity index) {
        this.res = res;
        this.ptrReg = ptrReg;
        this.index = index;
    }
    @Override
    public void accept(irVisitor visitor) {visitor.visit(this);}
    @Override
    public String printIr() {
        return res.printIr() + " = getelementptr i32, ptr " + ptrReg.printIr() + ", " + irFuncNode.printType(index.tp) + " " + index.printIr() + "\n";
    }
}
