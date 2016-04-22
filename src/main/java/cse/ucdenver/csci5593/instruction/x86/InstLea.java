package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

public class InstLea extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 2;
    }

    public String opCode() {
        return "LEA";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        
        if ((this.operands != null) && this.operands.size() != 1) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }
        if (this.getOperand(0).getFlag() != OperandFlag.pointer) {
            throw new BadlyFormattedInstructionException(this.opCode() + "the operand is not a pointer");
        }
        
        int result = this.getOperand(0).getValue();
        memoryManager.setMemoryValue(this.getOperand(1).getValue(), result);
        IPHelper.IncrementIP(memoryManager);
        return 0;
    }

    static {
        X86InstructionSet.RegisterInstruction(InstRet.class, "LEA");
        X86InstructionSet.RegisterInstruction(InstRet.class, "LEAL");
    }
}
