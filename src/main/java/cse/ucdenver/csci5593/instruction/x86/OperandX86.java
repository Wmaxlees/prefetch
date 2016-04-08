package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.Operand;
import cse.ucdenver.csci5593.instruction.OperandFlag;

/**
 * Created by max on 4/7/16.
 */
public class OperandX86 implements Operand {
    /**
     * Creates a new OperandX86 object with the given flag and value
     *
     * @param flag The specific type of operand this is
     * @param value The value of the operand
     */
    public OperandX86(OperandFlag flag, int value) {
        this.flag = flag;
        this.value = value;
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
        return 0;
    }

    @Override
    public boolean isType(OperandFlag flag) {
        return this.flag.equals(flag);
    }

    private OperandFlag flag;
    private int value;
}
