package br.com.gbvbahia.ecommerce.controllers.helpers;

import java.io.Serializable;
import java.util.Objects;

public class ItemScreen<ID extends Serializable> implements Serializable {

    private ID id;
    private String imgName;
    private String imgWidth;
    private String imgHeight;
    private String name;
    private String description;
    private final Class clazz;
    private float price;


    public ItemScreen(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemScreen<?> itemScreen = (ItemScreen<?>) o;
        return Objects.equals(getId(), itemScreen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    //=====================
    // Getters and Setters
    //=====================

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
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

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }
}
