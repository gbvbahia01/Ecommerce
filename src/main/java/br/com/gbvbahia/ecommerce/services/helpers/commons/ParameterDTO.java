package br.com.gbvbahia.ecommerce.services.helpers.commons;

import java.io.Serializable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18?
 */
public class ParameterDTO implements Serializable {

    private String key;
    private String value;
    private String description;
    private boolean activated = false;

    public ParameterDTO() {
        super();
    }

    public ParameterDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ParameterDTO(String key, String value, boolean activated) {
        this.key = key;
        this.value = value;
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "ParameterDTO {" +
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
