package ast;
import utl.position;
import utl.Type;
import irnode.*;
abstract public class expressionNode extends ASTNode {
    public Type nodeType;
    public irEntity irVal = null;
    public irRegister irPtr = null;
    public expressionNode(position pos) {
        super(pos);
    }
    abstract public void accept(ASTVisitor visitor);
}
