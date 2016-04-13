package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.Operand;
import cse.ucdenver.csci5593.instruction.OperandFlag;

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
        this.dereference(value);
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
    public int getValue() {
        return this.value;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public boolean isType(OperandFlag flag) {
        return this.flag.equals(flag);
    }

    /**
     * Get rid of the pointer symbol
     *
     * @param token The token to remove the pointer from
     * @return A string that's the value
     */
    private void dereference(String token) {
        String val = token;

        if (!token.startsWith("(")) {
            String[] tokens = val.split("\\(");
            this.offset = Integer.getInteger(tokens[0]);

            val = "(" + tokens[1];
        }

        val = val.substring(1, val.length()-2);

        this.value = Integer.getInteger(val);
    }

    private OperandFlag flag;
    private int value;
    private int offset;
}
