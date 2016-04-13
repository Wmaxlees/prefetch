package cse.ucdenver.csci5593.memory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
public class FIFOCache implements MemoryModule{
	FIFOCache(int size, int time)
	{
		this.max=size;
		this.accesstime=time;
	}
	
	int current=0;
	int max=20;
	int accesstime;
	ArrayList<Integer> cache = new ArrayList<Integer>();
	
	public boolean hasValue(int i) {
		for(int j:cache)
		{
			if(j==i)
			{
				return true;
			}
		}
		return false;
	}

	public void setValue(int i) {
		
		cache.set(current, i);
		
		current = (current + 1)% max;
	}

	public void update() {
		
	}

	public int checkTime() {
		
		return 0;
	}

	public int accessTime() {
		
		return accesstime;
	}
	
	

}


