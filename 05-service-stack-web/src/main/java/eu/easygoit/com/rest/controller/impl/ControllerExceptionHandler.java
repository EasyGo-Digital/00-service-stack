package eu.easygoit.com.rest.controller.impl;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.app.ApplicationContextService;
import eu.easygoit.com.rest.controller.IControllerExceptionHandler;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.exception.BeanNotFoundException;
import eu.easygoit.exception.ExceptionHandlerNotDefinedException;
import eu.easygoit.exception.handler.IExceptionHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * The type Controller exception handler.
 */
@Slf4j
public abstract class ControllerExceptionHandler implements IControllerExceptionHandler {

    @Getter
    @Autowired
    private ApplicationContextService applicationContextService;

    private IExceptionHandler exceptionHandler;

    public final IExceptionHandler exceptionHandler() throws BeanNotFoundException, ExceptionHandlerNotDefinedException {
        if (this.exceptionHandler == null) {
            CtrlHandler ctrlHandler = this.getClass().getAnnotation(CtrlHandler.class);
            if (ctrlHandler != null) {
                this.exceptionHandler = applicationContextService.getBean(ctrlHandler.value());
                if (this.exceptionHandler == null) {
                    log.error("<Error>: Exception Handler bean not found");
                    throw new BeanNotFoundException(this.getClass().getSimpleName());
                }
            } else {
                CtrlDef ctrlDef = this.getClass().getAnnotation(CtrlDef.class);
                if (ctrlDef != null) {
                    this.exceptionHandler = applicationContextService.getBean(ctrlDef.handler());
                    if (this.exceptionHandler == null) {
                        log.error("<Error>: Exception Handler bean not found");
                        throw new BeanNotFoundException(this.getClass().getSimpleName());
                    }
                } else {
                    log.error("<Error>: Exception Handler bean not defined, please use CtrlExHandler or CtrlDef annotations");
                    throw new ExceptionHandlerNotDefinedException(this.getClass().getSimpleName());
                }
            }
        }

        return this.exceptionHandler;
    }

    @Override
    public String handleExceptionMessage(Throwable throwable) {
        if (exceptionHandler() != null) {
            return exceptionHandler().handleError(throwable);
        }
        return throwable.toString();
    }

    @Override
    public ResponseEntity getBackExceptionResponse(Throwable e) {
        log.error("<Error>: Exception {}", e);
        return ResponseFactory.ResponseInternalError(exceptionHandler().handleError(e));
    }
}
