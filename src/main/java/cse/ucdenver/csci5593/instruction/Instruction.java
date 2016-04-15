package cse.ucdenver.csci5593.instruction;

import cse.ucdenver.csci5593.memory.MemoryManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willi on 3/14/2016.
 */
public abstract class Instruction {

    /**
     * Cons
     */
    public Instruction() {
        this.operands = new ArrayList<>();
    }

    /**
     * @return The number of cycles needed to execute
     * the instruction
     */
    public abstract int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException;

    /**
     * @return The string representation of the operation
     * to be executed
     */
    public abstract String opCode();

    /**
     *
     * @param memoryManager The memory manager of the executing cpu
     * @return The result of the execution
     *          0 - Success
     */
    public abstract int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException;

    /**
     * @return The memory addresses of the operands or null
     * if there are no operands
     */
    public List<Operand> operands() {
        return this.operands;
    }

    /**
     * Returns the i-th operand
     *
     * @param index The index of the operand to return
     * @return The operand requested
     */
    public Operand getOperand(int index) {
        return this.operands.get(index);
    }

    /**
     * Add an operand to the instruction
     *
     * @param operand The memory location
     */
    public void addOperand(Operand operand) {
        this.operands.add(operand);
    }

    /**
     * Add a variable number of operands to the
     * instruction
     *
     * @param operands The memory locations to add
     */
    public void addOperands(Operand... operands) {
        for (Operand i : operands) {
            this.addOperand(i);
        }
    }

    public void throwException(String message) throws BadlyFormattedInstructionException {
        throw new BadlyFormattedInstructionException(this.opCode() + ": " + message);
    }

    protected List<Operand> operands;

}
