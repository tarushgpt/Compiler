package ast;

import environment.*;
import emitter.*;

/**
 * Makes a while.
 * 
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class While extends Statement
{
    private Condition condition;
    private Statement body;
    
    /**
     * Makes a while.
     * @param condition the condition to evaluate
     * @param body the stuff to do
     */
    public While(Condition condition, Statement body)
    {
        this.condition = condition;
        this.body = body;
    }
    
    /**
     * Executes the while.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        while (condition.eval(env))
        {
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
        e.emit("#while loop");
        String startLabel = "startwhile" + labelID;
        String endLabel = "endwhile" + labelID;
        e.emit(startLabel + ":");
        condition.compile(e, endLabel);
        body.compile(e);
        e.emit("j " + startLabel);
        e.emit(endLabel + ":");
    }
}
