package eu.easygoit.com.rest.service.impl;

import eu.easygoit.com.rest.service.IMultiFileServiceMethods;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.encrypt.helper.CRC16;
import eu.easygoit.encrypt.helper.CRC32;
import eu.easygoit.exception.ObjectNotFoundException;
import eu.easygoit.model.*;
import eu.easygoit.repository.JpaPagingAndSortingRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Multi file service.
 *
 * @param <I> the type parameter
 * @param <T> the type parameter
 * @param <L> the type parameter
 * @param <R> the type parameter
 */
@Slf4j
public abstract class MultiFileService<I, T extends IMultiFileEntity & IIdEntity, L extends ILinkedFile & ICodifiable & IIdEntity, R extends JpaPagingAndSortingRepository>
        extends MultiFileServiceSubMethods<I, T, L, R>
        implements IMultiFileServiceMethods<I, T> {

    private final Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    private final Class<L> linkedFileClass = (Class<L>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];

    @Override
    public List<L> uploadAdditionalFiles(I parentId, MultipartFile[] files) throws IOException {
        T entity = findById(parentId);
        if (entity != null) {
            for (MultipartFile file : files) {
                this.uploadAdditionalFile(parentId, file);
            }
            return entity.getAdditionalFiles();
        } else {
            throw new ObjectNotFoundException(persistentClass.getSimpleName() + " with id: " + parentId);
        }
    }

    @Override
    public List<L> uploadAdditionalFile(I parentId, MultipartFile file) throws IOException {
        T entity = findById(parentId);
        if (entity != null) {
            if (file != null && !file.isEmpty()) {
                try {
                    if (file != null && !file.isEmpty()) {
                        L linkedFile = linkedFileClass.newInstance();
                        linkedFile.setCode(this.getNextCode());
                        if (ISAASEntity.class.isAssignableFrom(entity.getClass()) && ISAASEntity.class.isAssignableFrom(linkedFile.getClass())) {
                            ((ISAASEntity) linkedFile).setDomain(((ISAASEntity) entity).getDomain());
                        }
                        linkedFile.setOriginalFileName(file.getOriginalFilename());
                        linkedFile.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
                        linkedFile.setPath(this.getUploadDirectory() +
                                File.separator + (ISAASEntity.class.isAssignableFrom(entity.getClass()) ? ((ISAASEntity) entity).getDomain() : DomainConstants.DEFAULT_DOMAIN_NAME) +
                                File.separator + persistentClass.getSimpleName().toLowerCase() + File.separator + "additional");
                        linkedFile.setMimetype(file.getContentType());
                        linkedFile.setCrc16(CRC16.calculate(file.getBytes()));
                        linkedFile.setCrc32(CRC32.calculate(file.getBytes()));
                        linkedFile.setSize(file.getSize());
                        linkedFile.setVersion(1L);

                        //Uploading file
                        linkedFile = this.beforeUpload((ISAASEntity.class.isAssignableFrom(entity.getClass())
                                        ? ((ISAASEntity) entity).getDomain()
                                        : DomainConstants.DEFAULT_DOMAIN_NAME),
                                linkedFile,
                                file);
                        linkedFile = subUploadFile(file, linkedFile);
                        this.afterUpload((ISAASEntity.class.isAssignableFrom(entity.getClass())
                                        ? ((ISAASEntity) entity).getDomain()
                                        : DomainConstants.DEFAULT_DOMAIN_NAME),
                                linkedFile,
                                file);

                        if (CollectionUtils.isEmpty(entity.getAdditionalFiles())) {
                            entity.setAdditionalFiles(new ArrayList<>());
                        }
                        entity.getAdditionalFiles().add(linkedFile);
                    } else {
                        log.warn("Upload file ({}) :File is null or empty", this.persistentClass.getSimpleName());
                    }
                } catch (Exception e) {
                    log.error("Update additional files failed : ", e);
                    //throw new RemoteCallFailedException(e);
                }
            }

            entity = this.update(entity);
            return entity.getAdditionalFiles();
        } else {
            throw new ObjectNotFoundException(persistentClass.getSimpleName() + " with id: " + parentId);
        }
    }

    @Override
    public Resource downloadFile(I parentId, I fileId, Long version) throws IOException {
        T entity = findById(parentId);
        if (entity != null) {
            L linkedFile = (L) entity.getAdditionalFiles().stream()
                    .filter(item -> ((L) item).getId().equals(fileId)).findAny()
                    .orElse(null);
            if (linkedFile != null) {
                return subDownloadFile(linkedFile, version);
            } else {
                throw new ObjectNotFoundException(this.linkedFileClass.getSimpleName() + " with id " + fileId);
            }

        } else {
            throw new ObjectNotFoundException(this.persistentClass.getSimpleName() + " with id " + parentId);
        }
    }

    @Override
    public boolean deleteAdditionalFile(I parentId, I fileId) throws IOException {
        T entity = findById(parentId);
        if (entity != null) {
            L linkedFile = (L) entity.getAdditionalFiles().stream()
                    .filter(item -> ((L) item).getId().equals(fileId)).findAny()
                    .orElse(null);
            if (linkedFile != null) {
                entity.getAdditionalFiles().removeIf(elm -> ((L) elm).getId().equals(fileId));
                subDeleteFile(linkedFile);
                this.update(entity);
                return true;
            } else {
                throw new FileNotFoundException(linkedFileClass.getSimpleName() + " with id " + fileId);
            }
        } else {
            throw new ObjectNotFoundException(persistentClass.getSimpleName() + " with id: " + parentId);
        }
    }

    /**
     * Before upload l.
     *
     * @param domain the domain
     * @param entity the entity
     * @param file   the file
     * @return the l
     * @throws IOException the io exception
     */
    public L beforeUpload(String domain, L entity, MultipartFile file) throws IOException {
        return entity;
    }

    /**
     * After upload l.
     *
     * @param domain the domain
     * @param entity the entity
     * @param file   the file
     * @return the l
     * @throws IOException the io exception
     */
    public L afterUpload(String domain, L entity, MultipartFile file) throws IOException {
        return entity;
    }

    /**
     * Gets upload directory.
     *
     * @return the upload directory
     */
    protected abstract String getUploadDirectory();
}
