package cse.ucdenver.csci5593.instruction.x86.helpers;

import cse.ucdenver.csci5593.memory.RegisterMemoryModule;

/**
 * Created by max on 4/15/16.
 */
public class FlagHelper {
    /**
     * Determine the parity of a number
     *
     * @param num The number to check parity
     * @return True if it has odd parity, false otherwise
     */
    public static final boolean GetParityFlag(long num) {
        boolean parity = true;
        while (num != 0) {
            long temp = num - 1;
            num = num & temp;

            parity = !parity;
        }

        return parity;
    }

    /**
     * Determine whether the number overflows a 4 byte word
     *
     * @param num Number to check
     * @return True if an overflow occurred
     */
    public static final boolean GetOverflowFlag(long num) {
        return (num > 4294967295L) || (num < -2147483647);
    }

    /**
     * Determine whether the sign flag should be set
     *
     * @param num Number to check
     * @return True if the sign flag should be set
     */
    public static final boolean GetSignFlag(long num) {
        return (num < 0);
    }

    /**
     * Determine whether the zero flag should be set
     *
     */
    public static final boolean GetZeroFlag(long num) {
        return (num == 0);
    }
}
