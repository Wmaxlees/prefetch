package cse.ucdenver.csci5593.parser;

import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.Operand;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.instruction.x86.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by willi on 3/14/2016.
 */
public class X86InstructionSet implements InstructionSet {
    @Override
    public List<Instruction> generateInstructions(String[] tokens) throws ParserException {
        ArrayList<Instruction> instructions = new ArrayList<>();

        // Check operands first for necessary pointer loads
        int pointerIndex = -1;
        for (int i = 1; i < tokens.length; ++i) {
            if (this.isPointer(tokens[i])) {
                if (pointerIndex != -1) {
                    throw new ParserException("Can't have two pointers in single command");
                }
                pointerIndex = i;

                Instruction pushInst = new InstPush();
                pushInst.addOperand(new OperandX86(OperandFlag.register, this.registerAddress("%ebx")));

                // Load the pointer value into a reg
                Instruction loadInst = new InstMov();
                loadInst.addOperand(new OperandX86Ptr(tokens[i]));
                loadInst.addOperand(new OperandX86(OperandFlag.register, this.registerAddress("%ebx")));
                instructions.add(loadInst);
            }
        }


        String upper = tokens[0].toUpperCase();

        switch (upper) {
            case "ADD":
            case "ADDL": {
                this.checkOperandNumber(tokens, 2);

                Instruction inst = new InstAdd();
                inst.addOperand(this.parseOperand(tokens[1]));
                inst.addOperand(this.parseOperand(tokens[2]));
                instructions.add(inst);
                break;
            }
            case "MOV":
            case "MOVL": {
                this.checkOperandNumber(tokens, 2);

                Instruction inst = new InstMov();
                inst.addOperand(this.parseOperand(tokens[1]));
                inst.addOperand(this.parseOperand(tokens[2]));
                instructions.add(inst);
                break;
            }
            case "PUSH":
            case "PUSHL": {
                this.checkOperandNumber(tokens, 1);

                Instruction inst = new InstPush();
                inst.addOperand(this.parseOperand(tokens[1]));
            }
            case "POP":
            case "POPL": {
                this.checkOperandNumber(tokens, 1);

                Instruction inst = new InstPop();
                inst.addOperand(this.parseOperand(tokens[1]));
            }
            default:
                return null;
        }

        if (pointerIndex != -1) {
            // Store the pointer value from the reg
            Instruction storeInst = new InstMov();
            storeInst.addOperand(new OperandX86(OperandFlag.register, this.registerAddress("%ebx")));
            storeInst.addOperand(new OperandX86Ptr(tokens[pointerIndex]));
            instructions.add(storeInst);

            Instruction popInst = new InstPop();
            popInst.addOperand(new OperandX86(OperandFlag.register, this.registerAddress("%ebx")));
        }

        return instructions;
    }

    /**
     * Take in a token and produce the correct Operand
     * instance
     *
     * @param token The string to parse
     * @return The instance of the Operand that represents
     * the value
     */
    private Operand parseOperand(String token) {
        Operand operand;

        if (this.isPointer(token)) {
            operand = new OperandX86Ptr(token);
        } else if (this.isLiteral(token)) {
            operand = new OperandX86(OperandFlag.literal, this.getLiteralValue(token));
        } else if (this.isRegister(token)) {
            operand = new OperandX86(OperandFlag.register, registerAddress(token));
        } else if (false) {
            // TODO: HANDLE DIRECT ADDRESSING
        } else {
            throw new ParserException("Unrecognized operand: " + token);
        }

        return operand;
    }

    /**
     * Determine whether a token represents a pointer
     *
     * @param token The token to check
     * @return True if the token is a pointer
     */
    private boolean isPointer(String token) {
        Pattern pattern = Pattern.compile("[\\-\\+]*[0-9]*\\(%[a-zA-Z]+\\)");
        Matcher matcher = pattern.matcher(token);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get's the register's "memory" address for use
     * in the rest of the application
     *
     * @param token The token to determine the correct
     *              address of
     * @return The integer address of the register
     */
    private int registerAddress(String token) {
        String lower = token.toLowerCase();

        switch (lower) {
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
                return 0;
        }
    }

    @Override
    public int maxRegisterIndex() {
        return 18;
    }

    @Override
    public String stripComments(String line) {
        // Handle comments
        if (line.startsWith(";")) {
            return null;
        }

        // First get rid of comments from end of line
        return line.split(";")[0];
    }

    /**
     * Determine whether a token is a literal
     *
     * @param token The token to check
     * @return True if the token is a literal
     */
    private boolean isLiteral(String token) {
        String lower = token.toLowerCase();
//        if (lower.startsWith("0b") || lower.startsWith("0x") || token.matches("[0-9]+")) {
//            return true;
//        } else {
//            return false;
//        }

        if (lower.startsWith("$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the literal value of a token
     *
     * @param token The string to parse
     * @return The value the literal represents
     */
    private int getLiteralValue(String token) {
        return Integer.getInteger(token.substring(1));
    }

    /**
     * Determines whether a token represents a register
     *
     * @param token The token to check
     * @return True if the string is a register
     */
    private boolean isRegister(String token) {
        if (token.startsWith("%")) {
            String regName = token.substring(1);
            if (this.registerAddress(regName) != 0) {
                throw new ParserException("Unrecognized register: " + regName);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks whether the number of operands is correct
     *
     * @param tokens The list of tokens
     * @param expected The expected number of operands
     * @throws ParserException
     */
    private void checkOperandNumber(String[] tokens, int expected) throws ParserException {
        if (tokens.length != expected+1) {
            String message = "";
            for (String token : tokens) {
                message += token + " ";
            }

            throw new ParserException("Parse error: " + message);
        }
    }
}
