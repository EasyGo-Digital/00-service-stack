package eu.easygoit.com.rest.api;

import eu.easygoit.constants.JwtConstants;
import eu.easygoit.constants.RestApiConstants;
import eu.easygoit.dto.IFileUploadDto;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.extendable.IdentifiableDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Upload file api.
 *
 * @param <I> the type parameter
 * @param <D> the type parameter
 */
public interface IUploadFileApi<I, D extends IFileUploadDto> {


    /**
     * Download file response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @param version        the version
     * @return the response entity
     */
    @Operation(summary = "Download a file by object id and version Api",
            description = "Download a file by object id and version")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class))})
    })
    @GetMapping(path = "/file/download")
    ResponseEntity<Resource> downloadFile(
            @RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
            @RequestParam(name = RestApiConstants.ID) I id,
            @RequestParam(name = RestApiConstants.VERSION) Long version);

    /**
     * Upload file response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @param file           the file
     * @return the response entity
     */
    @Operation(summary = "Upload a file by object id and version Api",
            description = "Upload a file by object id and version")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @PutMapping(path = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<D> uploadFile(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                 @RequestParam(name = RestApiConstants.ID) I id,
                                 @Valid @RequestBody MultipartFile file);

    /**
     * Create with file response entity.
     *
     * @param requestContext the request context
     * @param fileUpload     the file upload
     * @return the response entity
     */
    @Operation(summary = "Create a new object and upload linked file",
            description = "Create a new object and upload linked file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @PostMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<D> createWithFile(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                     //@RequestParam(name = RestApiConstants.DOMAIN_NAME) String domain,
                                     @ModelAttribute(RestApiConstants.FILE_UPLOAD) D fileUpload);


    /**
     * Update with file response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @param fileUpload     the file upload
     * @return the response entity
     */
    @Operation(summary = "Upload a new file and update the linked object",
            description = "Upload a new file and update the linked object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @PutMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<D> updateWithFile(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                     @RequestParam(name = RestApiConstants.ID) I id,
                                     @ModelAttribute(RestApiConstants.FILE_UPLOAD) D fileUpload);

}
