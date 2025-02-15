package eu.easygoit.repository;

import eu.easygoit.annotation.IgnoreRepository;
import eu.easygoit.model.IIdEntity;
import eu.easygoit.model.extendable.LocaleMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

/**
 * The interface Message model repository.
 *
 * @param <T> the type parameter
 * @param <I> the type parameter
 */
@IgnoreRepository
@NoRepositoryBean
public interface MessageModelRepository<T extends IIdEntity, I extends Serializable>
        extends JpaRepository<T, I> {

    /**
     * Find by code ignore case and locale optional.
     *
     * @param code   the code
     * @param locale the locale
     * @return the optional
     */
    Optional<LocaleMessageModel> findByCodeIgnoreCaseAndLocale(String code, String locale);
}
