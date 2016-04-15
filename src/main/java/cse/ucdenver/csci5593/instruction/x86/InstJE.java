package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.MemoryManager;

/**
 * Created by max on 4/15/16.
 */
public class InstJE extends Instruction {
    @Override
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 1;
    }

    public String opCode() {
        return "JE";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 1) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }



        int result = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value +
                memoryManager.getMemoryValue(this.getOperand(1).getValue()).value;

        memoryManager.setMemoryValue(this.getOperand(0).getValue(), result);

        return 0;
    }
}
