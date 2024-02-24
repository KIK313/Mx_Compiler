package asmnode;

public class asmCallFuncIns extends asmInsNode{
    String FuncName;
    public asmCallFuncIns(String FuncName) {
        this.FuncName = FuncName;
    }
    @Override
    public String printASM() {
        String s = "call " + FuncName + "\n";
        return s;
    }
}