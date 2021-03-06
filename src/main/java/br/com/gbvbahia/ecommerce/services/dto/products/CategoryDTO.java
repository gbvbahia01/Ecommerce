/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.dto.products;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class CategoryDTO implements Serializable, Comparable<CategoryDTO> {

    private Long id;
    private String name;
    private String description;
    private Integer priority = 1;
    private Set<SubCategoryDTO> subCategories;

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

    public Set<SubCategoryDTO> getSubCategories() {
        if (subCategories == null) {
            subCategories = new HashSet<>();
        }
        return subCategories;
    }

    public void setSubCategories(Set<SubCategoryDTO> subCategories) {
        this.subCategories = subCategories;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "id=" + id + ", name=" + name + ", description=" + description + ", orders=" + priority + '}';
    }

    @Override
    public int compareTo(CategoryDTO cat) {
       if(cat == null) {
           return 1;
       }
       if(this.name == null) {
           return -1;
       }
       return this.name.compareToIgnoreCase(cat.getName());
    }
}
