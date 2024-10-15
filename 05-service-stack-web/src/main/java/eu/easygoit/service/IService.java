package eu.easygoit.service;

import eu.easygoit.model.IIdEntity;
import eu.easygoit.model.extendable.NextCodeModel;

/**
 * The interface Service.
 */
public interface IService {

    /**
     * Init code generator t.
     *
     * @param <T> the type parameter
     * @return the t
     */
    <T extends NextCodeModel> T initCodeGenerator();

    /**
     * Gets next code.
     *
     * @return the next code
     */
    String getNextCode();

    /**
     * Before persist e.
     *
     * @param <E>    the type parameter
     * @param entity the entity
     * @return the e
     */
    <E extends IIdEntity> E beforePersist(E entity);
}
