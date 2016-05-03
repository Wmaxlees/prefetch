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

    /**
     * Should be called every tick.
     *
     * @return Whether the core is continuing to run
     */
    boolean update();


    /**
     * Execute any initialization needed before the core can run
     *
     */
    void initialize();

    /**
     * The total amount of cycles the core ran
     *
     * @return
     */
    int getRuntime();

    /**
     * Set the memory manager for the core
     *
     * @param mm The memory manager
     */
    void setMemoryManager(MemoryManager mm);


    /**
     * Create a core that will share the L3 cache
     * and main memory with the current core
     *
     * @return The helper core
     */
    Core generateHelperCore();

    /**
     * Create a string that contains data about
     * runtimes, hits, misses, etc.
     *
     * @return A string containing the core's
     *         statistics
     */
    String getStatistics();
}
