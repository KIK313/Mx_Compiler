package irnode;

import java.util.List;

public class callVoidNode extends irInsNode {
    public String funcName;
    public List<irType> arType;
    public List<String> arName;
    public callVoidNode(String name, List<irType> arType, List<String> arName) {
        this.funcName = name;
        this.arType = arType;
        this.arName = arName;
    }

    @Override
    public void printIr() {
        System.out.print("call void @");
        System.out.print(funcName);
        System.out.print("(");
        for (int i = 0; i < arType.size(); i++) {
            if (i > 0) System.out.print(", ");
            irFuncNode.printType(arType.get(i));
            System.out.print(arName.get(i));
        }
        System.out.println(")");
    }
}
