package eu.easygoit.com.rest.api;

import eu.easygoit.constants.JwtConstants;
import eu.easygoit.constants.RestApiConstants;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.extendable.IdentifiableDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Mapped delete crud api.
 *
 * @param <I> the type parameter
 */
public interface IMappedDeleteCrudApi<I> {

    /**
     * Delete response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @return the response entity
     */
    @Operation(summary = "Delete object by identifier Api",
            description = "Delete object by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @DeleteMapping(path = "")
    ResponseEntity<?> delete(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                             @RequestParam(name = RestApiConstants.ID) I id);
}
