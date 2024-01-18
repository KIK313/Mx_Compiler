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
    public void printIr() {
//        System.out.print(resId);
//        System.out.print(" =  getelementptr ");
//        System.out.print(tp);
//        System.out.print(", ptr ");
//        System.out.print(ptrId);
//        for (int i = 0; i < ls.size(); i++) {
//            if (i > 0) System.out.print(", ");
//            System.out.print("i32 ");
//            System.out.print(ls.get(i));
//        }
//        System.out.println("");
    }
}
