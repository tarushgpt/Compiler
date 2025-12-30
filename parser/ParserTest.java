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
    /**
     * Tests the compiler.
     * @param args the arguments
     */
    public static void main(String[] args)
    {
        java.util.Scanner inputScanner = new java.util.Scanner(System.in);
        
        String test0 = "/Users/tarushgupta/Compiler/parserTest0.txt"; //3
        String test1 = "/Users/tarushgupta/Compiler/parserTest1.txt"; //4 9 1
        String test2 = "/Users/tarushgupta/Compiler/parserTest2.txt"; //14 10 20
        String test3 = "/Users/tarushgupta/Compiler/parserTest3.txt"; //1 2 3
        String test4 = "/Users/tarushgupta/Compiler/parserTest4.txt"; //15
        String test45 = "/Users/tarushgupta/Compiler/parserTest4.5ForLoopReadln.txt";
        //input of 1 means: 2 1 2 4 7 11 16 22 29 37 46 56 67 79 79 77
        String test6 = "/Users/tarushgupta/Compiler/parserTest6.txt"; 
        //15 5 3 0 1 2 3 4 5 6 7 8 9
        String test65 = "/Users/tarushgupta/Compiler/parsertest6_5.txt"; 
        //input of 1 means: 6 3 2 0 1 2 3 4 5 6 7 8 9 1 3 6
        String test7 = "/Users/tarushgupta/Compiler/parserTest7.txt";
        //15 5 3 0 1 2 3 4 5 6 7 8 9 10 12
        String test8 = "/Users/tarushgupta/Compiler/parserTest8.txt";
        //15 5 3 0 1 2 3 4 5 6 7 8 9 3 4 10 3 14
        String test85 = "/Users/tarushgupta/Compiler/parsertest8_5.txt"; //7 3 0
        String test9 = "/Users/tarushgupta/Compiler/parserTest9.txt";       
        //15 5 3 4 5 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
        String test95 = "/Users/tarushgupta/Compiler/parsertest9_5.txt";
        //input of 1 means 80 10 8 0 2 4 6 1 3 6 10 15 1
        String test10 = "/Users/tarushgupta/Compiler/parserTest10.txt"; //5 2
        String test11 = "/Users/tarushgupta/Compiler/parserTest11.txt"; //5 3
        String test12 = "/Users/tarushgupta/Compiler/parserTest12.txt"; //9
        String test13 = "/Users/tarushgupta/Compiler/parserTest13.txt"; //2 3 4
        
        String proctest1 = "/Users/tarushgupta/Compiler/parserTestProc1.txt";
        String proctest2 = "/Users/tarushgupta/Compiler/parserTestProc2.txt";
        String proctest3 = "/Users/tarushgupta/Compiler/parserTestProc3.txt";
        String proctest4 = "/Users/tarushgupta/Compiler/parserTestProc4.txt";
        String proctest5 = "/Users/tarushgupta/Compiler/parserTestProc5.txt";
        String proctest6 = "/Users/tarushgupta/Compiler/parserTestProc6.txt";
        String proctest7 = "/Users/tarushgupta/Compiler/parserTestProc7.txt";
        String proctest8 = "/Users/tarushgupta/Compiler/parserTestProc8.txt";
        
        ArrayList<String> tests = new ArrayList<String>();
        /*tests.add(test0);
        tests.add(test1);
        tests.add(test2);
        tests.add(test3);
        tests.add(test4);
        tests.add(test45);
        tests.add(test6);
        tests.add(test65);
        tests.add(test7);
        tests.add(test8);
        tests.add(test85);
        tests.add(test9);
        tests.add(test95);
        tests.add(test10);
        tests.add(test11);
        tests.add(test12);
        tests.add(test13);
        */
        tests.add(proctest1);
        tests.add(proctest2);
        tests.add(proctest3);
        tests.add(proctest4);
        tests.add(proctest5);
        tests.add(proctest6);
        tests.add(proctest7);
        tests.add(proctest8);
           
        int testNum = 0;
        for (String test: tests)
        {
            testNum++;
            System.out.println("Test " + testNum + ": " + test);
            
            try
            {
                FileInputStream inStream = new FileInputStream(new File(test));
                Scanner scan = new Scanner(inStream);
                Parser parse = new Parser(scan);
                Program program = parse.parseProgram();
                
                Environment env = new Environment();
                program.exec(env);
                
                program.compile("testfile.asm");
            }
            catch (Exception e)
            {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println("Output written to testfile.asm.");
            
            System.out.println("\nPress ENTER to continue...");
            inputScanner.nextLine();
        }
        
        System.out.println("All tests completed.");
        inputScanner.close();
    }
}
