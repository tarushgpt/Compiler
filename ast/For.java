package ast;

import environment.*;
import emitter.*;

/**
 * Creates a FOR statement.
 * 
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class For extends Statement
{
    private String varName;
    private Expression startExpr;
    private Expression endExpr;
    private Statement body;
    
    /**
     * Makes a For statement.
     * @param varName the variable name
     * @param startExpr starting expression
     * @param endExpr ending expression
     * @param body the stuff to do
     */
    public For(String varName, Expression startExpr, Expression endExpr, Statement body)
    {
        this.varName = varName;
        this.startExpr = startExpr;
        this.endExpr = endExpr;
        this.body = body;
    }
    
    /**
     * Executes the FOR statement.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        int start = startExpr.eval(env);
        int end = endExpr.eval(env);
        
        for (int i = start; i <= end; i++)
        {
            env.setVariable(varName, i);
            body.exec(env);
        }
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        int labelID = e.nextLabelID();
        
        e.emit("#for loop");
        
        String startLabel = "startfor" + labelID;
        String endLabel = "endfor" + labelID;
        

        startExpr.compile(e);
        e.emit("la $t0 var" + varName);
        e.emit("sw $v0 ($t0)");
        
        e.emit(startLabel + ":");
        
        e.emit("la $t0 var" + varName);
        e.emit("lw $v0 ($t0)");
        e.emit("subu $sp $sp 4"); 
        e.emit("sw $v0 ($sp)");
        
        endExpr.compile(e);
        e.emit("lw $t0 ($sp) #pop loop variable");
        e.emit("addu $sp $sp 4");
        
        e.emit("bgt $t0 $v0 " + endLabel);
        
        body.compile(e);
        
        e.emit("la $t0 var" + varName);
        e.emit("lw $v0 ($t0)");
        e.emit("addi $v0 $v0 1");
        e.emit("la $t0 var" + varName);
        e.emit("sw $v0 ($t0)");
        
        e.emit("j " + startLabel);
        
        e.emit(endLabel + ":");
    }
}
