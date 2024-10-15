package eu.easygoit.com.rest.controller;

import eu.easygoit.exception.BeanNotFoundException;
import eu.easygoit.exception.ExceptionHandlerNotDefinedException;
import eu.easygoit.exception.handler.IExceptionHandler;
import org.springframework.http.ResponseEntity;

/**
 * The interface Controller exception handler.
 */
public interface IControllerExceptionHandler {

    /**
     * Exception handler exception handler.
     *
     * @return the exception handler
     * @throws BeanNotFoundException               the bean not found exception
     * @throws ExceptionHandlerNotDefinedException the exception handler not defined exception
     */
    IExceptionHandler exceptionHandler() throws BeanNotFoundException, ExceptionHandlerNotDefinedException;

    /**
     * Gets back exception response.
     *
     * @param e the e
     * @return the back exception response
     */
    ResponseEntity getBackExceptionResponse(Throwable e);

    /**
     * Handle exception message string.
     *
     * @param throwable the throwable
     * @return the string
     */
    String handleExceptionMessage(Throwable throwable);
}
