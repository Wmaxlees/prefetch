package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.Operand;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.memory.MemoryManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return memoryManager.getMemoryValue(this.getAddress(memoryManager)).value;
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
        Pattern pattern = Pattern.compile("[-+]*[0-9]*\\(%[a-zA-Z]+\\)");
        Matcher matcher = pattern.matcher(this.token);

        Pattern pattern2 = Pattern.compile("\\d*(\\w*,%?\\w*,\\w*)");
        Matcher matcher2 = pattern2.matcher(this.token);

        int offset = 0;
        int multiplier = 1;
        String val = "";

        if (matcher.matches()) {
            if (!token.startsWith("(")) {
                String[] tokens = token.split("\\(");
                offset = Integer.parseInt(tokens[0]);
                val = "(" + tokens[1];
            }

            val = val.substring(1, val.length()-1);
        } else if (matcher2.matches()) {
            String[] split = this.token.split("\\(|,|\\)");
            offset = Integer.parseInt(split[0]);
            val = split[2];
            multiplier = Integer.parseInt(split[3]);
        } else {
            String[] tokens = token.split(":");
            val = tokens[0];
            offset = Integer.parseInt(tokens[1]);
        }

        return (memoryManager.getRegisterValue(val)*multiplier) + offset;
    }

    public int getAddress(MemoryManager memoryManager) {
        return this.dereference(memoryManager);
    }

    public String toString() {
        return ("(" + this.flag + ": " + this.token +")");
    }

    private OperandFlag flag;
    private String token;
}
