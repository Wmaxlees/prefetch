package cse.ucdenver.csci5593.memory;

import cse.ucdenver.csci5593.memory.exceptions.AddressNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Integer, Integer> values;

    public MemoryManager() {
        this.modules = new ArrayList<>();
        this.values = new HashMap<>();
    }

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

    public void update() {
        for (MemoryModule module : modules) {
            module.update();
        }
    }

    /**
     * Set the value of a given memory location
     *
     * @param address The memory location to set
     * @param value The value to save to the memory location
     */
    public void setMemoryValue(int address, int value) {
        for (MemoryModule module : modules) {
            module.setValue(address);
        }

        this.values.put(address, value);
    }

    public class MemoryReturn {
        public int accessTime;
        public int value;
    }

    /**
     * Get the value of a given memory address
     *
     * @param address The address to access
     * @return A MemoryReturn object containing the value and
     *         the time it took to access the given value
     * @throws AddressNotFoundException
     */
    public MemoryReturn getMemoryValue(int address) throws AddressNotFoundException {
        MemoryReturn result = new MemoryReturn();

        if (values.containsKey(address)) {
            result.value = values.get(address);
        } else {
            throw new AddressNotFoundException();
        }

        int accum = 0;
        for (MemoryModule module : modules) {
            accum += module.checkTime();
            if (module.hasValue(address)) {
                result.accessTime = accum;
                break;
            }
        }

        return result;
    }
}
