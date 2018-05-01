package br.com.gbvbahia.ecommerce.controllers.helpers;

import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ItemScreen implements Serializable {

    private Serializable id;
    private String imgName;
    private String imgWidth;
    private String imgHeight;
    private String keyPicture;
    private String name;
    private String value;
    private String description;
    private final Class clazz;
    private float price;
    private boolean rendered = true;
    private List<ItemScreen> subItems;

    public ItemScreen(Class clazz, KeyPicture keyPicture) {
        this.clazz = clazz;
        if(keyPicture != null) {
            this.keyPicture = keyPicture.name();
            this.imgHeight = keyPicture.getHeight();
            this.imgWidth = keyPicture.getWidth();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemScreen itemScreen = (ItemScreen) o;
        return Objects.equals(getId(), itemScreen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    //=====================
    // Getters and Setters
    //=====================

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getImgWidth() {
        return imgWidth;
    }

    public String getImgHeight() {
        return imgHeight;
    }
    
    public String getKeyPicture() {
        return keyPicture;
    }

    public List<ItemScreen> getSubItems() {
        if (subItems == null) {
            subItems = new ArrayList<>();
        }
        return subItems;
    }

    public void setSubItems(List<ItemScreen> subItems) {
        this.subItems = subItems;
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
