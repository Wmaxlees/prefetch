package cse.ucdenver.csci5593.parser;

import cse.ucdenver.csci5593.instruction.Instruction;

/**
 * Created by willi on 3/14/2016.
 */
public interface InstructionSet {
    /**
     * Generates the correct instruction instance from
     * the split string
     *
     * @param splitString The split string
     * @return The correct instance of Instruction interface or
     *         null if the instruction isn't recognized
     */
    Instruction generateInstruction(String[] splitString);

    /**
     * Returns the register index or 0 if the string doesn't
     * represent a register
     *
     * @param str The string to check
     * @return The index of the register or 0 if the string doesn't
     *         represent a register
     */
    int isRegister(String str);

    /**
     * @return The maximum index that represents a register for the given
     *         instruction set
     */
    int maxRegisterIndex();

    /**
     * Strips the comments from a string and returns the sanitized string
     *
     * @param line The string to sanitize
     * @return The sanitized string
     */
    String stripComments(String line);

    /**
     * Returns true if the token represents a constant in the given
     * instruction set
     *
     * @param token The token to check against
     * @return True if the token is a constant
     */
    boolean isConstant(String token);
}
