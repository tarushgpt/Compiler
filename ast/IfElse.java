package ast;

import environment.*;
import emitter.*;

/**
 * Makes an f else.
 * 
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class IfElse extends Statement
{
    private Condition condition;
    private Statement thenStmt;
    private Statement elseStmt;
    
    /**
     * Makes an if else.
     * @param condition the condition
     * @param thenStmt the then statement
     * @param elseStmt the else statement
     */
    public IfElse(Condition condition, Statement thenStmt, Statement elseStmt)
    {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }
    
    /**
     * Executes the if else.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        if (condition.eval(env))
        {
            thenStmt.exec(env);
        }
        else
        {
            elseStmt.exec(env);
        }
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        int labelID = e.nextLabelID();
        String elseLabel = "else" + labelID;
        String endLabel = "endif" + labelID;
        condition.compile(e, elseLabel);
        thenStmt.compile(e);
        e.emit("#if else statement");
        e.emit("j " + endLabel);
        e.emit(elseLabel + ":");
        elseStmt.compile(e);
        e.emit(endLabel + ":");
    }
}
