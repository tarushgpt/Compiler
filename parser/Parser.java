package parser;
import scanner.*;
import java.util.Map;
import java.util.HashMap;
import ast.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Parses Scanner output using a recursive descent leftmost parse tree.
 *
 * @author Tarush Gupta
 * @version 11/9/25
 */
public class Parser
{
    private Scanner scanner;
    private String currToken;
    
    /**
     * Constructs a parser object.
     * @param s the scanner
     */
    public Parser(Scanner s) throws ScanErrorException
    {
        scanner = s;
        currToken = s.nextToken();
    }

    /**
     * Eats a token.
     * @param expected the expected token to be eaten
     */
    private void eat(String expected) throws ScanErrorException
    {
        if (currToken.equals(expected))
        {
            currToken = scanner.nextToken();
            return;
        }
        throw new IllegalArgumentException("expected " + currToken + " but found " + expected);
    }
    
    /**
     * Parses a number.
     * @return the number
     */
    private ast.Number parseNumber() throws ScanErrorException
    {
        ast.Number num = new ast.Number(Integer.parseInt(currToken));
        eat(currToken);
        return num;
    }
    
    /**
     * Parses a factor.
     * @return the factor
     */
    private Expression parseFactor() throws ScanErrorException
    {
        if (currToken.equals("-"))
        {
            eat("-");
            Expression rhs = parseFactor();
            return new BinOp("*", new ast.Number(-1), rhs);
        }
        else if (currToken.equals("("))
        {
            eat("(");
            Expression num = parseExpression();
            eat(")");
            return num;
        }
        
        try 
        {
            return parseNumber();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        String name = currToken;
        eat(currToken);
        if (currToken.equals("("))
        {
            eat("(");
            List<Expression> arguments = parseArguments();
            eat(")");
            return new ProcedureCall(name, arguments);
        }
        return new Variable(name);
    }
    
    /**
     * Parses a term.
     * @return the term
     */
    private ast.Expression parseTerm() throws ScanErrorException
    {
        ast.Expression factor = parseFactor();
        while (currToken.equals("*") | currToken.equals("/"))
        {
            if (currToken.equals("*"))
            {
                eat("*");
                factor = new BinOp("*", factor, parseFactor());
            }
            else 
            {
                eat("/");
                factor = new BinOp("/", factor, parseFactor());
            }
        }
        return factor;
    }
    
    /**
     * Parses an expression.
     * @return the expression value
     */
    private Expression parseExpression() throws ScanErrorException
    {
        ast.Expression expression = parseTerm();
        while (currToken.equals("+") | currToken.equals("-"))
        {
            if (currToken.equals("+"))
            {
                eat("+");
                expression = new BinOp("+", expression, parseTerm());
            }
            else 
            {
                eat("-");
                expression = new BinOp("-", expression, parseTerm());
            }
        }
        return expression;
    }
    
    /**
     * Parses a condition (expr relop expr).
     * @return the condition
     */
    private Condition parseCondition() throws ScanErrorException
    {
        Expression left = parseExpression();
        String relop = currToken;
 
        if (relop.equals("=") || relop.equals("<>") || relop.equals("<") || 
            relop.equals(">") || relop.equals("<=") || relop.equals(">="))
        {
            eat(relop);
            Expression right = parseExpression();
            return new Condition(left, relop, right);
        }
        else
        {
            throw new IllegalArgumentException("Expected relop but found: " + relop);
        }
    }
    
    /**
     * Parses multiple statements.
     * @return the block.
     */
    public Block parseStatements() throws ScanErrorException
    {
        List<Statement> stmts = new ArrayList<Statement>();
    
        while (!currToken.equals("END"))
        {
            stmts.add(parseStatement()); 
        }
        
        return new Block(stmts);
    }
    
    /**
     * Parses a single statement.
     * @return the statement.
     */
    public Statement parseStatement() throws ScanErrorException
    {
        if (currToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression e = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(e);
        }
        else if (currToken.equals("BEGIN"))
        {
            eat("BEGIN");
            Block b = parseStatements();
            eat("END");
            eat(";");
            return b;
        }
        else if (currToken.equals("IF"))
        {
            eat("IF");
            Condition cond = parseCondition();
            eat("THEN");
            Statement thenStmt = parseStatement();

            if (currToken.equals("ELSE"))
            {
                eat("ELSE");
                Statement elseStmt = parseStatement();
                return new IfElse(cond, thenStmt, elseStmt);
            }
            else
            {
                return new If(cond, thenStmt);
            }
        }
        else if (currToken.equals("WHILE"))
        {
            eat("WHILE");
            Condition cond = parseCondition();
            eat("DO");
            Statement body = parseStatement();
            return new While(cond, body);
        }
        else if (currToken.equals("READLN"))
        {
            eat("READLN");
            eat("(");
            String varName = currToken;
            eat(varName);
            eat(")");
            eat(";");
            return new Readln(varName);
        }
        else if (currToken.equals("FOR"))
        {
            eat("FOR");
            String varName = currToken;
            eat(varName);
            eat(":=");
            Expression startExpr = parseExpression();
            eat("TO");
            Expression endExpr = parseExpression();
            eat("DO");
            Statement body = parseStatement();
            return new For(varName, startExpr, endExpr, body);
        }
        else if (currToken.equals("EOF"))
        {
            return null;
        }
        else
        {
            String id = currToken;
            eat(currToken);
            eat(":=");
            Expression e = parseExpression();
            Assignment a = new Assignment(id, e);
            eat(";");
            return a;
        }
    }   
    
    private List<String> parseParameters() throws ScanErrorException
    {
        List<String> parameters = new ArrayList<String>();
        
        if (!currToken.equals(")")) 
        {
            parameters.add(currToken);
            eat(currToken);
            
            while (currToken.equals(","))
            {
                eat(",");
                parameters.add(currToken);
                eat(currToken);
            }
        }
        
        return parameters;
    }
    
    /**
     * Parses the arguments.
     * @return the arguments
     */
    private List<Expression> parseArguments() throws ScanErrorException
    {
        List<Expression> arguments = new ArrayList<Expression>();
        
        if (!currToken.equals(")")) 
        {
            arguments.add(parseExpression());
            
            while (currToken.equals(","))
            {
                eat(",");
                arguments.add(parseExpression());
            }
        }
        
        return arguments;
    }

    /**
     * Parses variable declarations.
     * @return the variable declaration
     */
    private VarDeclaration parseVarDeclarations() throws ScanErrorException
    {
        VarDeclaration varDecl = new VarDeclaration();
        
        while (currToken.equals("VAR"))
        {
            eat("VAR");
            
            String varName = currToken;
            eat(currToken);
            
            if (currToken.equals(":="))
            {
                eat(":=");
                int value = Integer.parseInt(currToken);
                eat(currToken);
                varDecl.addVariable(varName, value);
            }
            else
            {
                varDecl.addVariable(varName);
            }
            
            while (currToken.equals(","))
            {
                eat(",");
                varName = currToken;
                eat(currToken);
                
                if (currToken.equals(":="))
                {
                    eat(":=");
                    int value = Integer.parseInt(currToken);
                    eat(currToken);
                    varDecl.addVariable(varName, value);
                }
                else
                {
                    varDecl.addVariable(varName);
                }
            }
            
            eat(";");
        }
        
        return varDecl;
    }
    
    /**
     * Parses a program.
     * @return the program
     */
    public Program parseProgram() throws ScanErrorException
    {
        VarDeclaration varDecl = parseVarDeclarations();
        
        List<ProcedureDeclaration> procedures = new ArrayList<ProcedureDeclaration>();
        
        while (currToken.equals("PROCEDURE"))
        {
            eat("PROCEDURE");
            String id = currToken;
            eat(currToken);
            eat("(");
            List<String> parameters = parseParameters();
            eat(")");
            eat(";");
            
            List<String> localVars = new ArrayList<String>();
            while (currToken.equals("VAR"))
            {
                eat("VAR");
                localVars.add(currToken);
                eat(currToken);
                
                while (currToken.equals(","))
                {
                    eat(",");
                    localVars.add(currToken);
                    eat(currToken);
                }
                
                eat(";");
            }
            
            Statement procstmt = parseStatement();
            procedures.add(new ProcedureDeclaration(id, parameters, localVars, procstmt));
        }
        
        List<Statement> stmts = new ArrayList<Statement>();
        
        while (!currToken.equals(".") && !currToken.equals("EOF"))
        {
            Statement stmt = parseStatement();
            if (stmt != null)
            {
                stmts.add(stmt);
            }
        }
        
        return new Program(varDecl, procedures, new Block(stmts));
    }
}