package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

public class InstStos extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 1;
    }

    public String opCode() {
        return "STOS";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 0) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }
        int esp = memoryManager.getMemoryValue(memoryManager.getRegisterAddress("%esp")).value;
        int edi = memoryManager.getMemoryValue(memoryManager.getRegisterAddress("%edi")).value;
        int eax = esp + edi;
        memoryManager.setMemoryValue(memoryManager.getRegisterAddress("%edi"), edi + 1);
        memoryManager.setMemoryValue(memoryManager.getRegisterAddress("%eax"), eax);
        return 0;
    }

    static {
        X86InstructionSet.RegisterInstruction(InstRet.class, "STOS");
        X86InstructionSet.RegisterInstruction(InstRet.class, "STOSL");
    }
}