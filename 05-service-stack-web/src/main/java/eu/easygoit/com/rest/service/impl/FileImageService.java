package eu.easygoit.com.rest.service.impl;

import eu.easygoit.com.rest.service.IFileServiceMethods;
import eu.easygoit.com.rest.service.IImageServiceMethods;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.constants.LogConstants;
import eu.easygoit.exception.BadArgumentException;
import eu.easygoit.exception.EmptyPathException;
import eu.easygoit.exception.ObjectNotFoundException;
import eu.easygoit.exception.ResourceNotFoundException;
import eu.easygoit.helper.FileHelper;
import eu.easygoit.model.*;
import eu.easygoit.repository.JpaPagingAndSortingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Path;

/**
 * The type File image service.
 *
 * @param <I> the type parameter
 * @param <T> the type parameter
 * @param <R> the type parameter
 */
@Slf4j
public abstract class FileImageService<I, T extends IImageEntity & IFileEntity & IIdEntity & ICodifiable, R extends JpaPagingAndSortingRepository>
        extends FileService<I, T, R>
        implements IFileServiceMethods<I, T>, IImageServiceMethods<I, T> {

    private final Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    @Override
    @Transactional
    public T uploadImage(String senderDomain, I id, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            T entity = this.findById(id);
            if (entity != null) {
                entity.setImagePath(FileHelper.storeMultipartFile(this.getUploadDirectory() +
                                File.separator + (ISAASEntity.class.isAssignableFrom(entity.getClass()) ? ((ISAASEntity) entity).getDomain() : DomainConstants.DEFAULT_DOMAIN_NAME) +
                                File.separator + entity.getClass().getSimpleName().toLowerCase() +
                                File.separator + "image",
                        (ICodifiable.class.isAssignableFrom(entity.getClass()) ? ((ICodifiable) entity).getCode() : entity.getId()) + "_" + file.getOriginalFilename(), file, "png").toString());
                return this.update(entity);
            } else {
                throw new ObjectNotFoundException(this.persistentClass.getSimpleName() + " with id " + id);
            }
        } else {
            log.warn(LogConstants.EMPTY_FILE_PROVIDED);
        }

        throw new BadArgumentException(LogConstants.EMPTY_FILE_PROVIDED);
    }

    @Override
    public Resource downloadImage(I id) throws IOException {
        T entity = this.findById(id);
        if (entity != null) {
            if (StringUtils.hasText(entity.getImagePath())) {
                Resource resource = new UrlResource(Path.of(entity.getImagePath()).toUri());
                if (!resource.exists()) {
                    throw new ResourceNotFoundException("for path " + entity.getImagePath());
                }
                return resource;
            } else {
                throw new EmptyPathException("for id " + id);
            }
        } else {
            throw new ResourceNotFoundException("with id " + id);
        }
    }

    @Override
    @Transactional
    public T createWithImage(String senderDomain, T entity, MultipartFile file) throws IOException {
        //Check SAAS entity modification
        if (ISAASEntity.class.isAssignableFrom(persistentClass) && !DomainConstants.SUPER_DOMAIN_NAME.equals(senderDomain)) {
            ((ISAASEntity) entity).setDomain(senderDomain);
        }

        if (ICodifiable.class.isAssignableFrom(entity.getClass()) && !StringUtils.hasText(((ICodifiable) entity).getCode())) {
            ((ICodifiable) entity).setCode(this.getNextCode());
        }
        if (file != null && !file.isEmpty()) {
            entity.setImagePath(FileHelper.storeMultipartFile(this.getUploadDirectory() +
                            File.separator + (ISAASEntity.class.isAssignableFrom(entity.getClass()) ? ((ISAASEntity) entity).getDomain() : DomainConstants.DEFAULT_DOMAIN_NAME) +
                            File.separator + entity.getClass().getSimpleName().toLowerCase() +
                            File.separator + "image",
                    file.getOriginalFilename() + "_" + (ICodifiable.class.isAssignableFrom(entity.getClass()) ? ((ICodifiable) entity).getCode() : entity.getId()), file, "png").toString());
        } else {
            log.warn("File is null or empty");
        }
        return this.create(entity);
    }

    @Override
    @Transactional
    public T updateWithImage(String senderDomain, T entity, MultipartFile file) throws IOException {
        //Check SAAS entity modification
        if (ISAASEntity.class.isAssignableFrom(persistentClass) && !DomainConstants.SUPER_DOMAIN_NAME.equals(senderDomain)) {
            ((ISAASEntity) entity).setDomain(senderDomain);
        }

        if (file != null && !file.isEmpty()) {
            entity.setImagePath(FileHelper.storeMultipartFile(this.getUploadDirectory() +
                            File.separator + (ISAASEntity.class.isAssignableFrom(entity.getClass()) ? ((ISAASEntity) entity).getDomain() : DomainConstants.DEFAULT_DOMAIN_NAME) +
                            File.separator + entity.getClass().getSimpleName().toLowerCase() +
                            File.separator + "image",
                    file.getOriginalFilename() + "_" + (ICodifiable.class.isAssignableFrom(entity.getClass()) ? ((ICodifiable) entity).getCode() : entity.getId()), file, "png").toString());
        } else {
            entity.setImagePath(this.findById((I) entity.getId()).getImagePath());
            log.warn("File is null or empty");
        }
        return this.update(entity);
    }

    protected abstract String getUploadDirectory();
}
