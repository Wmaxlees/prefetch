package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;
/**
 * Created by jaspreet on 4/22/16.
 */
public class InstLea extends Instruction {
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        return 2;
    }

    public String opCode() {
        return "LEA";
    }

    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {

        if ((this.operands != null) && this.operands.size() != 2) {
            throw new BadlyFormattedInstructionException(this.opCode() + ": Incorrect number of arguments.");
        }
        if (this.getOperand(0).getFlag() != OperandFlag.pointer) {
            throw new BadlyFormattedInstructionException(this.opCode() + "the operand is not a pointer");
        }

        OperandX86Ptr ptr = (OperandX86Ptr)this.getOperand(0);
        memoryManager.setMemoryValue(this.getOperand(1).getAddress(memoryManager), ptr.getAddress(memoryManager));
        IPHelper.IncrementIP(memoryManager);
        return 0;
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstLea.class, "LEA");
        X86InstructionSet.RegisterInstruction(InstLea.class, "LEAL");
    }
}
