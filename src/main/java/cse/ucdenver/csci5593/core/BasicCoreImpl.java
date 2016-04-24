package cse.ucdenver.csci5593.core;

import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.MemoryManager;

import java.util.HashMap;

/**
 * Created by Ahmed Aldhlaki on 4/1/16.
 */

public class BasicCoreImpl implements Core
{
    /**
     * Declare the variable that we need 
     */
    MemoryManager mm;
	int currentCycle;
	int currentInstCycle;
	Instruction currentInst;
	HashMap<Integer, Instruction> inst;
	
	/**
     * set-up the instruction 
     */
	public void setInstruction(HashMap<Integer, Instruction> list)
	{
		this.inst = list;
	}
	
	/**
     * Update the current cycle by adding one each time
     *  Updating the current Instruction cycle by subtracting one each time and check it if it is zero or not
     */
	public boolean update()
	{
		++this.currentCycle;
		--this.currentInstCycle;
		if (this.currentInstCycle == 0)
		{
			this.currentInst.execute(this.mm);
			this.currentInst = inst.get(mm.getMemoryValue(mm.getRegisterAddress("%ip")));
            if (this.currentInst == null) {
                return false;
            }
		}
        return true;
	}
		
	/**
     * Returning the Run Time for current Cycle
     */
	
	public int getRuntime()
	{
		return this.currentCycle;
	}
	
	/**
     * Setting the Memory Manager
     */
	public void setMemoryManager(MemoryManager mm) {
		this.mm = mm;
	}
}