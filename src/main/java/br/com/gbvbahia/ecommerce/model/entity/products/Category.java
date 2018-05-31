/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gbvbahia.ecommerce.model.cotract.Model;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "category", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})}, schema = "products")
public class Category implements Model<Long>, Comparable<Category> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_category")
    @SequenceGenerator(sequenceName = "seq_category", name = "seq_category")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 40, nullable = false, unique = true)
    @Size(max = 40, min = 3)
    @NotNull
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    @Size(max = 250, min = 10)
    @NotNull
    private String description;
    
    @Column(name = "order_show", nullable = false)
    @Min(1)
    @NotNull
    private Integer priority = 1;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SubCategory> subCategories;

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

    public Set<SubCategory> getSubCategories() {
        if (subCategories == null) {
            subCategories = new HashSet<>();
        }
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategorys) {
        this.subCategories = subCategorys;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", description=" + description + ", orders=" + priority + '}';
    }

    @Override
    public int compareTo(Category cat) {
       if(cat == null) {
           return 1;
       }
       if(this.name == null) {
           return -1;
       }
       return this.name.compareToIgnoreCase(cat.getName());
    }
}
