package cse.ucdenver.csci5593.core;

import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.memory.MemoryManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BasicCoreImpl implements Core
{
// Declare the variable that we need
    MemoryManager mm;
	int currentCycle;
	int currentInstCycle;
	Instruction currentInst;
	HashMap<Integer, Instruction> inst;
    String name;

    public BasicCoreImpl(String name) {
        this.name = name;
    }
	
	// set-up the instruction
	public void setInstruction(HashMap<Integer, Instruction> list)
	{
		this.inst = list;
	}
	
	// Update the current cycle by adding one each time
	// Updating the current Instruction cycle by subtracting one each time and check it if it is zero or not 
	public boolean update()
    {
		++this.currentCycle;
		--this.currentInstCycle;
		if (this.currentInstCycle == 0)
		{
            System.out.println(this.getStatistics());
            System.out.println(mm);
            System.out.println("Executing: " + this.currentInst);
			this.currentInst.execute(this.mm);
            this.currentInst = inst.get(mm.getRegisterValue("%ip"));
            if (this.currentInst == null) {
                return false;
            }

			this.currentInstCycle = this.currentInst.CPI(mm);

            this.mm.update();

//			try {
//				System.in.read();
//			} catch (IOException e) {
//
//			}
		}
        return true;
	}
		
	// Returning the Run Time for current Cycle
	public int getRuntime()
	{
		return this.currentCycle;
	}
	
	// Setting the Memory Manager
	public void setMemoryManager(MemoryManager mm) {
		this.mm = mm;
	}

    public void initialize() {
        this.currentCycle = 0;

        this.currentInst = this.inst.get(mm.getMemoryValue(mm.getRegisterAddress("%ip")).value);
        this.currentInstCycle = this.currentInst.CPI(mm);
    }

	public Core generateHelperCore() {
		Core newCore = new BasicCoreImpl(this.name + " - Helper");

        newCore.setInstruction(this.inst);
        newCore.setMemoryManager(this.mm.generateHelperMemoryManager());

        return newCore;
	}

    @Override
    public String getStatistics() {
        String result = "________________" + this.name + "________________\n";

        List<Integer> hits = this.mm.getAllHits();
        List<Integer> misses = this.mm.getAllMisses();

        for (int i = 0; i < hits.size(); ++i) {
            result += "[" + this.mm.getModuleName(i) + "]" + "     " + hits.get(i) + "/" + misses.get(i) + "\n";
        }

        return result;
    }
}