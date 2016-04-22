package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.memory.RegisterMemoryModule;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

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

        if (memoryManager.getFlagStatus(RegisterMemoryModule.Flag.ZERO_FLAG)) {
            IPHelper.setIP(memoryManager, this.operands.get(0).getValue(memoryManager));
        } else {
            IPHelper.IncrementIP(memoryManager);
        }

        return 0;
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstJE.class, "JE");
        X86InstructionSet.RegisterInstruction(InstJE.class, "JZ");
    }
}
