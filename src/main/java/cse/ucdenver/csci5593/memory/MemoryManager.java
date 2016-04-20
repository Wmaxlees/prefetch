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
    private RegisterMemoryModule registers;
    private List<MemoryModule> modules;
    private HashMap<Integer, Integer> values;

    /**
     * Create a new instance of the MemoryManager
     */
    public MemoryManager() {
        this.modules = new ArrayList<>();
        this.values = new HashMap<>();
    }

    /**
     * Set the register memory module for the manager
     *
     * @param rmm The RegisterMemoryModule to use as registers
     */
    public void setRegisterMemoryModule(RegisterMemoryModule rmm) {
        this.registers = rmm;
        this.setMemoryValue(this.registers.getRegisterAddress("%ip"), this.registers.getMaxRegisterIndex() + 1);
    }

    /**
     * Get the address of a named register
     *
     * @param name The name of the register to get the address of
     * @return The address of the named register
     */
    public int getRegisterAddress(String name) {
        return this.registers.getRegisterAddress(name);
    }

    /**
     * Get the status of a flag
     *
     * @param name Name of the flag
     * @return The status of the flag
     */
    public boolean getFlagStatus(RegisterMemoryModule.Flag name) {
        return this.registers.getFlag(name);
    }

    /**
     * Set the register flag
     *
     * @param name The name of the flag to set
     */
    public void setFlag(RegisterMemoryModule.Flag name) {
        this.registers.setFlag(name);
    }

    /**
     * Reset the register flag
     *
     * @param name The name of the register flag
     */
    public void resetFlag(RegisterMemoryModule.Flag name) {
        this.registers.resetFlag(name);
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
        boolean result = (this.modules.get(index) != null);
        this.modules.set(index, module);
        return result;
    }

    public void update() {
        this.registers.update();

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
        this.registers.setValue(address);

        for (MemoryModule module : this.modules) {
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

        if (this.values.containsKey(address)) {
            result.value = this.values.get(address);
        } else {
            throw new AddressNotFoundException();
        }

        int accum = 0;
        accum += this.registers.checkTime();
        if (this.registers.hasValue(address)) {
            result.accessTime = accum + this.registers.accessTime();
            return result;
        }

        for (MemoryModule module : this.modules) {
            accum += module.checkTime();
            if (module.hasValue(address)) {
                result.accessTime = accum + module.accessTime();
                break;
            }
        }

        return result;
    }
    
    // return the same values for modules for HelperMemoryManager
    HelperMemoryManager GenerateHelper()
	{
		return new HelperMemoryManager(values, modules);
	}
}
