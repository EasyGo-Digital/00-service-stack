package eu.easygoit.com.rest.service.impl;

import eu.easygoit.annotation.DmsLinkFileService;
import eu.easygoit.app.ApplicationContextService;
import eu.easygoit.com.rest.api.ILinkedFileApi;
import eu.easygoit.exception.LinkedFileServiceNotDefinedException;
import eu.easygoit.model.ICodifiable;
import eu.easygoit.model.IIdEntity;
import eu.easygoit.model.ILinkedFile;
import eu.easygoit.model.IMultiFileEntity;
import eu.easygoit.repository.JpaPagingAndSortingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Multi file service sub methods.
 *
 * @param <I> the type parameter
 * @param <T> the type parameter
 * @param <L> the type parameter
 * @param <R> the type parameter
 */
@Slf4j
public abstract class MultiFileServiceSubMethods<I, T extends IMultiFileEntity & IIdEntity, L extends ILinkedFile & ICodifiable & IIdEntity, R extends JpaPagingAndSortingRepository>
        extends CodifiableService<I, T, R> {

    @Autowired
    private ApplicationContextService applicationContextService;

    private ILinkedFileApi linkedFileApi;

    private ILinkedFileApi linkedFileService() throws LinkedFileServiceNotDefinedException {
        if (this.linkedFileApi == null) {
            DmsLinkFileService annotation = this.getClass().getAnnotation(DmsLinkFileService.class);
            if (annotation != null) {
                this.linkedFileApi = applicationContextService.getBean(annotation.value());
                if (this.linkedFileApi == null) {
                    log.error("<Error>: bean {} not found", annotation.value().getSimpleName());
                    throw new LinkedFileServiceNotDefinedException("Bean not found " + annotation.value().getSimpleName() + " not found");
                }
            } else {
                log.error("<Error>: Linked file service not defined for {}, local storage will be used!", this.getClass().getSimpleName());
            }
        }

        return this.linkedFileApi;
    }

    /**
     * Sub upload file l.
     *
     * @param file   the file
     * @param entity the entity
     * @return the l
     */
    final L subUploadFile(MultipartFile file, L entity) {
        try {
            ILinkedFileApi linkedFileService = this.linkedFileService();
            if (linkedFileService != null) {
                entity.setCode(FileServiceDmsStaticMethods.upload(file, entity, linkedFileService).getCode());
            } else {
                entity.setCode(FileServiceLocalStaticMethods.upload(file, entity));
            }
        } catch (Exception e) {
            log.error("Remote feign call failed : ", e);
        }

        return entity;
    }

    /**
     * Sub download file resource.
     *
     * @param entity  the entity
     * @param version the version
     * @return the resource
     */
    final Resource subDownloadFile(L entity, Long version) {
        try {
            ILinkedFileApi linkedFileService = this.linkedFileService();
            if (linkedFileService != null) {
                return FileServiceDmsStaticMethods.download(entity, version, linkedFileService);
            } else {
                return FileServiceLocalStaticMethods.download(entity, version);
            }
        } catch (Exception e) {
            log.error("Remote feign call failed : ", e);
        }
        return null;
    }

    /**
     * Sub delete file boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    final boolean subDeleteFile(L entity) {
        try {
            ILinkedFileApi linkedFileService = this.linkedFileService();
            if (linkedFileService != null) {
                return FileServiceDmsStaticMethods.delete(entity, linkedFileService);
            } else {
                return FileServiceLocalStaticMethods.delete(entity);
            }
        } catch (Exception e) {
            log.error("Remote feign call failed : ", e);
        }

        return false;
    }
}
