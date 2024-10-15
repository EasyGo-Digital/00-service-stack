package eu.easygoit.com.rest.service;

import eu.easygoit.exception.JpaRepositoryNotDefinedException;
import eu.easygoit.model.IIdEntity;
import org.springframework.data.repository.Repository;

/**
 * The interface Crud service utils.
 *
 * @param <T> the type parameter
 */
public interface ICrudServiceUtils<T extends IIdEntity> {

    /**
     * Repository repository.
     *
     * @return the repository
     * @throws JpaRepositoryNotDefinedException the jpa repository not defined exception
     */
    Repository repository() throws JpaRepositoryNotDefinedException;
}
