package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.FlagHelper;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.memory.MemoryManager.MemoryReturn;
import cse.ucdenver.csci5593.memory.RegisterMemoryModule;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

/**
 * Created by jaspreet on 4/19/16.
 */
public class InstRet extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 1;
    }

    public String opCode() {
        return "RET";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        boolean hasOp = this.operands.size() == 1;
        if (this.operands.size() != 0 && !hasOp) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }

        int cs = memoryManager.getRegisterValue("%cs");
        int sp = memoryManager.getRegisterValue("%esp");

        int shift = 0;
        if (hasOp) {
            shift = this.getOperand(0).getValue(memoryManager);
        }

        sp -= shift;
        memoryManager.setRegisterValue("%esp", sp);

        IPHelper.setIP(memoryManager, cs);
        return 0;
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstRet.class, "RET");
    }
}
