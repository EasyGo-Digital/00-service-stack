package eu.easygoit.com.rest.controller;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.dto.IIdentifiableDto;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.model.IIdEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * The interface Crud controller sub methods.
 *
 * @param <I>     the type parameter
 * @param <T>     the type parameter
 * @param <MIND>  the type parameter
 * @param <FULLD> the type parameter
 * @param <S>     the type parameter
 */
public interface ICrudControllerSubMethods<I, T extends IIdEntity, MIND extends IIdentifiableDto, FULLD extends MIND, S extends ICrudServiceMethod<I, T>>
        extends ICrudControllerEvents<I, T, MIND, FULLD> {

    /**
     * Sub create response entity.
     *
     * @param object the object
     * @return the response entity
     */
    ResponseEntity<FULLD> subCreate(FULLD object);

    /**
     * Sub create response entity.
     *
     * @param objects the objects
     * @return the response entity
     */
    ResponseEntity<List<FULLD>> subCreate(List<FULLD> objects);

    /**
     * Sub update response entity.
     *
     * @param id     the id
     * @param object the object
     * @return the response entity
     */
    ResponseEntity<FULLD> subUpdate(I id, FULLD object);

    /**
     * Sub update response entity.
     *
     * @param objects the objects
     * @return the response entity
     */
    ResponseEntity<List<FULLD>> subUpdate(List<FULLD> objects);

    /**
     * Sub delete response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @return the response entity
     */
    ResponseEntity<?> subDelete(RequestContextDto requestContext, I id);

    /**
     * Sub delete response entity.
     *
     * @param requestContext the request context
     * @param objects        the objects
     * @return the response entity
     */
    ResponseEntity<?> subDelete(RequestContextDto requestContext, List<FULLD> objects);

    /**
     * Sub find all full response entity.
     *
     * @param requestContext the request context
     * @return the response entity
     */
    ResponseEntity<List<FULLD>> subFindAllFull(RequestContextDto requestContext);

    /**
     * Sub find all full response entity.
     *
     * @param requestContext the request context
     * @param page           the page
     * @param size           the size
     * @return the response entity
     */
    ResponseEntity<List<FULLD>> subFindAllFull(RequestContextDto requestContext, Integer page, Integer size);

    /**
     * Sub find all response entity.
     *
     * @param requestContext the request context
     * @return the response entity
     */
    ResponseEntity<List<MIND>> subFindAll(RequestContextDto requestContext);

    /**
     * Sub find all default response entity.
     *
     * @param requestContext the request context
     * @return the response entity
     */
    ResponseEntity<List<MIND>> subFindAllDefault(RequestContextDto requestContext);

    /**
     * Sub find all response entity.
     *
     * @param requestContext the request context
     * @param page           the page
     * @param size           the size
     * @return the response entity
     */
    ResponseEntity<List<MIND>> subFindAll(RequestContextDto requestContext, Integer page, Integer size);

    /**
     * Sub find by id response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @return the response entity
     */
    ResponseEntity<FULLD> subFindById(RequestContextDto requestContext, I id);


    /**
     * Sub get count response entity.
     *
     * @param requestContext the request context
     * @return the response entity
     */
    ResponseEntity<Long> subGetCount(RequestContextDto requestContext);

    /**
     * Sub find all filter criteria response entity.
     *
     * @return the response entity
     */
    ResponseEntity<Map<String, String>> subFindAllFilterCriteria();

    /**
     * Sub find all filtered by criteria response entity.
     *
     * @param requestContext the request context
     * @param criteria       the criteria
     * @return the response entity
     */
    ResponseEntity<List<FULLD>> subFindAllFilteredByCriteria(RequestContextDto requestContext, String criteria);

    /**
     * Sub find all filtered by criteria response entity.
     *
     * @param requestContext the request context
     * @param criteria       the criteria
     * @param page           the page
     * @param size           the size
     * @return the response entity
     */
    ResponseEntity<List<FULLD>> subFindAllFilteredByCriteria(RequestContextDto requestContext, String criteria, Integer page, Integer size);
}
