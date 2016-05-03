package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

/**
 * Created by jaspreet on 4/15/16.
 */
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

        int divisor = (memoryManager.getRegisterValue("%edx") << 32 | memoryManager.getRegisterValue("%eax"));
        int operand = this.getOperand(0).getValue(memoryManager);

        int quotient = operand / divisor;
        int remainder = operand % divisor;

        memoryManager.setRegisterValue("%eax", quotient);
        memoryManager.setRegisterValue("%edx", remainder);

        IPHelper.IncrementIP(memoryManager);

        return 0;
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstDiv.class, "DIV");
        X86InstructionSet.RegisterInstruction(InstDiv.class, "DIVL");
    }
}
