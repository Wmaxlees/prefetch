package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.MemoryManager;

public class InstSub  extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 6;
    }

    public String opCode() {
        return "SUB";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 2) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }

        int result = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value -
                     memoryManager.getMemoryValue(this.getOperand(1).getValue()).value;

        memoryManager.setMemoryValue(this.getOperand(0).getValue(), result);

        return 0;
    }

}
