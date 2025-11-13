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
public class ProcedureDeclaration extends Statement
{
    private String id;
    private List<String> parameters;
    private Statement stmt;
    
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
     * Gets the parameters.
     * @return the parameters
     */
    public List<String> getParameters()
    {
        return parameters;
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
     */
    public void compile(Emitter e)
    {
        //we dont have procedures yet, this is for subroutines
    }
}
