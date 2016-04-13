public class BasicCoreImpl 
{
// Declare the variable that we need
    MemoryManager MM;
	int currentCycle;
	int crrentInstCycle;
	Instruction currentInst;
	List<instruction> inst;
	
	// set-up the instruction
	public void SetInstruction(List<inst> List)
	{
		this.inst = list;
	}
	
	// Update the current cycle by adding one each time
	// Updating the current Instruction cycle by subtracting one each time and check it if it is zero or not 
	public void Update() 
	{
		CurrentCycle += 1;
		CurrentInstCycle -=1;
		if (CurrentInstCycle = 0)
		{
			CI.execute(MM);
			CI = inst.pop();
		}
	}
		
	// Returning the Run Time for current Cycle
	public int GetRunTime()
	{
		return CurrentCycle;
	}
	
	// Setting the Memory Manager
	public void SetMemoryManager(MemoryManager MM)
	{
		this.MM = MM;
	}
}