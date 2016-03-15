package cse.ucdenver.csci5593.instruction.x86;

import cse.ucdenver.csci5593.instruction.Instruction;

/**
 * Created by willi on 3/14/2016.
 */
public class InstAdd extends Instruction {
    public int CPI() {
        return 6;
    }

    public String OpCode() {
        return "BEQ";
    }
}
