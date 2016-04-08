package cse.ucdenver.csci5593.instruction;

/**
 * Created by max on 4/7/16.
 */
public class BadlyFormattedInstructionException extends RuntimeException {
    public BadlyFormattedInstructionException() {
        super();
    }

    public BadlyFormattedInstructionException(String s) {
        super(s);
    }
}
