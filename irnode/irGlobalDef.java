package irnode;

public class irGlobalDef {
    public irType pointTp;
    public irRegister glbVar;
    public irEntity initVal;

    public irGlobalDef(irRegister glbVar, irType pointTp, irEntity initVal) {
        this.glbVar = glbVar;
        this.pointTp = pointTp;
        this.initVal = initVal;
    }
    public void accept(irVisitor visitor) {visitor.visit(this);}
    public String printIr() {
        String s = glbVar.printIr() + " =  global " + irFuncNode.printType(initVal.tp) + " " + initVal.printIr() + "\n";
        return s;
    }
}