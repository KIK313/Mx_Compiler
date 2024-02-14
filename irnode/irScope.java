package irnode;

import java.util.HashMap;

public class irScope {
    public HashMap<String, irRegister> Name2Reg;
    public irScope parentScope = null;
    public irBlock continue2Blk = null;
    public irBlock break2Blk = null;
    public irScope(irScope scope) {
        Name2Reg = new HashMap<>();
        this.parentScope = scope;
    }

    public irRegister findReg(String name) {
        if (Name2Reg.containsKey(name)) return Name2Reg.get(name);
        if (parentScope == null) return null;
        return parentScope.findReg(name);
    }
    public irBlock findContinueBlk() {
        if (continue2Blk == null) return parentScope.findContinueBlk();
        return continue2Blk;
    }
    public irBlock findBreakBlk() {
        if (break2Blk == null) return parentScope.findBreakBlk();
        return break2Blk;
    }
}
