/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.enums.Generous;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "sub_category", schema = "products", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "category_id"})})
public class SubCategory implements Model<Long>, Comparable<SubCategory> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sub_category")
    @SequenceGenerator(sequenceName = "seq_sub_category", name = "seq_sub_category", schema = "products")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 40, nullable = false, unique = false)
    @Size(max = 40, min = 3)
    @NotNull
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    @Size(max = 250, min = 10)
    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "generous", length = 15)
    @NotNull
    private Generous generous;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "ID")
    @NotNull
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final SubCategory other = (SubCategory) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SubCategory{" + "id=" + id + ", name=" + name + ", description=" + description + ", generous=" + generous + '}';
    }

    @Override
    public int compareTo(SubCategory sub) {
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
