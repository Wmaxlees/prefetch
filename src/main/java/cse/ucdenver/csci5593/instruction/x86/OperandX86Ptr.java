package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.Operand;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.memory.MemoryManager;

/**
 * Created by max on 4/7/16.
 */
public class OperandX86Ptr implements Operand {
    /**
     * Creates a new OperandX86 object with the given flag and value
     *
     * @param value The value of the operand
     */
    public OperandX86Ptr(String value) {
        this.flag = OperandFlag.pointer;
        this.token = value;
    }

    /**
     * Returns the flag
     *
     * @return The flag
     */
    @Override
    public OperandFlag getFlag() {
        return this.flag;
    }

    /**
     * Returns the value
     *
     * @return The value
     */
    @Override
    public int getValue(MemoryManager memoryManager) {
        return this.dereference(memoryManager);
    }

    @Override
    public void setValue(int value) {
        System.err.println("Attempting to set value of pointer");
    }

    @Override
    public boolean isType(OperandFlag flag) {
        return this.flag.equals(flag);
    }

    /**
     * Get rid of the pointer symbol
     *
     * @param memoryManager The memory manager of the executing thread
     * @return A string that's the value
     */
    private int dereference(MemoryManager memoryManager) {
        int offset = 0;
        String val = "";

        if (!token.startsWith("(")) {
            String[] tokens = token.split("\\(");
            offset = Integer.parseInt(tokens[0]);
            val = "(" + tokens[1];
        }

        val = val.substring(1, val.length()-1);

        return memoryManager.getMemoryValue(memoryManager.getRegisterAddress(val)).value + offset;
    }

    public String toString() {
        return ("(" + this.flag + ": " + this.token +")");
    }

    private OperandFlag flag;
    private String token;
}
