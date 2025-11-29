package ast;

import environment.*;
import emitter.*;
/**
 * Does a binary operation.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;
    
    /**
     * Creates a new binary operation.
     * @param o the operator
     * @param one the first expression
     * @param two the second expression
     */
    public BinOp(String o, Expression one, Expression two)
    {
        op = o;
        exp1 = one;
        exp2 = two;
    }
    
    /**
     * Evaluates the binary operation.
     * @param env the environment
     * @return the result
     */
    public int eval(Environment env)
    {
        int val1 = exp1.eval(env);
        int val2 = exp2.eval(env);
        
        if (op.equals("+"))
        {
            return val1 + val2;
        }
        if (op.equals("-"))
        {
            return val1 - val2;
        }
        if (op.equals("*"))
        {
            return val1 * val2;
        }
        if (op.equals("/"))
        {
            return val1 / val2;
        }
        throw new RuntimeException("Unexpected operator");
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        e.emit("#binary operand");
        exp1.compile(e);
        e.emitPush("$v0");
        exp2.compile(e);
        e.emitPop("$t0");
        if (op.equals("+"))
        {
            e.emit("addu $v0, $t0, $v0");
        }
        if (op.equals("-"))
        {
            e.emit("subu $v0, $t0, $v0");
        }
        if (op.equals("*"))
        {
            e.emit("mul $v0, $v0, $t0");
        }
        if (op.equals("/"))
        {
            e.emit("div $t0, $v0");
            e.emit("mflo $v0");
        }
    }
}
