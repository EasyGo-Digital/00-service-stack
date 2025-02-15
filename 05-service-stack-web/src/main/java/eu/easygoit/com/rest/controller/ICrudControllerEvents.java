package eu.easygoit.com.rest.controller;

import eu.easygoit.dto.IIdentifiableDto;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.model.IIdEntity;

import java.util.List;

/**
 * The interface Crud controller events.
 *
 * @param <I>     the type parameter
 * @param <T>     the type parameter
 * @param <MIND>  the type parameter
 * @param <FULLD> the type parameter
 */
interface ICrudControllerEvents<I, T extends IIdEntity, MIND extends IIdentifiableDto, FULLD extends MIND> {

    /**
     * Before create full d.
     *
     * @param object the object
     * @return the full d
     */
    FULLD beforeCreate(FULLD object);

    /**
     * After create t.
     *
     * @param object the object
     * @return the t
     */
    T afterCreate(T object);

    /**
     * Before update full d.
     *
     * @param id     the id
     * @param object the object
     * @return the full d
     */
    FULLD beforeUpdate(I id, FULLD object);

    /**
     * After update t.
     *
     * @param object the object
     * @return the t
     */
    T afterUpdate(T object);

    /**
     * Before delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean beforeDelete(I id);

    /**
     * After delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean afterDelete(I id);

    /**
     * Before delete boolean.
     *
     * @param objects the objects
     * @return the boolean
     */
    boolean beforeDelete(List<FULLD> objects);

    /**
     * After delete boolean.
     *
     * @param objects the objects
     * @return the boolean
     */
    boolean afterDelete(List<FULLD> objects);

    /**
     * After find by id full d.
     *
     * @param object the object
     * @return the full d
     */
    FULLD afterFindById(FULLD object);

    /**
     * After find all full list.
     *
     * @param requestContext the request context
     * @param list           the list
     * @return the list
     */
    List<FULLD> afterFindAllFull(RequestContextDto requestContext, List<FULLD> list);

    /**
     * After find all list.
     *
     * @param requestContext the request context
     * @param list           the list
     * @return the list
     */
    List<MIND> afterFindAll(RequestContextDto requestContext, List<MIND> list);
}
