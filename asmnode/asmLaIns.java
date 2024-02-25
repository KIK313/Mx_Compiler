package asmnode;

public class asmLaIns extends asmInsNode {
    public String name, res;
    public asmLaIns(String name, String res) {
        this.res = res;
        this.name = name;
    }
    @Override
    public String printASM() {
        String s = "la " + res + ", " + name + "\n";
        return s;
    }
}
