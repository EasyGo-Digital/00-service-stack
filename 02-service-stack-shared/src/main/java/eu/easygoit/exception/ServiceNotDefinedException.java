package eu.easygoit.exception;

import eu.easygoit.annotation.MsgLocale;


/**
 * The type Service not defined exception.
 */
@MsgLocale("service.not.defined.exception")
public class ServiceNotDefinedException extends ManagedException {

    /**
     * Instantiates a new Service not defined exception.
     *
     * @param message the message
     */
    public ServiceNotDefinedException(String message) {
        super(message);
    }
}
