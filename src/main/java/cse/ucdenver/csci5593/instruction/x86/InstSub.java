package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.FlagHelper;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.memory.RegisterMemoryModule;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

public class InstSub  extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 6;
    }

    public String opCode() {
        return "SUB";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 2) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }

        long result = this.getOperand(0).getValue(memoryManager) - this.getOperand(1).getValue(memoryManager);

        memoryManager.setMemoryValue(this.getOperand(1).getAddress(memoryManager), (int)result);

        this.setFlags(memoryManager, result);

        IPHelper.IncrementIP(memoryManager);

        return 0;
    }

    private void setFlags(MemoryManager memoryManager, long result) {
        if (FlagHelper.GetOverflowFlag(result)) {
            memoryManager.setFlag(RegisterMemoryModule.Flag.OVERFLOW_FLAG);
        }

        if (FlagHelper.GetParityFlag(result)) {
            memoryManager.setFlag(RegisterMemoryModule.Flag.PARITY_FLAG);
        } else {
            memoryManager.resetFlag(RegisterMemoryModule.Flag.PARITY_FLAG);
        }

        // TODO: Handle CARRY_FLAG
        // TODO: Handle ADJUST_FLAG

        if (FlagHelper.GetSignFlag(result)) {
            memoryManager.setFlag(RegisterMemoryModule.Flag.SIGN_FLAG);
        }

        if (FlagHelper.GetZeroFlag(result)) {
            memoryManager.setFlag(RegisterMemoryModule.Flag.ZERO_FLAG);
        }
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstSub.class, "SUB");
        X86InstructionSet.RegisterInstruction(InstSub.class, "SUBL");
    }

}
