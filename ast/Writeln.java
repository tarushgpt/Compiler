package ast;

import emitter.*;
import environment.*;
/**
 * Writes a value.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Makes a Writeln statement.
     * @param exp the expression
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
    
    /**
     * Executes the writeln.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        e.emit("#writeln");
        exp.compile(e);
        e.emit("move $a0, $v0");
        e.emit("li $v0, 1");
        e.emit("syscall");
        
        e.emit("li $a0, 10");
        e.emit("li $v0, 11");
        e.emit("syscall");
    }
}
