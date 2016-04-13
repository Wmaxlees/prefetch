package cse.ucdenver.csci5593.core;

import cse.ucdenver.csci5593.instruction.Instruction;

import java.util.List;

/**
 * Created by willi on 3/14/2016.
 * finish by Ahmed Aldhlaki on 4/12/2016
 */
public interface Core {
    /**
     * Gives the instructions to execute
     *
     * @param inst The list of instructions to execute
     */
	
	void setInstructions(Queue<Instruction> list);
	boolean update();
	void setMemoryManager();
	void getRuntime();
}