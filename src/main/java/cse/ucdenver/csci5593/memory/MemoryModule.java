package cse.ucdenver.csci5593.memory;
import java.util.HashMap;
import java.util.Set;
public L1Cache implements  MemoryModule {
   
   public HashMap<Integer, String> hmap; 

	public boolean hasValue(int memoryLocation) {
		hmap= new HashMap<Integer, String>();
		
	 if( hmap.containsKey(memoryLocation))
	 {
		 System.out.println("value is "+hasValue(memoryLocation)); 
	 }
	 else
	 {
		 System.out.println( "the module does not holds the value");
		
	 }
	 
	return false;	
	}
    
    void setValue(int memoryLocation)
{
   hmap= new HashMap<Integer, String>();
		  hmap.put(1, "one");
	      hmap.put(2, "two");
	      hmap.put(3, "three");
	      hmap.put(4, "four");
	      hmap.put(5, "five");
	      hmap.put(6, "six");
	      hmap.put(7, "seven");
	      hmap.put(8, "eight");
	      hmap.put(9, "nine");
	      hmap.put(10, "ten");
	      Set set = hmap.entrySet();
	      System.out.println(hmap);
}
    void update()
    {
        hmap= new HashMap<Integer, String>();
		 hmap.put(1, "a");
	      hmap.put(2, "b");
	      hmap.put(3, "c");
	      hmap.put(4, "d");
	      hmap.put(5, "e");
	      hmap.put(6, "f");
	      hmap.put(7, "g");
	      hmap.put(8, "h");
	      hmap.put(9, "i");
	      hmap.put(10, "j");
	      System.out.println(hmap);
		
    }

   
    int checkTime();

  
    int accessTime()
    {
        
       long startTime = System.currentTimeMillis();
        long total=0;
        for (int i=0;i<10000000;i++)
        {
        	total += i;
        }
        long stopTime= System.currentTimeMillis();
        long elapsedTime = stopTime-startTime;
        System.out.println(elapsedTime);
    }
}
