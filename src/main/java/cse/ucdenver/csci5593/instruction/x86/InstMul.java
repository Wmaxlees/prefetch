package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.MemoryManager;

public class InstMul  extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 20;
    }
    public String opCode()
    {
        return "MUL";
    }
    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 1) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }

        int result = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value *
                     memoryManager.getMemoryValue(1).value;

        int eax = memoryManager.getMemoryValue(1).value;
        int edx = memoryManager.getMemoryValue(7).value;
        
        edx = result >> 32;
        eax = result ^ (edx << 32);
   
        memoryManager.setMemoryValue(7, edx);
        memoryManager.setMemoryValue(1, eax);
        return 0;
    }
}

