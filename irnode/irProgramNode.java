package irnode;
import java.util.ArrayList;
import java.util.List;
public class irProgramNode {
    public irFuncNode mainNd;
    public List<irFuncNode> funcLis;
    public List<irGlobalDef> glbVarLis;
    public List<irClassNode> classLis;
    public List<irGlobalStringConst> strLis;
    public irProgramNode() {
        mainNd = null;
        funcLis = new ArrayList<>();
        glbVarLis = new ArrayList<>();
        classLis = new ArrayList<>();
        strLis = new ArrayList<>();
    }
    public void accept(irVisitor visitor) {visitor.visit(this);}
    public void appendFunc(irFuncNode nd) {
        funcLis.add(nd);
    }
    public void appendVar(irGlobalDef nd) {
        glbVarLis.add(nd);
    }
    public void appendClass(irClassNode nd) {
        classLis.add(nd);
    }
    public String printIr() {
        String s = "";
        s += "declare void @print(ptr)\n";
        s += "declare void @println(ptr)\n";
        s += "declare void @printInt(i32)\n";
        s += "declare void @printlnInt(i32)\n";
        s += "declare ptr @getString()\n";
        s += "declare i32 @getInt()\n";
        s += "declare ptr @toString(i32)\n";
        s += "declare i32 @string.length(ptr)\n";
        s += "declare ptr @string.substring(ptr,i32,i32)\n";
        s += "declare i32 @string.parseInt(ptr)\n";
        s += "declare i32 @string.ord(ptr,i32)\n";
        s += "declare ptr @string.add(ptr,ptr)\n";
        s += "declare i1 @string.eq(ptr,ptr)\n";
        s += "declare i1 @string.neq(ptr,ptr)\n";
        s += "declare i1 @string.lt(ptr,ptr)\n";
        s += "declare i1 @string.le(ptr,ptr)\n";
        s += "declare i1 @string.gt(ptr,ptr)\n";
        s += "declare i1 @string.ge(ptr,ptr)\n";
        s += "declare i32 @array.size(ptr)\n";
        s += "declare ptr @malloc(i32)\n";
        for (var u : strLis) s += u.printIr();
        for (var u : glbVarLis) s += u.printIr();
        for (var u : funcLis) s += u.printIr();
        s += mainNd.printIr();
        return s;
//        for (irGlobalVar glbVarLi : glbVarLis) {
//            glbVarLi.printIr();
//        }
//        for (irClassDef classLi : classLis) {
//            classLi.printIr();
//        }
//        for (irFuncNode funcLi : funcLis) {
//            funcLi.printIr();
//        }
//        mainNd.printIr();
    }
}
