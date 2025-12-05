package ast;

import environment.*;
import emitter.*;
import java.util.List;
import java.util.ArrayList;
/**
 * Declares a procedure.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class ProcedureDeclaration
{
    private String id;
    private List<String> parameters;
    private Statement stmt;
    private List<String> localVariables;
    
    /**
     * Declares a procedure.
     * @param id the id
     * @param parameters the parameters
     * @param stmt the statement
     */
    public ProcedureDeclaration(String id, List<String> parameters, Statement stmt)
    {
        this.id = id;
        this.parameters = parameters;
        this.stmt = stmt;
        this.localVariables = new ArrayList<String>();
    }
    
    /**
     * Declares a procedure with local variables.
     * @param id the id
     * @param parameters the parameters
     * @param localVariables the local variables
     * @param stmt the statement
     */
    public ProcedureDeclaration(String id, List<String> parameters, List<String> localVariables, Statement stmt)
    {
        this.id = id;
        this.parameters = parameters;
        this.stmt = stmt;
        this.localVariables = localVariables;
    }
    
    /**
     * Executes.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        env.setProcedure(id, this);
    }
    
    /**
     * Gets the name.
     * @return the name
     */
    public String getName()
    {
        return id;
    }
    
    /**
     * Gets the parameters.
     * @return the parameters
     */
    public List<String> getParameters()
    {
        return parameters;
    }
    
    /**
     * Gets the local variables.
     * @return the local variables
     */
    public List<String> getLocalVariables()
    {
        return localVariables;
    }
    
    /**
     * Gets the statement.
     * @return the statement
     */
    public Statement getStatement()
    {
        return stmt;
    }
    
    /**
     * Compiles.
     * @param e the emitter
     *
     */
    public void compile(Emitter e)
    {
        e.emit("#procedure declaration: " + id);
        e.emit("proc" + id + ":");
        
        e.setProcedureContext(this);
        
        e.emitPush("$zero");
        
        for (String localVar : localVariables)
        {
            e.emitPush("$zero");
        }
        
        stmt.compile(e);
        
        for (int i = 0; i < localVariables.size(); i++)
        {
            e.emitPop("$t0");
        }
        
        e.emitPop("$v0");
        
        e.clearProcedureContext();
        
        e.emit("jr $ra");
    }
}
