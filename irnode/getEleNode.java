package irnode;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

public class getEleNode extends irInsNode {
    public String resId, tp, ptrId;
    public List<Integer> ls;

    public getEleNode(String resId, String tp, String ptrId) {
        this.resId = resId;
        this.tp = tp;
        this.ptrId = ptrId;
        ls = new ArrayList<>();
    }
    public void addLs(int x) {
        ls.add(x);
    }
    @Override
    public void printIr() {
        System.out.print(resId);
        System.out.print(" =  getelementptr ");
        System.out.print(tp);
        System.out.print(", ptr ");
        System.out.print(ptrId);
        for (int i = 0; i < ls.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print("i32 ");
            System.out.print(ls.get(i));
        }
        System.out.println("");
    }
}
