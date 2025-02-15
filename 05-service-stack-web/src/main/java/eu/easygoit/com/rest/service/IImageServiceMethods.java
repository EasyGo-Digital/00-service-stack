package eu.easygoit.com.rest.service;

import eu.easygoit.model.IIdEntity;
import eu.easygoit.model.IImageEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The interface Image service methods.
 *
 * @param <I> the type parameter
 * @param <T> the type parameter
 */
public interface IImageServiceMethods<I, T extends IIdEntity & IImageEntity> {

    /**
     * Upload image t.
     *
     * @param senderDomain the sender domain
     * @param id           the id
     * @param image        the image
     * @return the t
     * @throws IOException the io exception
     */
    T uploadImage(String senderDomain, I id, MultipartFile image) throws IOException;

    /**
     * Download image resource.
     *
     * @param id the id
     * @return the resource
     * @throws IOException the io exception
     */
    Resource downloadImage(I id) throws IOException;

    /**
     * Create with image t.
     *
     * @param senderDomain the sender domain
     * @param entity       the entity
     * @param file         the file
     * @return the t
     * @throws IOException the io exception
     */
    T createWithImage(String senderDomain, T entity, MultipartFile file) throws IOException;

    /**
     * Update with image t.
     *
     * @param senderDomain the sender domain
     * @param entity       the entity
     * @param file         the file
     * @return the t
     * @throws IOException the io exception
     */
    T updateWithImage(String senderDomain, T entity, MultipartFile file) throws IOException;
}
