package cse.ucdenver.csci5593.memory.exceptions;

import com.sun.jndi.cosnaming.IiopUrl;

/**
 * Created by max on 4/7/16.
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String msg) {
        super(msg);
    }

}
