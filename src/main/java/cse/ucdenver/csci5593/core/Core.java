package cse.ucdenver.csci5593.core;

import cse.ucdenver.csci5593.instruction.Instruction;

import java.util.List;

/**
 * Created by willi on 3/14/2016.
 */
public interface Core {
    /**
     * Gives the instructions to execute
     *
     * @param inst The list of instructions to execute
     */
    void instructions(List<Instruction> inst);
}
