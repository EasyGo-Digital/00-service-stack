package eu.easygoit.repository;

import eu.easygoit.annotation.IgnoreRepository;
import eu.easygoit.model.IIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * The interface Jpa paging and sorting repository.
 *
 * @param <T> the type parameter
 * @param <I> the type parameter
 */
@IgnoreRepository
@NoRepositoryBean
public interface JpaPagingAndSortingRepository<T extends IIdEntity, I extends Serializable>
        extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {
}
