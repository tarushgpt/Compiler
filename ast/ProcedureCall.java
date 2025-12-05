package ast;

import environment.*;
import emitter.*;
import java.util.List;
/**
 * Calls a procedure.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class ProcedureCall extends Expression
{
    private String name;
    private List<Expression> arguments;
    
    /**
     * Makes a procedure call.
     * @param name the name
     * @param arguments the arguments
     */
    public ProcedureCall(String name, List<Expression> arguments)
    {
        this.name = name;
        this.arguments = arguments;
    }
    
    /**
     * Evaluates.
     * @param env the environment
     * @return the val
     */
    public int eval(Environment env)
    {
        ProcedureDeclaration procedure = env.getProcedure(name);
        List<String> parameters = procedure.getParameters();
        if (arguments.size() != parameters.size()) 
        {
            throw new RuntimeException("Procedure " + name + " expects " + parameters.size() + " arguments but got " + arguments.size());
        }
        Environment procedureEnv = new Environment(env.getGlobal());
        procedureEnv.declareVariable(name, 0);
        for (int i = 0; i < arguments.size(); i++) 
        {
            int argValue = arguments.get(i).eval(env);
            String paramName = parameters.get(i);
            procedureEnv.declareVariable(paramName, argValue);
        }
        Statement stmt = procedure.getStatement();
        stmt.exec(procedureEnv);
        return procedureEnv.getVariable(name);
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        e.emit("#procedure call: " + name);

        e.emitPush("$ra");
        
        for (Expression arg : arguments)
        {
            arg.compile(e);
            e.emitPush("$v0");
        }
        
        e.emit("jal proc" + name);
        
        for (int i = 0; i < arguments.size(); i++)
        {
            e.emitPop("$t0");
        }
        
        e.emitPop("$ra");
    }
}
