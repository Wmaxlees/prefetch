package cse.ucdenver.csci5593.memory;

import cse.ucdenver.csci5593.memory.exceptions.AddressNotFoundException;

import java.util.HashMap;

/**
 * Created by max on 4/15/16.
 */
public class X86RegisterMemory implements RegisterMemoryModule {

    HashMap<Flag, Boolean> flags;

    public X86RegisterMemory() {
        flags = new HashMap<>();

        flags.put(Flag.ZERO_FLAG, false);
        flags.put(Flag.ADJUST_FLAG, false);
        flags.put(Flag.CARRY_FLAG, false);
        flags.put(Flag.OVERFLOW_FLAG, false);
        flags.put(Flag.PARITY_FLAG, false);
        flags.put(Flag.SIGN_FLAG, false);
    }

    @Override
    public boolean getFlag(Flag name) {
        if (flags.containsKey(name)) {
            return flags.get(name);
        } else {
            throw new AddressNotFoundException();
        }
    }

    @Override
    public void setFlag(Flag name) {
        if (flags.containsKey(name)) {
            flags.replace(name, true);
        } else {
            throw new AddressNotFoundException();
        }
    }

    @Override
    public void resetFlag(Flag name) {
        if (flags.containsKey(name)) {
            flags.replace(name, false);
        } else {
            throw new AddressNotFoundException();
        }
    }

    @Override
    public int getRegisterAddress(String name) {
        switch (name) {
            case "%eax":             // 1 and 2
                return 1;
            case "%ax":              // 2
                return 2;
            case "%ebx":             // 3 and 4
                return 3;
            case "%bx":              // 4
                return 4;
            case "%ecx":             // 5 and 6
                return 5;
            case "%cx":              // 6
                return 6;
            case "%edx":             // 7 and 8
                return 7;
            case "%dx":              // 8
                return 8;
            case "%ebp":              // 9
                return 9;
            case "%esp":              // 10
                return 10;
            case "%di":              // 11
                return 11;
            case "%si":              // 12
                return 12;
            case "%cs":              // 13
                return 13;
            case "%ss":              // 14
                return 14;
            case "%es":              // 15
                return 15;
            case "%ds":              // 16
                return 16;
            case "%ip":              // 17
                return 17;
            case "%flags":           // 18
                return 18;
            default:
                throw new AddressNotFoundException();
        }
    }

    @Override
    public int getMaxRegisterIndex() {
        return 18;
    }

    @Override
    public void setValue(int memoryLocation) {

    }

    @Override
    public boolean hasValue(int memoryLocation) {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public int checkTime() {
        return 0;
    }

    @Override
    public int accessTime() {
        return 0;
    }
}
