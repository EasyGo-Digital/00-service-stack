package eu.easygoit.dto.extendable;

import eu.easygoit.dto.IImageUploadDto;
import eu.easygoit.enums.IEnumBinaryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * The type Customer model dto.
 *
 * @param <T> the type parameter
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class CustomerModelDto<T extends Serializable> extends AbstractAuditableDto<T> implements IImageUploadDto {

    private String name;
    private String description;
    private String url;
    private String email;
    private String phoneNumber;
    private String imagePath;
    private String domain;
    @Builder.Default
    private IEnumBinaryStatus.Types adminStatus = IEnumBinaryStatus.Types.ENABLED;
}
