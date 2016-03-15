package cse.ucdenver.csci5593.instruction;

import java.util.List;

/**
 * Created by willi on 3/14/2016.
 */
public abstract class Instruction {
    /**
     * @return The number of cycles needed to execute
     * the instruction
     */
    public abstract int CPI();

    /**
     * @return The string representation of the operation
     * to be executed
     */
    public abstract String OpCode();

    /**
     * @return The memory addresses of the operands or null
     * if there are no operands
     */
    public List<Integer> Operands() {
        return this.operands;
    }

    /**
     * Add an operand to the instruction
     *
     * @param operand The memory location
     */
    public void addOperand(int operand) {
        this.operands.add(operand);
    }

    /**
     * Add a variable number of operands to the
     * instruction
     *
     * @param operands The memory locations to add
     */
    public void addOperands(int... operands) {
        for (int i : operands) {
            this.addOperand(i);
        }
    }

    private List<Integer> operands;

}
