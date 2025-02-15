package eu.easygoit.com.rest.service.impl;

import eu.easygoit.com.rest.api.ILinkedFileApi;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.dto.common.LinkedFileRequestDto;
import eu.easygoit.dto.common.LinkedFileResponseDto;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type File service dms static methods.
 */
@Slf4j
public final class FileServiceDmsStaticMethods {

    /**
     * Upload linked file response dto.
     *
     * @param <T>               the type parameter
     * @param file              the file
     * @param entity            the entity
     * @param linkedFileService the linked file service
     * @return the linked file response dto
     * @throws IOException the io exception
     */
    static <T extends IFileEntity & IIdEntity & ICodifiable> LinkedFileResponseDto upload(MultipartFile file,
                                                                                          T entity,
                                                                                          ILinkedFileApi linkedFileService) throws IOException {
        ResponseEntity<LinkedFileResponseDto> result = linkedFileService.upload(//RequestContextDto.builder().build(),
                LinkedFileRequestDto.builder()
                        .domain((ISAASEntity.class.isAssignableFrom(entity.getClass())
                                ? ((ISAASEntity) entity).getDomain()
                                : DomainConstants.DEFAULT_DOMAIN_NAME))
                        .code(entity.getCode())
                        .path(File.separator + entity.getClass().getSimpleName().toLowerCase())
                        .tags(entity.getTags())
                        .categoryNames(List.of(entity.getClass().getSimpleName()))
                        .file(file)
                        .build());
        if (result.getStatusCode().is2xxSuccessful()) {
            log.info("File uploaded successfully {} with code {}", file.getOriginalFilename(), result.getBody().getCode());
            return result.getBody();
        }

        return null;
    }

    /**
     * Download resource.
     *
     * @param <T>               the type parameter
     * @param entity            the entity
     * @param version           the version
     * @param linkedFileService the linked file service
     * @return the resource
     * @throws IOException the io exception
     */
    static <T extends IFileEntity & IIdEntity & ICodifiable> Resource download(T entity, Long version, ILinkedFileApi linkedFileService) throws IOException {
        String domain = ISAASEntity.class.isAssignableFrom(entity.getClass())
                ? ((ISAASEntity) entity).getDomain()
                : DomainConstants.DEFAULT_DOMAIN_NAME;
        ResponseEntity<Resource> result = linkedFileService.download(RequestContextDto.builder().build(),
                domain,
                entity.getCode());
        if (result.getStatusCode().is2xxSuccessful()) {
            log.info("File downloaded successfully with domain {}  and code {}", domain, entity.getCode());
            return result.getBody();
        }

        return null;
    }

    /**
     * Delete boolean.
     *
     * @param <L>               the type parameter
     * @param entity            the entity
     * @param linkedFileService the linked file service
     * @return the boolean
     */
    public static <L extends ILinkedFile & ICodifiable & IIdEntity> boolean delete(L entity, ILinkedFileApi linkedFileService) {
        String domain = ISAASEntity.class.isAssignableFrom(entity.getClass())
                ? ((ISAASEntity) entity).getDomain()
                : DomainConstants.DEFAULT_DOMAIN_NAME;
        ResponseEntity<Boolean> result = linkedFileService.deleteFile(RequestContextDto.builder().build(),
                domain,
                entity.getCode());
        if (result.getStatusCode().is2xxSuccessful()) {
            log.info("File deleted successfully with domain {}  and code {}", domain, entity.getCode());
            return result.getBody();
        }

        return false;
    }
}
