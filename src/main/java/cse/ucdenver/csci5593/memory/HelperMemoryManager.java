package cse.ucdenver.csci5593.memory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ahmed Aldhlaki on 4/1/16.
 */

public class HelperMemoryManager extends MemoryManager 
{
	/**
     * Set the memory value for Helper memory manager 
     * 
     * @param address The memory location to set
     * 
     */
	public void setMemoryValue(int address, int value)
	{
		for (MemoryModule module : modules) {
			module.setValue(address);
		}
	}
	
	/**
     * The number 0 represents first level of cache.
     * The number 1 represents second level of cache.
     * The number 2 represents third level of cache which is shared by both processors. 
     * The number 3 represents MM.
     * Set the cache level 3 and Main Memory.
     * Set the cache level 0 and cache level 1 to new access time and size
     * for cache level 0, the number 5 is cache access time, and the number 10 is cache size.
     * for cache level 1, the number 10 is cache access time, and the number 20 is cache size.
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
