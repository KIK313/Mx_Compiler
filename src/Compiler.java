package src;
import java.io.InputStream;
import java.io.FileInputStream;

import ast.ProgramNode;
import frontend.SemanticChecker;
import frontend.SymbolCollector;
import utl.error.error;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import parser.*;
import utl.mxErrorListener;
import frontend.ASTBuilder;
import utl.globalScope;
import ir.*;
import asm.asmBuilder;
import asmnode.*;
public class Compiler {
    public static void main(String[] args) throws Exception {
    //    String name = "src/test.mx";
    //    InputStream input = new FileInputStream(name);
        InputStream input = System.in;
        try {
            mxLexer lexer = new mxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new mxErrorListener());
            mxParser parser = new mxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new mxErrorListener());
            ParseTree parseTreeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder();
            ProgramNode nd = (ProgramNode) astBuilder.visit(parseTreeRoot);
            globalScope gScope = new globalScope(null);
            new SymbolCollector(gScope).visit(nd);
            new SemanticChecker(gScope).visit(nd);
            IRBuilder irTree = new IRBuilder();
            irTree.visit(nd);
     //       System.out.println(irTree.pr.printIr());
            asmBuilder asmTree = new asmBuilder();
            asmTree.visit(irTree.pr);
            System.out.println(asmTree.pr.printASM());
        } catch (error er) {
            System.err.println(er.toString());
            throw new RuntimeException();
        }
        return;
    }
}
