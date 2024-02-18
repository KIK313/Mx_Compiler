package asmnode;

public class asmCallFuncNode extends asmInsNode{
    String FuncName;
    public asmCallFuncNode(String FuncName) {
        this.FuncName = FuncName;
    }
    @Override
    public String printASM() {
        String s = "";
        return s;
    }
}