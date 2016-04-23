package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

/**
 * Created by max on 4/23/16.
 */
public class InstRep extends Instruction {

    @Override
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 0;
    }

    @Override
    public String opCode() {
        return "REP";
    }

    @Override
    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 1) {
            this.throwException("Wrong number of operands");
        }

        int ecx = memoryManager.getRegisterValue("%ecx");

        if (ecx == 0) {
            IPHelper.IncrementIP(memoryManager);
            return 0;
        } else {
            memoryManager.setRegisterValue("%ecx", ecx - 1);
        }

        this.getOperand(0).getValue(memoryManager);

        return 0;
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstRep.class, "REP");
    }
}
