package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

/**
 * Created by max on 4/7/16.
 */
public class InstMov extends Instruction {

    @Override
    public int CPI(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        int result = 0;

        if (this.operands.size() != 2) {
            this.throwException("Incorrect number of arguments.");
        }

        if (this.getOperand(0).isType(OperandFlag.literal)) {
            return result;
        } else {
            result += memoryManager.getMemoryValue(this.getOperand(0).getValue(memoryManager)).accessTime;
        }

        IPHelper.IncrementIP(memoryManager);

        return result;
    }

    @Override
    public String opCode() {
        return "MOV";
    }

    @Override
    public int execute(MemoryManager memoryManager) throws BadlyFormattedInstructionException {
        if (this.operands.size() != 2) {
            this.throwException("Incorrect number of arguments.");
        }

        if (this.getOperand(1).isType(OperandFlag.literal)) {
            this.throwException("Second operand cannot be a constant.");
        }

        int addrA = 0, addrB = 0;

        addrA = this.getOperand(0).getValue(memoryManager);
        addrB = this.getOperand(1).getValue(memoryManager);

        System.out.println("Address A: " + addrA);
        System.out.println("Address B: " + addrB);

        memoryManager.setMemoryValue(addrA, memoryManager.getMemoryValue(addrB).value);

        return 0;
    }

    public static void load() {
        X86InstructionSet.RegisterInstruction(InstMov.class, "MOV");
        X86InstructionSet.RegisterInstruction(InstMov.class, "MOVL");
    }
}
