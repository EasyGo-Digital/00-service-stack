package eu.easygoit.com.rest.api;


import eu.easygoit.constants.JwtConstants;
import eu.easygoit.constants.RestApiConstants;
import eu.easygoit.dto.IIdentifiableDto;
import eu.easygoit.dto.IImageUploadDto;
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

import java.io.IOException;

/**
 * The interface Upload image api.
 *
 * @param <I> the type parameter
 * @param <D> the type parameter
 */
public interface IUploadImageApi<I, D extends IIdentifiableDto & IImageUploadDto> {


    /**
     * Download image response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @return the response entity
     * @throws IOException the io exception
     */
    @Operation(summary = "Download the image by linked object identifier",
            description = "Download the image by linked object identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class))})
    })
    @GetMapping(path = "/image/download/{id}")
    ResponseEntity<Resource> downloadImage(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                           @PathVariable(name = RestApiConstants.ID) I id) throws IOException;

    /**
     * Create with image response entity.
     *
     * @param requestContext the request context
     * @param file           the file
     * @param dto            the dto
     * @return the response entity
     */
    @Operation(summary = "Create a new object and upload linked image file",
            description = "Create a new object and upload linked image file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @PostMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> createWithImage(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                      @Valid @RequestBody MultipartFile file, D dto);

    /**
     * Update with image response entity.
     *
     * @param requestContext the request context
     * @param file           the file
     * @param dto            the dto
     * @return the response entity
     */
    @Operation(summary = "Upload a new image file and update the linked object",
            description = "Upload a new image file and update the linked object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @PutMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> updateWithImage(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                      @Valid @RequestBody MultipartFile file, D dto);

    /**
     * Upload image response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @param file           the file
     * @return the response entity
     */
    @Operation(summary = "Upload a new image file and link it to an object with object identifier",
            description = "Upload a new image file and link it to an object with object identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @PostMapping(path = "/image/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<D> uploadImage(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                  @PathVariable(name = RestApiConstants.ID) I id,
                                  @Valid @RequestBody MultipartFile file);
}
