package emitter;
import java.io.*;

/**
 * Emits assembly code.
 * @author Tarush Gupta
 * @version 11/9/25
 */
public class Emitter
{
    private PrintWriter out;
    private int labelCounter;

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
    }
    
    /**
     * Pops a register off the stack.
     * @param reg the register
     */
    public void emitPop(String reg)
    {
        emit("lw " + reg + " ($sp)");
        emit("addu $sp $sp 4");   
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
}
