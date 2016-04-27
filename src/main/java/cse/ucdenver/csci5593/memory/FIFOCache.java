package cse.ucdenver.csci5593.memory;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Jaspreet on 4/15/2016.
 */
public class FIFOCache implements MemoryModule {
    private int current;
    private int max;
    private int accessTime;
    private List<Integer> cache;

    public FIFOCache(int size, int time) {
        this.current = 0;
        this.max = size;
        this.accessTime = time;
        this.cache = new ArrayList<Integer>();
    }

    public boolean hasValue(int i) {
        for (int j : this.cache) {
            if (j == i) {
                return true;
            }
        }
        return false;
    }

    public void setValue(int i) {
        this.cache.set(this.current, i);
        this.current = (this.current + 1) % this.max;
    }

    public void update() {
    }

    public int checkTime() {
        return 0;
    }

    public int accessTime() {
        return this.accessTime;
    }

}
