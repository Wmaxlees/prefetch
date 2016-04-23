package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.memory.RegisterMemoryModule;
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

        int es = memoryManager.getRegisterValue("%es");
        int edi = memoryManager.getRegisterValue("%edi");
        int eax = memoryManager.getRegisterValue("%eax");
        boolean df = memoryManager.getFlagStatus(RegisterMemoryModule.Flag.DIRECTION_FLAG);

        int address = es + edi;

        memoryManager.setMemoryValue(address, eax);

        if (df) {
            memoryManager.setRegisterValue("%edi", edi - 1);
        } else {
            memoryManager.setRegisterValue("%edi", edi + 1);
        }

        IPHelper.IncrementIP(memoryManager);

        return 0;
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstRet.class, "STOS");
        X86InstructionSet.RegisterInstruction(InstRet.class, "STOSL");
    }
}