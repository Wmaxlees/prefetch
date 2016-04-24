package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.FlagHelper;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.memory.RegisterMemoryModule;
import cse.ucdenver.csci5593.parser.X86InstructionSet;


public class InstAdd extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 6;
    }

    public String opCode() {
        return "ADD";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 2) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }

        long result = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value +
                     memoryManager.getMemoryValue(this.getOperand(1).getValue()).value;

        memoryManager.setMemoryValue(this.getOperand(0).getValue(), (int)result);

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

    static {
        X86InstructionSet.RegisterInstruction(InstAdd.class, "ADD");
        X86InstructionSet.RegisterInstruction(InstAdd.class, "ADDL");
    }
}
