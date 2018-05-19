/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.products;

import br.com.gbvbahia.ecommerce.model.enums.Generous;

import java.io.Serializable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class SubCategoryDTO implements Serializable, Comparable<SubCategoryDTO> {

    private Long id;
    private String name;
    private String description;
    private Generous generous;
    private CategoryDTO category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Generous getGenerous() {
        return generous;
    }

    public void setGenerous(Generous generous) {
        this.generous = generous;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" + "id=" + id + ", name=" + name + ", description=" + description + ", generous=" + generous + '}';
    }

    @Override
    public int compareTo(SubCategoryDTO sub) {
        if(sub == null) {
            return 1;
        }
        if(this.category == null) {
            return - 1;
        }
        int i = this.category.compareTo(sub.getCategory());
        if (i != 0 || this.name == null) {
            return i;
        }
        return this.name.compareToIgnoreCase(sub.getName());
    }

}
