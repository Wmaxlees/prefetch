package cse.ucdenver.csci5593.memory;

import java.util.List;

/**
 * Created by max on 3/14/16.
 */
public class MemoryManager {
    /**
     * Holds the memory modules (attempts to access
     * lower indices first)
     */
    private List<MemoryModule> modules;

    /**
     * Add a module to the memory manager
     *
     * @param index The index to insert the memory module
     * @param module The module to insert
     * @return true if the memory module replaced an already
     * existing module
     */
    public boolean addModule(int index, MemoryModule module) throws IndexOutOfBoundsException {
        if (index >= this.modules.size()) {
            throw new IndexOutOfBoundsException("Index " +
                    index + " doesn't exist in memory manager");
        }

        boolean result = (this.modules.get(index) != null);

        this.modules.set(index, module);

        return result;
    }
}
