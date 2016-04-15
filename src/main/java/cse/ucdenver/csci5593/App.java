package cse.ucdenver.csci5593;

import cse.ucdenver.csci5593.core.BasicCoreImpl;
import cse.ucdenver.csci5593.core.Core;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.FIFOCache;
import cse.ucdenver.csci5593.memory.InfiniteMainMemory;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.Parser;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class App 
{
    public static void main( String[] args )
    {
        Parser parser = new Parser(new X86InstructionSet());
        Queue<Instruction> inst = parser.parseFile("test.s");

        MemoryManager mm = new MemoryManager();
        mm.addModule(0, new FIFOCache(20, 5));
        mm.addModule(1, new FIFOCache(60, 10));
        mm.addModule(2, new FIFOCache(200, 20));
        mm.addModule(3, new InfiniteMainMemory()); // Add main memory

        Core core = new BasicCoreImpl();
        core.setInstruction(inst);
        core.setMemoryManager(mm);

        while (core.update()) {
            mm.update();
        }

    }
}
