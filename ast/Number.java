package ast;

import environment.*;
import emitter.*;
/**
 * Makes a number.
 *
 * @author Tarush Gupta
 * @version 1/12/25
 */
public class Number extends Expression
{
    private int value;
    
    /**
     * Makes a number.
     * @param val the value
     */
    public Number(int val)
    {
        value = val;
    }
    
    /**
     * Evaluates.
     * @param e the environment
     * @return the value
     */
    public int eval(Environment e)
    {
        return value;
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        e.emit("li $v0, " + value);
    }
}
