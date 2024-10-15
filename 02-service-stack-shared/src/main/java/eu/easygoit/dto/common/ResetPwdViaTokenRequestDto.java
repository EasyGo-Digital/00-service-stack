package eu.easygoit.dto.common;


import eu.easygoit.dto.extendable.AbstractAuditableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Reset pwd via token request dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ResetPwdViaTokenRequestDto extends AbstractAuditableDto<Long> {

    private String token;
    private String password;
    private String fullName;
    private String application;
}
