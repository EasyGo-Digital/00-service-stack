package eu.easygoit.dto.common;

import eu.easygoit.dto.extendable.AbstractAuditableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Bpm event response dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BpmEventResponseDto extends AbstractAuditableDto<Long> {

    private Boolean accepted;
    private String status;

}
