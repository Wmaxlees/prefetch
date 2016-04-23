package cse.ucdenver.csci5593.instruction;

import cse.ucdenver.csci5593.memory.MemoryManager;

/**
 * Created by max on 4/7/16.
 */
public interface Operand {
    /**
     *
     * @return The flag of the operand
     */
    OperandFlag getFlag();

    /**
     * Get the value of the operand
     *
     * @param memoryManager The memory manager
     * @return The actual value of the operand
     */
    int getValue(MemoryManager memoryManager);

    /**
     * Set the value of the operand. Should only be used during parsing
     * @param value The value to set the operand to
     */
    void setValue(int value);

    /**
     * Returns the address of the operand
     *
     * @param memoryManager The memory manager
     * @return The memory location of the operand
     */
    int getAddress(MemoryManager memoryManager);

    /**
     *
     * @param flag The flag to compare to
     * @return True if the two flags are equal
     */
    boolean isType(OperandFlag flag);
}
