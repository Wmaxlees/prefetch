package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.memory.MemoryManager;

/**
 * Created by max on 4/7/16.
 */
public class InstPop extends Instruction {
    @Override
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        int stackAddress = memoryManager.getMemoryValue(10).value;

        return memoryManager.getMemoryValue(stackAddress).accessTime;
    }

    @Override
    public String opCode() {
        return "POP";
    }

    @Override
    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 1) {
            this.throwException("Incorrect number of arguments.");
        }

        if (!this.getOperand(0).isType(OperandFlag.register)) {
            this.throwException("Operand is not a register");
        }

        int stackPointer = memoryManager.getMemoryValue(10).value;
        int value = memoryManager.getMemoryValue(stackPointer).value;

        memoryManager.setMemoryValue(this.getOperand(0).getValue(), value);
        memoryManager.setMemoryValue(10, stackPointer-1);

        return 0;
    }
}
