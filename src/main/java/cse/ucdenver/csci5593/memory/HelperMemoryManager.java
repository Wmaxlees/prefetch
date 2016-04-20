package cse.ucdenver.csci5593.memory;

import java.util.HashMap;
import java.util.List;

public class HelperMemoryManager extends MemoryManager 
{
	/**
     * Set the memory value for Helper memory manager 
     * 
     * @param adress The memory location to set
     * 
     */
	public void setMemoryValue(int address, int value)
	{
		for (MemoryModule module : modules) {
			module.setValue(address);
		}
	}
	
	/**
     * Set the cache level 3 and Main Memory 
     * Set the cache level 0 and cache level 1 to new access time and size
     * 
     * 
     */
	
	HelperMemoryManager(HashMap<Integer, Integer> values, List<MemoryModule> Module)
	{
		this.values = new HashMap<Integer, Integer>(values);
		this.modules.set(2, modules.get(2));
		this.modules.set(3, modules.get(3));
		this.modules.set(0, new FIFOCache(5, 10));
		this.modules.set(1, new FIFOCache(10, 20));
		
		this.registers = new X86RegisterMemory();
	}


}
