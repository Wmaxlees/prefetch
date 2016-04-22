package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.FlagHelper;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.memory.MemoryManager.MemoryReturn;
import cse.ucdenver.csci5593.memory.RegisterMemoryModule;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

public class InstRet extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 1;
    }

    public String opCode() {
        return "RET";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        boolean operand = (this.operands != null);
        if (operand && this.operands.size() != 1) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }

        int result = memoryManager.getMemoryValue(memoryManager.getRegisterAddress("%cs")).value;
        int a = memoryManager.getMemoryValue(this.getOperand(0).getValue(memoryManager)).value;
        if (operand && this.operands.size() != 0) {
            int sp = memoryManager.getMemoryValue(memoryManager.getRegisterAddress("%esp")).value;
            sp -= a;
            memoryManager.setMemoryValue(memoryManager.getRegisterAddress("%esp"), sp);
        }
        IPHelper.setIP(memoryManager, result);
        return 0;
    }

    static {
        X86InstructionSet.RegisterInstruction(InstRet.class, "RET");
    }
}