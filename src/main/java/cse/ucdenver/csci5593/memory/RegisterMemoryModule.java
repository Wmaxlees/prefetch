package cse.ucdenver.csci5593.memory;

/**
 * Created by max on 4/15/16.
 */
public interface RegisterMemoryModule extends MemoryModule {
    enum Flag {
        ZERO_FLAG,
        OVERFLOW_FLAG,
        SIGN_FLAG,
        ADJUST_FLAG,
        CARRY_FLAG,
        PARITY_FLAG,
        DIRECTION_FLAG
    }

    /**
     * Get the address of the register by name
     *
     * @param name The name of the register
     * @return The memory address of the name
     */
    int getRegisterAddress(String name);


    /**
     * Returns the name of the address. Or null if
     * the the address is not a register
     *
     * @param address The named address
     * @return Either the name or null if the address is not
     * a register
     */
    String getAddressName(int address);


        /**
         * Get the value of a specific flag
         *
         * @param name The name of the flag to get
         * @return The value of the flag
         */
    boolean getFlag(Flag name);

    /**
     * Set the value of a flag
     *
     * @param name The name of the flag
     */
    void setFlag(Flag name);

    /**
     * Reset the value of a flag
     *
     * @param name The name of the flag
     */
    void resetFlag(Flag name);

    /**
     * Get the maximum index of the registers
     *
     * @return The maximum register index
     */
    int getMaxRegisterIndex();
}
