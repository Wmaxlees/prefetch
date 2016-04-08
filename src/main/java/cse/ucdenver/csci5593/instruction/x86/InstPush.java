package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.memory.MemoryManager;

/**
 * Created by max on 4/7/16.
 */
public class InstPush extends Instruction {
    @Override
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {

        return 0;
    }

    @Override
    public String opCode() {
        return "PUSH";
    }

    @Override
    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 1) {
            this.throwException("Wrong number of operands");
        }
        if (!this.getOperand(0).isType(OperandFlag.register)) {
            this.throwException("Operand not register");
        }

        int stackAddress = memoryManager.getMemoryValue(10).value;     // Stack
        int value = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value;

        memoryManager.setMemoryValue(stackAddress, value);
        memoryManager.setMemoryValue(10, stackAddress + 1);

        return 0;
    }
}
