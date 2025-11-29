package emitter;
import java.io.*;
import ast.ProcedureDeclaration;

/**
 * Emits assembly code.
 * @author Tarush Gupta
 * @version 11/9/25
 */
public class Emitter
{
    private PrintWriter out;
    private int labelCounter;
    private ProcedureDeclaration currentProcedure;
    private int excessStackHeight;

    /**
     * Makes an emitter.
     * @param outputFileName the ouput file name
     */
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
            labelCounter = 0;
            currentProcedure = null;
            excessStackHeight = 0;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Creates a header.
     * @param author the author
     */
    public void emitHeader(String author)
    {
        java.time.LocalDate date = java.time.LocalDate.now(); //isnt this cool? 
        out.println("# @author " + author);
        out.println("# @date " + date);
        out.println();
    }

    /**
     * Emits.
     * @param code the code
     */
    public void emit(String code)
    {
        if (!code.endsWith(":"))
        {
            code = "\t" + code;
        }
        out.println(code);
    }

    /**
     * Closes.
     */
    public void close()
    {
        out.close();
    }
    
    /**
     * Pushes a register to the stack.
     * @param reg the register
     */
    public void emitPush(String reg)
    {
        emit("subu $sp $sp 4");
        emit("sw " + reg + " ($sp)");
        excessStackHeight += 4;
    }
    
    /**
     * Pops a register off the stack.
     * @param reg the register
     */
    public void emitPop(String reg)
    {
        emit("lw " + reg + " ($sp)");
        emit("addu $sp $sp 4");
        excessStackHeight -= 4;
    }
    
    /**
     * Gets the next label id.
     * @return the next label id
     */
    public int nextLabelID()
    {
        labelCounter++;
        return labelCounter;
    }
    
    /**
     * Remember proc as current procedure context.
     * @param proc the procedure declaration
     */
    public void setProcedureContext(ProcedureDeclaration proc)
    {
        currentProcedure = proc;
        excessStackHeight = 0;
    }
    
    /**
     * Clear current procedure context (remember null).
     */
    public void clearProcedureContext()
    {
        currentProcedure = null;
        excessStackHeight = 0;
    }
    
    /**
     * Checks if a variable is local.
     * @param varName the variable name
     * @return true if local, false otherwise
     */
    public boolean isLocalVariable(String varName)
    {
        if (currentProcedure == null)
        {
            return false;
        }
        
        if (varName.equals(currentProcedure.getName()))
        {
            return true;
        }
        
        for (String param : currentProcedure.getParameters())
        {
            if (param.equals(varName))
            {
                return true;
            }
        }
        
        for (String localVar : currentProcedure.getLocalVariables())
        {
            if (localVar.equals(varName))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Gets the offset of a local variable from $sp.
     * @param localVarName the local variable name
     * @return the offset
     */
    public int getOffset(String localVarName)
    {
        if (currentProcedure == null)
        {
            throw new RuntimeException("No procedure context set");
        }
        
        int numLocalVars = currentProcedure.getLocalVariables().size();
        int numParams = currentProcedure.getParameters().size();
        
        if (localVarName.equals(currentProcedure.getName()))
        {
            return excessStackHeight + 4 * numLocalVars + 4 * numParams;
        }
        
        for (int i = 0; i < numLocalVars; i++)
        {
            if (currentProcedure.getLocalVariables().get(i).equals(localVarName))
            {
                return excessStackHeight + 4 * numParams + 4 * (numLocalVars - i - 1);
            }
        }
        
        for (int i = 0; i < numParams; i++)
        {
            if (currentProcedure.getParameters().get(i).equals(localVarName))
            {
                return excessStackHeight + 4 * (numParams - i - 1);
            }
        }
        
        throw new RuntimeException("Local variable " + localVarName + " not found");
    }
}
