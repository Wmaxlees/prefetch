package cse.ucdenver.csci5593;

import cse.ucdenver.csci5593.core.Core;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.Parser;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Parser parser = new Parser();

        MemoryManager mm = new MemoryManager();
        // TODO: Add memory modules

        Core core = new Core() {
            @Override
            public void instructions(List<Instruction> inst) {
                int clock = 0;

                for (Instruction i : inst) {
                    // Increment the clock
                    clock += i.CPI(mm);

                    // TODO: Handle memory stuff
                }
            }
        };

        core.instructions(parser.parseFile("test.asm"));

    }
}
