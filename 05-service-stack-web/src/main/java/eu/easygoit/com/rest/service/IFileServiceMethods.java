package eu.easygoit.com.rest.service;

import eu.easygoit.model.IFileEntity;
import eu.easygoit.model.IIdEntity;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The interface File service methods.
 *
 * @param <I> the type parameter
 * @param <T> the type parameter
 */
public interface IFileServiceMethods<I, T extends IIdEntity & IFileEntity> {

    /**
     * Create with file t.
     *
     * @param senderDomain the sender domain
     * @param entity       the entity
     * @param file         the file
     * @return the t
     * @throws IOException the io exception
     */
    @Transactional
    T createWithFile(String senderDomain, T entity, MultipartFile file) throws IOException;

    /**
     * Update with file t.
     *
     * @param senderDomain the sender domain
     * @param id           the id
     * @param entity       the entity
     * @param file         the file
     * @return the t
     * @throws IOException the io exception
     */
    @Transactional
    T updateWithFile(String senderDomain, I id, T entity, MultipartFile file) throws IOException;

    /**
     * Upload file t.
     *
     * @param senderDomain the sender domain
     * @param id           the id
     * @param file         the file
     * @return the t
     * @throws IOException the io exception
     */
    @Transactional
    T uploadFile(String senderDomain, I id, MultipartFile file) throws IOException;

    /**
     * Download file resource.
     *
     * @param id      the id
     * @param version the version
     * @return the resource
     * @throws IOException the io exception
     */
    Resource downloadFile(I id, Long version) throws IOException;
}
