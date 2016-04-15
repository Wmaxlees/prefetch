package cse.ucdenver.csci5593.instruction.x86.helpers;

import cse.ucdenver.csci5593.memory.MemoryManager;

/**
 * Created by max on 4/15/16.
 */
public class IPHelper {
    public static final void IncrementIP(MemoryManager mm) {
        int ipAddress = mm.getRegisterAddress("%ip");
        IPHelper.setIP(mm, mm.getMemoryValue(ipAddress).value + 1);
    }

    public static final void setIP(MemoryManager mm, int value) {
        int ipAddress = mm.getRegisterAddress("%ip");
        mm.setMemoryValue(ipAddress, value);
    }
}
