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
        }

        if (this.getOperand(0).isType(OperandFlag.pointer)) {
            int effectiveAddress = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value +
                    this.getOperand(0).getOffset();

            result += memoryManager.getMemoryValue(effectiveAddress).accessTime;
        } else {
            result += memoryManager.getMemoryValue(this.getOperand(0).getValue()).accessTime;
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

        if (this.getOperand(0).isType(OperandFlag.pointer)) {
            addrA = memoryManager.getMemoryValue(this.getOperand(0).getValue()).value +
                    this.getOperand(0).getOffset();
        } else {
            addrA = this.getOperand(0).getValue();
        }

        if (this.getOperand(1).isType(OperandFlag.pointer)) {
            addrB = memoryManager.getMemoryValue(this.getOperand(1).getValue()).value +
                    this.getOperand(1).getOffset();
        } else {
            addrB = this.getOperand(1).getValue();
        }

        memoryManager.setMemoryValue(addrA, memoryManager.getMemoryValue(addrB).value);

        return 0;
    }

    static {
        X86InstructionSet.RegisterInstruction(InstMov.class, "MOV");
        X86InstructionSet.RegisterInstruction(InstMov.class, "MOVL");
    }
}
