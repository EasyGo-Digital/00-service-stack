package eu.easygoit.exception;

import eu.easygoit.annotation.MsgLocale;


/**
 * The type Invalid password exception.
 */
@MsgLocale("invalid.password.exception")
public class InvalidPasswordException extends ManagedException {
    /**
     * Instantiates a new Invalid password exception.
     *
     * @param s the s
     */
    public InvalidPasswordException(String s) {
    }
}
