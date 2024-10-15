package eu.easygoit.annotation;

import eu.easygoit.com.rest.service.ICrudServiceUtils;
import eu.easygoit.exception.handler.IExceptionHandler;
import eu.easygoit.mapper.EntityMapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Ctrl def.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CtrlDef {
    /**
     * Handler class.
     *
     * @return the class
     */
    Class<? extends IExceptionHandler> handler(); // Data Exception Handler class

    /**
     * Mapper class.
     *
     * @return the class
     */
    Class<? extends EntityMapper> mapper(); // full dto / entity mapper class

    /**
     * Min mapper class.
     *
     * @return the class
     */
    Class<? extends EntityMapper> minMapper(); // min dto / entity mapper class

    /**
     * Service class.
     *
     * @return the class
     */
    Class<? extends ICrudServiceUtils> service(); // eu.easygoit.service class
}
