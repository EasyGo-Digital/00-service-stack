package eu.easygoit.service;

import eu.easygoit.constants.JwtConstants;
import eu.easygoit.constants.RestApiConstants;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.enums.IEnumCharSet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;


/**
 * The interface Key service api.
 */
public interface KeyServiceApi {

    /**
     * Generate random key response entity.
     *
     * @param requestContext the request context
     * @param length         the length
     * @param charSetType    the char set type
     * @return the response entity
     */
    @Operation(summary = "Generate random key Api",
            description = "Generate random key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    @GetMapping(path = "/random/{length}/{charSetType}")
    ResponseEntity<String> generateRandomKey(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                             @PathVariable(name = RestApiConstants.LENGTH) Integer length,
                                             @PathVariable(name = RestApiConstants.CHAR_SET_TYPE) IEnumCharSet.Types charSetType);

    /**
     * Renew key by name response entity.
     *
     * @param requestContext the request context
     * @param domain         the domain
     * @param keyName        the key name
     * @param length         the length
     * @param charSetType    the char set type
     * @return the response entity
     */
    @Operation(summary = "Renew key by name Api",
            description = "Renew key by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    @PostMapping(path = "/random/renew/{domain}/{keyName}/{length}/{charSetType}")
    ResponseEntity<String> renewKeyByName(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                          @PathVariable(name = RestApiConstants.DOMAIN_NAME) String domain,
                                          @PathVariable(name = RestApiConstants.KEY_NAME) String keyName,
                                          @PathVariable(name = RestApiConstants.LENGTH) Integer length,
                                          @PathVariable(name = RestApiConstants.CHAR_SET_TYPE) IEnumCharSet.Types charSetType);

    /**
     * Gets key by name.
     *
     * @param requestContext the request context
     * @param domain         the domain
     * @param keyName        the key name
     * @return the key by name
     */
    @Operation(summary = "Get key by name Api",
            description = "Get key by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    @GetMapping(path = "/random/get/{domain}/{keyName}")
    ResponseEntity<String> getKeyByName(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT, required = false) RequestContextDto requestContext,
                                        @PathVariable(name = RestApiConstants.DOMAIN_NAME) String domain,
                                        @PathVariable(name = RestApiConstants.KEY_NAME) String keyName);
}