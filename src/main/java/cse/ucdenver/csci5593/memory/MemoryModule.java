package cse.ucdenver.csci5593.memory;

/**
 * Created by max on 3/14/16.
 */
public interface MemoryModule {
    /**
     * Returns true if the memory module holds the value
     * of the given index
     *
     * @param memoryLocation The index of the memory needed
     * @return true if the module holds the value, false otherwise
     */
    boolean hasValue(int memoryLocation);

    /**
     * Sets the module to hold the value of the given
     * memory location
     *
     * @param memoryLocation
     */
    void setValue(int memoryLocation);

    /**
     * Must be called every tick. Used for when caches update
     * flags based on ticks, etc.
     */
    void update();

    /**
     * @return The time it takes to check whether the module contains
     * memory
     */
    int checkTime();

    /**
     * @return The access time of the module
     */
    int accessTime();
}
