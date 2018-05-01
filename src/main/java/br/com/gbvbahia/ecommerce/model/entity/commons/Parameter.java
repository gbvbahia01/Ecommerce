package br.com.gbvbahia.ecommerce.model.entity.commons;

import br.com.gbvbahia.ecommerce.model.cotract.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
@Entity
@Table(name = "parameter", schema = "commons")
public class Parameter implements Model<String> {

    @Id
    @Size(max = 30, min = 1)
    @NotNull
    @Column(name = "key")
    private String key;

    @Size(max = 500, min = 1)
    @NotNull
    @Column(name = "value", length = 500, nullable = false)
    private String value;

    @Column(name = "description", length = 250)
    private String description;

    /**
     * Some configuration can be disabled, NOT all.
     */
    @Column(name = "active", nullable = false)
    private boolean activated = false;

    public Parameter() {
        super();
    }

    public Parameter(@Size(max = 30, min = 1) @NotNull String key,
                     @Size(max = 30, min = 1) @NotNull String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter that = (Parameter) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return getKey();
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
