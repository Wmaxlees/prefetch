package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

public class InstDiv extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 20;
    }

    public String opCode() {
        return "DIV";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 1) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }

        int eax = memoryManager.getMemoryValue(1).value;
        int edx = memoryManager.getMemoryValue(7).value;
        int divisor = (edx << 32 | eax);

        int quotient = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value / divisor;
        int remainder = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value % divisor;

        memoryManager.setMemoryValue(memoryManager.getRegisterAddress("%edx"), remainder);
        memoryManager.setMemoryValue(memoryManager.getRegisterAddress("%eax"), quotient);

        IPHelper.IncrementIP(memoryManager);

        return 0;
    }

    static {
        X86InstructionSet.RegisterInstruction(InstDiv.class, "DIV");
        X86InstructionSet.RegisterInstruction(InstDiv.class, "DIVL");
    }
}
