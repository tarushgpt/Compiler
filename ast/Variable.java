package ast;

import environment.*;
import emitter.*;
/**
 * Makes a variable.
 *
 * @author Tarush Gupta
 * @version 1/12/25
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Makes a variable.
     * @param n the name
     */
    public Variable(String n)
    {
        name = n;
    }
    
    /**
     * Evaluates the variable.
     * @param env the environment
     * @return the value
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        e.emit("la $t0 var" + name);
        e.emit("lw $v0 ($t0)");
    }
}
