package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.memory.RegisterMemoryModule;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

public class InstMul extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 20;
    }

    public String opCode() {
        return "MUL";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 1) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }
        int eax = memoryManager.getMemoryValue(1).value;
        int result = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value * eax;

        int edx = result >> 32;
        eax = result ^ (edx << 32);

        memoryManager.setMemoryValue(memoryManager.getRegisterAddress("%edx"), edx);
        memoryManager.setMemoryValue(memoryManager.getRegisterAddress("%eax"), eax);
        if (edx == 0) {
            memoryManager.setFlag(RegisterMemoryModule.Flag.CARRY_FLAG);
            memoryManager.setFlag(RegisterMemoryModule.Flag.OVERFLOW_FLAG);
        } else {
            memoryManager.resetFlag(RegisterMemoryModule.Flag.CARRY_FLAG);
            memoryManager.resetFlag(RegisterMemoryModule.Flag.OVERFLOW_FLAG);

        }
        IPHelper.IncrementIP(memoryManager);

        return 0;
    }

    static {
        X86InstructionSet.RegisterInstruction(InstMul.class, "MUL");
        X86InstructionSet.RegisterInstruction(InstMul.class, "MULL");
    }
}
