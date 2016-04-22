package cse.ucdenver.csci5593.parser;

import cse.ucdenver.csci5593.instruction.Instruction;
import cse.ucdenver.csci5593.instruction.Operand;
import cse.ucdenver.csci5593.instruction.OperandFlag;
import cse.ucdenver.csci5593.instruction.x86.*;
import cse.ucdenver.csci5593.memory.RegisterMemoryModule;
import cse.ucdenver.csci5593.memory.X86RegisterMemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by willi on 3/14/2016.
 */
public class X86InstructionSet implements InstructionSet {

    private RegisterMemoryModule registers;
    private static HashMap<String, Class<? extends Instruction>> is = new HashMap<>();
    private HashMap<String, OperandX86> labels;

    public static void RegisterInstruction(Class<? extends Instruction> instruction, String key) {
        is.put(key, instruction);
    }

    public X86InstructionSet() {
        this.registers = new X86RegisterMemory();
        this.labels = new HashMap<>();
        System.out.println(this.is);
    }

    public void addLabel(String label, int address) {
        OperandX86 op = this.labels.get(label);
        if (op == null) {
            this.labels.put(label, new OperandX86(OperandFlag.address, address));
        } else {
            op.setValue(address);
        }
    }

    @Override
    public HashMap<Integer, Instruction> generateInstructions(String[] tokens, int index) throws ParserException {
        HashMap<Integer, Instruction> instructions = new HashMap<>();

        // Check for a label
        int offset = 0;
        if (this.isLabel(tokens[0])) {
            this.addLabel(tokens[0], index);
            offset = 1;
        }

        // Check operands first for necessary pointer loads
        int pointerIndex = -1;
        for (int i = 1; i+offset < tokens.length; ++i) {
            if (this.isPointer(tokens[offset+i])) {
                if (pointerIndex != -1) {
                    throw new ParserException("Can't have two pointers in single command");
                }
                pointerIndex = i;

                Instruction pushInst = new InstPush();
                pushInst.addOperand(new OperandX86(OperandFlag.register, this.registers.getRegisterAddress("%ebx")));

                // Load the pointer value into a reg
                Instruction loadInst = new InstMov();
                loadInst.addOperand(new OperandX86Ptr(tokens[offset+i]));
                loadInst.addOperand(new OperandX86(OperandFlag.register, this.registers.getRegisterAddress("%ebx")));
                instructions.put(index++, loadInst);
            }
        }

        tokens[offset] = tokens[offset].trim();
        String upper = tokens[offset].toUpperCase();

        Class<? extends Instruction> instClass = is.get(upper);
        if (instClass == null) {
            System.out.println("No such instruction: " + upper);
            return null;
        }

        Instruction inst;
        try {
            inst = instClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.print("Error instantiating instance of instruction class");
            return null;
        }

        for (int i = 1; i+offset < tokens.length; ++i) {
            inst.addOperand(this.parseOperand(tokens[offset+i]));
        }
        instructions.put(index++, inst);

        System.out.println(inst);

        if (pointerIndex != -1) {
            // Store the pointer value from the reg
            Instruction storeInst = new InstMov();
            storeInst.addOperand(new OperandX86(OperandFlag.register, this.registers.getRegisterAddress("%ebx")));
            storeInst.addOperand(new OperandX86Ptr(tokens[offset+pointerIndex]));
            instructions.put(index++, storeInst);

            Instruction popInst = new InstPop();
            popInst.addOperand(new OperandX86(OperandFlag.register, this.registers.getRegisterAddress("%ebx")));
            instructions.put(index++, popInst);
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
        token = token.trim();

        if (token.endsWith(",")) {
            token = token.substring(0, token.length() - 1);
        }

        if (this.isPointer(token)) {
            operand = new OperandX86Ptr(token);
        } else if (this.isLiteral(token)) {
            operand = new OperandX86(OperandFlag.literal, this.getLiteralValue(token));
        } else if (this.isRegister(token)) {
            operand = new OperandX86(OperandFlag.register, this.registers.getRegisterAddress(token));
        } else if (this.isLabel(token)) {
            OperandX86 op = this.labels.get(token);
            if (op == null) {
                op = new OperandX86(OperandFlag.address, 0);
                this.labels.put(token, op);
            }
            operand = this.labels.get(token);
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
        Pattern pattern = Pattern.compile("[-+]*[0-9]*\\(%[a-zA-Z]+\\)");
        Matcher matcher = pattern.matcher(token);

        Pattern pattern2 = Pattern.compile("%[a-zA-Z]+:[0-9]+");
        Matcher matcher2 = pattern2.matcher(token);

        if (matcher.matches() || matcher2.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int maxRegisterIndex() {
        return this.registers.getMaxRegisterIndex();
    }

    @Override
    public String stripComments(String line) {
        // Handle comments
        if (line.startsWith(";")) {
            return "";
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

        if (lower.startsWith("$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determine whether the token is a label
     *
     * @param token The token to check
     * @return True if the token is a label
     */
    private boolean isLabel(String token) {
        return token.startsWith(".");
    }

    /**
     * Get the literal value of a token
     *
     * @param token The string to parse
     * @return The value the literal represents
     */
    private int getLiteralValue(String token) {
        return Integer.parseInt(token.substring(1));
    }

    /**
     * Determines whether a token represents a register
     *
     * @param token The token to check
     * @return True if the string is a register
     */
    private boolean isRegister(String token) {
        if (token.startsWith("%") && !token.contains(":")) {
            if (this.registers.getRegisterAddress(token) == 0) {
                throw new ParserException("Unrecognized register: " + token);
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

    /**
     * Load in all of the instructions appropriate for this
     * instruction set. Must be called before the instruction set
     * can be used to parse an asm file.
     */
    public static void loadInstructions() {
        InstAdd.load();
        InstAnd.load();
        InstDiv.load();
        InstJE.load();
        InstJMP.load();
        InstMov.load();
        InstMul.load();
        InstOr.load();
        InstPop.load();
        InstPush.load();
        InstRet.load();
        InstSub.load();
        InstXor.load();
    }
}
