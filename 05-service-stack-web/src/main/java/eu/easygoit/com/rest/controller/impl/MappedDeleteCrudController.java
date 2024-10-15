package eu.easygoit.com.rest.controller.impl;

import eu.easygoit.com.rest.api.IMappedDeleteCrudApi;
import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.dto.IIdentifiableDto;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.model.IIdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

/**
 * The type Mapped delete crud controller.
 *
 * @param <I>     the type parameter
 * @param <T>     the type parameter
 * @param <MIND>  the type parameter
 * @param <FULLD> the type parameter
 * @param <S>     the type parameter
 */
@Slf4j
public abstract class MappedDeleteCrudController<I, T extends IIdEntity,
        MIND extends IIdentifiableDto,
        FULLD extends MIND,
        S extends ICrudServiceMethod<I, T>>
        extends CrudControllerSubMethods<I, T, MIND, FULLD, S>
        implements IMappedDeleteCrudApi<I> {

    @Override
    public final ResponseEntity<?> delete(RequestContextDto requestContext,
                                          I id) {
        return subDelete(requestContext, id);
    }
}
