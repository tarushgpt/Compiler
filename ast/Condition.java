package ast;

import environment.*;
import emitter.*;

/**
 * Creates a condition.
 * 
 * @author Tarush Gupta
 * @version 1/12/25
 */
public class Condition
{
    private Expression left;
    private String relop;
    private Expression right;
    
    /**
     * Makes a condition.
     * @param left the left expression
     * @param relop =, <>, <, >, <=, >=
     * @param right the right expression
     */
    public Condition(Expression left, String relop, Expression right)
    {
        this.left = left;
        this.relop = relop;
        this.right = right;
    }
    
    /**
     * Evaluates the condition.
     * @param env the environment
     * @return true if satisfied, else false
     */
    public boolean eval(Environment env)
    {
        int leftVal = left.eval(env);
        int rightVal = right.eval(env);
        
        switch (relop)
        {
            case "=":
                return leftVal == rightVal;
            case "<>":
                return leftVal != rightVal;
            case "<":
                return leftVal < rightVal;
            case ">":
                return leftVal > rightVal;
            case "<=":
                return leftVal <= rightVal;
            case ">=":
                return leftVal >= rightVal;
            default:
                throw new RuntimeException("don't know what ts is: " + relop);
        }
    }
    
    /**
     * Compiles. 
     * @param e the emitter
     * @param targetLabel branch here if false
     */
    public void compile(Emitter e, String targetLabel)
    {
        left.compile(e);
        e.emitPush("$v0");
        right.compile(e);
        e.emitPop("$t0");
        
        switch (relop)
        {
            case "=":
                e.emit("bne $t0 $v0 " + targetLabel);
                return;
            case "<>":
                e.emit("beq $t0 $v0 " + targetLabel);
                return;
            case "<":
                e.emit("bge $t0 $v0 " + targetLabel);
                return;
            case ">":
                e.emit("ble $t0 $v0 " + targetLabel);
                return;
            case "<=":
                e.emit("bgt $t0 $v0 " + targetLabel);
                return;
            case ">=":
                e.emit("blt $t0 $v0 " + targetLabel);
                return;
            default:
                throw new RuntimeException("what is this bro " + relop);
        }
    }
}
