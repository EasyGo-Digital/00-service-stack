package eu.easygoit.com.rest.controller;

import eu.easygoit.com.rest.service.ICrudServiceUtils;
import eu.easygoit.dto.IIdentifiableDto;
import eu.easygoit.exception.BeanNotFoundException;
import eu.easygoit.exception.MapperNotDefinedException;
import eu.easygoit.exception.ServiceNotDefinedException;
import eu.easygoit.mapper.EntityMapper;
import eu.easygoit.model.IIdEntity;

/**
 * The interface Crud controller utils.
 *
 * @param <T>     the type parameter
 * @param <MIND>  the type parameter
 * @param <FULLD> the type parameter
 * @param <S>     the type parameter
 */
public interface ICrudControllerUtils<T extends IIdEntity,
        MIND extends IIdentifiableDto,
        FULLD extends MIND,
        S extends ICrudServiceUtils<T>> {

    /**
     * Mapper entity mapper.
     *
     * @return the entity mapper
     * @throws BeanNotFoundException     the bean not found exception
     * @throws MapperNotDefinedException the mapper not defined exception
     */
    EntityMapper<T, FULLD> mapper() throws BeanNotFoundException, MapperNotDefinedException;

    /**
     * Min dto mapper entity mapper.
     *
     * @return the entity mapper
     * @throws BeanNotFoundException     the bean not found exception
     * @throws MapperNotDefinedException the mapper not defined exception
     */
    EntityMapper<T, MIND> minDtoMapper() throws BeanNotFoundException, MapperNotDefinedException;

    /**
     * Crud service s.
     *
     * @return the s
     * @throws BeanNotFoundException      the bean not found exception
     * @throws ServiceNotDefinedException the service not defined exception
     */
    S crudService() throws BeanNotFoundException, ServiceNotDefinedException;
}
