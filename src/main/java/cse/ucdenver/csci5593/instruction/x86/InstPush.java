package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

/**
 * Created by max on 4/7/16.
 */
public class InstPush extends Instruction {
    @Override
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 1;
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

        int stackAddress = memoryManager.getRegisterValue("%esp");     // Stack
        int value = this.getOperand(0).getValue(memoryManager);

        memoryManager.setMemoryValue(stackAddress, value);
        memoryManager.setMemoryValue(10, stackAddress + 1);

        IPHelper.IncrementIP(memoryManager);

        return 0;
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstPush.class, "PUSH");
        X86InstructionSet.RegisterInstruction(InstPush.class, "PUSHL");
    }
}
