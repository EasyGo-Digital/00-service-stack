package eu.easygoit.repository;

import eu.easygoit.annotation.IgnoreRepository;
import eu.easygoit.model.ICodifiable;
import eu.easygoit.model.IIdEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The interface Jpa paging and sorting codifiable repository.
 *
 * @param <T> the type parameter
 * @param <I> the type parameter
 */
@IgnoreRepository
@NoRepositoryBean
public interface JpaPagingAndSortingCodifiableRepository<T extends ICodifiable & IIdEntity, I extends Serializable>
        extends JpaPagingAndSortingRepository<T, I> {

    /**
     * Exists by code ignore case boolean.
     *
     * @param code the code
     * @return the boolean
     */
    boolean existsByCodeIgnoreCase(String code);

    /**
     * Find by code ignore case optional.
     *
     * @param code the code
     * @return the optional
     */
    Optional<T> findByCodeIgnoreCase(String code);

    /**
     * Find by code ignore case in list.
     *
     * @param codeList the code list
     * @return the list
     */
    List<T> findByCodeIgnoreCaseIn(List<String> codeList);
}
