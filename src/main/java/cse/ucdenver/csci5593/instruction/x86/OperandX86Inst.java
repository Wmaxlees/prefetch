package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.Operand;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.memory.MemoryManager;

/**
 * Special case of Operand. Only used to make the REP instruction
 * work. Look at the documentation for full explanation.
 */
public class OperandX86Inst implements Operand {

    public OperandX86Inst(Instruction inst) {
        this.inst = inst;
    }

    /**
     * SHOULD NEVER BE USED. REQUIRED TO IMPLEMENT OPERAND
     */
    @Override
    public void setValue(int value) {
        System.out.println("setValue() called on an OperandX86Inst.");
    }

    @Override
    public boolean isType(OperandFlag flag) {
        return (flag == OperandFlag.instruction);
    }

    /**
     * SHOULD NEVER BE USED. REQUIRED TO IMPLEMENT OPERAND
     */
    @Override
    public int getAddress(MemoryManager memoryManager) {
        System.out.println("getAddress() called on an OperandX86Inst.");
        return -1;
    }

    /**
     * Executes the instruction and returns the time it took
     * to execute.
     *
     * @param memoryManager The memory manager
     * @return The time the instruction took to execute
     */
    @Override
    public int getValue(MemoryManager memoryManager) {
        this.inst.execute(memoryManager);
        return 0;
    }

    @Override
    public OperandFlag getFlag() {
        return OperandFlag.instruction;
    }

    Instruction inst;
}
