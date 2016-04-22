package cse.ucdenver.csci5593.core;

import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.MemoryManager;

import java.util.HashMap;
import java.util.Queue;

/**
 * Created by willi on 3/14/2016.
 */
public interface Core {
    /**
     * Gives the instructions to execute
     *
     * @param list The list of instructions to execute
     */
    void setInstruction(HashMap<Integer, Instruction> list);
    boolean update();
    void initialize();
    int getRuntime();
    void setMemoryManager(MemoryManager mm);
}
