package ast;

import environment.*;
import java.util.Scanner;
import emitter.*;
/**
 * Reads a value from the user.
 * 
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Readln extends Statement
{
    private String varName;
    
    /**
     * Makes a Readln statement.
     * @param varName the variable name
     */
    public Readln(String varName)
    {
        this.varName = varName;
    }
    
    /**
     * Executes the Readln statement.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        System.out.print("Enter value for " + varName + ": ");
        Scanner inputScanner = new Scanner(System.in);
        int value = inputScanner.nextInt();
        env.setVariable(varName, value);
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        e.emit("#readln");
        e.emit("li $v0, 5");
        e.emit("syscall");
        
        if (e.isLocalVariable(varName))
        {
            int offset = e.getOffset(varName);
            e.emit("sw $v0 " + offset + "($sp)");
        }
        else
        {
            e.emit("la $t0 var" + varName);
            e.emit("sw $v0 ($t0)");
        }
    }
}
