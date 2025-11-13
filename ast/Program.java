package ast;

import java.util.*;
import emitter.*;
import environment.*;
/**
 * Makes a program.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Program
{
    private VarDeclaration varDecl;
    private List<ProcedureDeclaration> procedures;
    private Statement stmt;
    
    /**
     * Makes a program.
     * @param varDecl the variable declaration
     * @param procedures the procedures
     * @param stmt the statement
     */
    public Program(VarDeclaration varDecl, List<ProcedureDeclaration> procedures, Statement stmt)
    {
        this.varDecl = varDecl;
        this.procedures = procedures;
        this.stmt = stmt;
    }
    
    /**
     * Executes.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        for (ProcedureDeclaration procedure : procedures)
        {
            procedure.exec(env);
        }
        stmt.exec(env);
    }
    
    /**
     * Compiles.
     * @param filename the output file name
     */
    public void compile(String filename)
    {
        Emitter e = new Emitter(filename);
        
        e.emitHeader("Tarush Gupta");
        
        e.emit(".data");
        Map<String, Integer> variables = varDecl.getVariables();
        for (Map.Entry<String, Integer> entry : variables.entrySet())
        {
            e.emit("var" + entry.getKey() + ": .word " + entry.getValue());
        }
        e.emit("");
        
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main:");
        stmt.compile(e);
        e.emit("li $v0, 10");
        e.emit("syscall");
        e.close();
    }
}
