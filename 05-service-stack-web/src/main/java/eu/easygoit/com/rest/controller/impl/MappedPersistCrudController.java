package eu.easygoit.com.rest.controller.impl;

import eu.easygoit.com.rest.api.IMappedPersistCrudApi;
import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.dto.IIdentifiableDto;
import eu.easygoit.model.IIdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

/**
 * The type Mapped persist crud controller.
 *
 * @param <I>     the type parameter
 * @param <T>     the type parameter
 * @param <MIND>  the type parameter
 * @param <FULLD> the type parameter
 * @param <S>     the type parameter
 */
@Slf4j
public abstract class MappedPersistCrudController<I, T extends IIdEntity,
        MIND extends IIdentifiableDto,
        FULLD extends MIND,
        S extends ICrudServiceMethod<I, T>>
        extends CrudControllerSubMethods<I, T, MIND, FULLD, S>
        implements IMappedPersistCrudApi<I, FULLD> {

    public final ResponseEntity<FULLD> create(FULLD object) {
        return subCreate(object);
    }

    public final ResponseEntity<FULLD> update(I id, FULLD object) {
        return subUpdate(id, object);
    }
}
