package cse.ucdenver.csci5593.parser;

import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.Operand;

import java.util.HashMap;
import java.util.List;

/**
 * Created by willi on 3/14/2016.
 */
public interface InstructionSet {
    /**
     * Generates the correct instruction instances from
     * the passed token
     *
     * @param tokens The tokens to determine the instruction
     * @return The correct instance of Instruction interface or
     *         null if the instruction isn't recognized
     */
    HashMap<Integer, Instruction> generateInstructions(String[] tokens, int index);

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

}
