package irnode;
import java.util.ArrayList;
import java.util.List;
public class irProgramNode {
    public irMainNode mainNd;
    public List<irFuncNode> funcLis;
    public List<irGlobalVar> glbVarLis;
    public List<irClassDef> classLis;
    public irProgramNode() {
        mainNd = new irMainNode();
        funcLis = new ArrayList<>();
        glbVarLis = new ArrayList<>();
        classLis = new ArrayList<>();
    }
    public void appendFunc(irFuncNode nd) {
        funcLis.add(nd);
    }
    public void appendVar(irGlobalVar nd) {
        glbVarLis.add(nd);
    }
    public void appendClass(irClassDef nd) {
        classLis.add(nd);
    }
    public void printIr() {
        for (irGlobalVar glbVarLi : glbVarLis) {
            glbVarLi.printIr();
        }
        for (irClassDef classLi : classLis) {
            classLi.printIr();
        }
        for (irFuncNode funcLi : funcLis) {
            funcLi.printIr();
        }
        mainNd.printIr();
    }
}
