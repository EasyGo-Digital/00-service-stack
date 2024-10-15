package eu.easygoit.com.rest.service.impl;

import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.app.ApplicationContextService;
import eu.easygoit.com.rest.service.ICrudServiceUtils;
import eu.easygoit.exception.JpaRepositoryNotDefinedException;
import eu.easygoit.model.IIdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;

/**
 * The type Crud service utils.
 *
 * @param <T> the type parameter
 * @param <R> the type parameter
 */
@Slf4j
public abstract class CrudServiceUtils<T extends IIdEntity, R extends Repository>
        implements ICrudServiceUtils<T> {

    @Autowired
    private ApplicationContextService applicationContextServie;

    private R repository;

    @Override
    public final R repository() throws JpaRepositoryNotDefinedException {
        if (this.repository == null) {
            SrvRepo controllerDefinition = this.getClass().getAnnotation(SrvRepo.class);
            if (controllerDefinition != null) {
                this.repository = (R) applicationContextServie.getBean(controllerDefinition.value());
                if (this.repository == null) {
                    log.error("<Error>: bean {} not found", controllerDefinition.value().getSimpleName());
                    throw new JpaRepositoryNotDefinedException("JpaRepository " + controllerDefinition.value().getSimpleName() + " not found");
                }
            } else {
                log.error("<Error>: Repository bean not defined for {}", this.getClass().getSimpleName());
                throw new JpaRepositoryNotDefinedException("JpaRepository");
            }
        }

        return this.repository;
    }
}
