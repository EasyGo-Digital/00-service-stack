package eu.easygoit.model.extendable;

import eu.easygoit.constants.DomainConstants;
import eu.easygoit.enums.IEnumAccountSystemStatus;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.model.ICodifiable;
import eu.easygoit.model.ISAASEntity;
import eu.easygoit.model.jakarta.AuditableCancelableEntity;
import eu.easygoit.model.schema.ComSchemaColumnConstantName;
import eu.easygoit.model.schema.ComSchemaConstantSize;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * The type Account model.
 *
 * @param <T> the type parameter
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AccountModel<T extends Serializable> extends AuditableCancelableEntity<T> implements ISAASEntity, ICodifiable {

    //@Convert(converter = LowerCaseConverter.class)
    @Column(name = ComSchemaColumnConstantName.C_CODE, length = ComSchemaConstantSize.CODE, updatable = false, nullable = false)
    private String code;
    //@Convert(converter = LowerCaseConverter.class)
    @ColumnDefault("'" + DomainConstants.DEFAULT_DOMAIN_NAME + "'")
    @Column(name = ComSchemaColumnConstantName.C_DOMAIN, length = ComSchemaConstantSize.DOMAIN, updatable = false, nullable = false)
    private String domain;
    @Column(name = ComSchemaColumnConstantName.C_EMAIL, length = ComSchemaConstantSize.EMAIL, nullable = false)
    @Email(message = "email.should.be.valid")
    private String email;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ENABLED'")
    @Column(name = ComSchemaColumnConstantName.C_ADMIN_STATUS, length = IEnumBinaryStatus.STR_ENUM_SIZE, nullable = false)
    private IEnumBinaryStatus.Types adminStatus = IEnumBinaryStatus.Types.ENABLED;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'IDLE'")
    @Column(name = ComSchemaColumnConstantName.C_SYSTEM_STATUS, length = IEnumAccountSystemStatus.STR_ENUM_SIZE, nullable = false)
    private IEnumAccountSystemStatus.Types systemStatus = IEnumAccountSystemStatus.Types.IDLE;
}
