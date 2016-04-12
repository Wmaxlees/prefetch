package cse.ucdenver.csci5593.memory;

/**
 * Created by max on 4/12/16.
 */
public class InfiniteMainMemory implements MemoryModule {
    @Override
    public boolean hasValue(int memoryLocation) {
        return true;
    }

    @Override
    public int accessTime() {
        return 150;
    }

    @Override
    public int checkTime() {
        return 0;
    }

    @Override
    public void setValue(int memoryLocation) {}

    @Override
    public void update() {}
}
