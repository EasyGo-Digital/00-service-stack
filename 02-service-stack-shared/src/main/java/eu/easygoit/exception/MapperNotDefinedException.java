package eu.easygoit.exception;

import eu.easygoit.annotation.MsgLocale;


/**
 * The type Mapper not defined exception.
 */
@MsgLocale("mapper.not.defined.exception")
public class MapperNotDefinedException extends ManagedException {

    /**
     * Instantiates a new Mapper not defined exception.
     *
     * @param message the message
     */
    public MapperNotDefinedException(String message) {
        super(message);
    }
}
