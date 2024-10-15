package eu.easygoit.exception;

import eu.easygoit.annotation.MsgLocale;


/**
 * The type Unauthorized exception.
 */
@MsgLocale("unauthorized.exception")
public class UnauthorizedException extends ManagedException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param message the message
     */
    public UnauthorizedException(String message) {
        super(message);
    }

}
