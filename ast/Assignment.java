package ast;

import environment.*;
import emitter.*;
/**
 * Assigns a value to a variable.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Creates a new assignment.
     * @param v the variable
     * @param e the expression
     */
    public Assignment(String v, Expression e)
    {
        var = v;
        exp = e;
    }
    
    /**
     * Executes the assignment.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }
    
    /**
     * Compiles the assignment.
     * @param e the emitter 
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("la $t0 var" + var);
        e.emit("sw $v0 ($t0)");
    }
}
