package cse.ucdenver.csci5593.instruction;

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
     *
     * @return The actual value of the operand
     */
    int getValue();

    /**
     * Only applicable for pointers
     *
     * @return The offset of the pointer
     */
    int getOffset();

    /**
     *
     * @param flag The flag to compare to
     * @return True if the two flags are equal
     */
    boolean isType(OperandFlag flag);
}
