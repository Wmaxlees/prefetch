/**
 * Created by Ahmed on 4/19/16.
 */

package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.BadlyFormattedInstructionException;
import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.helpers.IPHelper;
import cse.ucdenver.csci5593.memory.MemoryManager;
import cse.ucdenver.csci5593.parser.X86InstructionSet;

public class InstJMP extends Instruction {
	@Override
	public int CPI(MemoryManager memoryManager)
			throws BadlyFormattedInstructionException {
		return 1;
	}
	
	public String opCode(){
		return "JMP";
	}
	
	public int execute (MemoryManager memoryManager) throws BadlyFormattedInstructionException{
		if (this.operands.size() != 1){
			throw new BadlyFormattedInstructionException(this.opCode()+" Incorrect number of arguments for JMP.");
	}
		
	    IPHelper.setIP(memoryManager, this.operands.get(0).getValue(memoryManager));
	    return 0;
	}

	public static void load() {
		X86InstructionSet.RegisterInstruction(InstJMP.class, "JMP");
	}
}



 


