package eu.easygoit.model.extendable;

import eu.easygoit.constants.DomainConstants;
import eu.easygoit.model.IIdEntity;
import eu.easygoit.model.ISAASEntity;
import eu.easygoit.model.schema.ComSchemaColumnConstantName;
import eu.easygoit.model.schema.ComSchemaConstantSize;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

/**
 * The type Next code model.
 *
 * @param <T> the type parameter
 */
@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class NextCodeModel<T extends Serializable> implements IIdEntity<T>, ISAASEntity {

    //@Convert(converter = LowerCaseConverter.class)
    @ColumnDefault("'" + DomainConstants.DEFAULT_DOMAIN_NAME + "'")
    @Column(name = ComSchemaColumnConstantName.C_DOMAIN, length = ComSchemaConstantSize.DOMAIN, updatable = false, nullable = false)
    private String domain;
    @Column(name = ComSchemaColumnConstantName.C_ENTITY, nullable = false)
    private String entity;
    @Column(name = ComSchemaColumnConstantName.C_ATTRIBUTE, nullable = false)
    private String attribute;
    @Column(name = ComSchemaColumnConstantName.C_PREFIX, length = ComSchemaConstantSize.CODE)
    private String prefix;
    @Column(name = ComSchemaColumnConstantName.C_SUFFIX, length = ComSchemaConstantSize.CODE)
    private String suffix;
    @Builder.Default
    @Column(name = ComSchemaColumnConstantName.C_VALUE, nullable = false)
    private Long value = 0L;
    @Builder.Default
    @Column(name = ComSchemaColumnConstantName.C_VALUE_LENGTH, nullable = false)
    private Long valueLength = 6L;
    @Builder.Default
    @Column(name = ComSchemaColumnConstantName.C_INCREMENT, nullable = false)
    private Integer increment = 1;

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return ((prefix != null ? prefix.trim() : "")
                + String.format("%1$" + (valueLength != null ? valueLength : 6L) + "s", (value != null ? value : 0L))
                + (suffix != null ? suffix.trim() : ""))
                .replace(" ", "0");
    }

    /**
     * Next code next code model.
     *
     * @return the next code model
     */
    public NextCodeModel nextCode() {
        value += (increment != null ? increment : 1);
        return this;
    }
}
