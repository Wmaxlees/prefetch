package cse.ucdenver.csci5593.parser;

import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.x86.InstAdd;

/**
 * Created by willi on 3/14/2016.
 */
public class X86InstructionSet implements InstructionSet {

    public Instruction generateInstruction(String[] tokens) {
        String upper = tokens[0].toUpperCase();

        switch (upper) {
            case "ADD":
                return new InstAdd();
            default:
                return null;
        }
    }

    public int isRegister(String token) {
        String lower = token.toLowerCase();

        switch (lower) {
            case "eax":             // 1 and 2
                return 1;
            case "ax":              // 2
                return 2;
            case "ebx":             // 3 and 4
                return 3;
            case "bx":              // 4
                return 4;
            case "ecx":             // 5 and 6
                return 5;
            case "cx":              // 6
                return 6;
            case "edx":             // 7 and 8
                return 7;
            case "dx":              // 8
                return 8;
            case "bp":              // 9
                return 9;
            case "sp":              // 10
                return 10;
            case "di":              // 11
                return 11;
            case "si":              // 12
                return 12;
            case "cs":              // 13
                return 13;
            case "ss":              // 14
                return 14;
            case "es":              // 15
                return 15;
            case "ds":              // 16
                return 16;
            case "ip":              // 17
                return 17;
            case "flags":           // 18
                return 18;
            default:
                return 0;
        }
    }

    public int maxRegisterIndex() {
        return 18;
    }

    public String stripComments(String line) {
        // Handle comments
        if (line.startsWith(";")) {
            return null;
        }

        // First get rid of comments from end of line
        return line.split(";")[0];
    }

    public boolean isConstant(String token) {
        String lower = token.toLowerCase();
        if (lower.startsWith("0b") || lower.startsWith("0x") || token.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }
}
