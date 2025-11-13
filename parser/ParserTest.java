package parser;
import scanner.*;
import java.io.*;
import environment.*;
import ast.*;
import emitter.*;
import java.util.ArrayList;
/**
 * Test the Parser class.
 * 
 * @author  Tarush Gupta
 * @version 11/9/25
 */
public class ParserTest 
{
    /* ParserTest methods: */

    /**
     * Tests the Compiler.
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        try
        {
            String test0 = "/Users/tarushgupta/Compiler/parserTest0.txt"; //3
            String test1 = "/Users/tarushgupta/Compiler/parserTest1.txt"; //4 9 1
            String test2 = "/Users/tarushgupta/Compiler/parserTest2.txt"; //14 10 20
            String test3 = "/Users/tarushgupta/Compiler/parserTest3.txt"; //1 2 3
            String test4_5 = "/Users/tarushgupta/Compiler/parserTest4.5ForLoopReadln.txt";
            String test4 = "/Users/tarushgupta/Compiler/parserTest4.txt"; //15
            String test6 = "/Users/tarushgupta/Compiler/parserTest6.txt"; 
            //this one is 15 5 3 0 1 2 3 4 5 6 7 8 9
            //String test6_5 = "/Users/tarushgupta/Compiler/parserTest6_5.txt"; 
            //this one is 15 5 3 0 1 2 3 4 5 6 7 8 9
            //String test7 = "/Users/tarushgupta/Compiler/parserTest7.txt"; //uses procedures
            //String test8 = "/Users/tarushgupta/Compiler/parserTest8.txt"; //uses procedures
            //String test8_5 = "/Users/tarushgupta/Compiler/parserTest8_5.txt"; //uses procedures
            String test9 = "/Users/tarushgupta/Compiler/parserTest9.txt";
            //15 5 3 4 5 1-15
            String test9_5 = "/Users/tarushgupta/Compiler/parsertest9_5.txt"; //max.txt (i think?)
            //80 10 8 0 2 4 6 1 3 6 10 15 Entervalue(5) 5
            ArrayList<String> tests = new ArrayList<String>();
            tests.add(test0);
            tests.add(test1);
            tests.add(test2);
            tests.add(test3);
            tests.add(test4);
            tests.add(test6);
            tests.add(test9);
            tests.add(test9);
            
            java.util.Scanner inputScanner = new java.util.Scanner(System.in);       
            
            String test = test9_5; //change this to change the test
            
            System.out.println("Testing: " + test + "\n");
            FileInputStream inStream = new FileInputStream(new File(test));
            Scanner scan = new Scanner(inStream);
            Parser parse = new Parser(scan);
            Program program = parse.parseProgram();
                
            System.out.println("Java Execution:\n");
            Environment env = new Environment();
            program.exec(env);
            
            System.out.println("\n");
            
            String asmFile = "testfile.asm"; //change this to change the asm file
        
            try 
            {
                program.compile(asmFile);
                System.out.println("MIPS assembly written to: " + asmFile);
            } 
            catch (Exception compileError) 
            {
                System.out.println("Compilation skipped: " + compileError.getMessage());
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
