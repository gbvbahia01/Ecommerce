/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gbvbahia.ecommerce.model.cotract.Model;

/**
 *
 * Represents if the product will be displayed in some where. Use detail as a key to mark a place.
 * Use active to mark if can be showed.
 *
 * @author Guilherme
 */
@Entity
@Table(name = "promotion", schema = "products")
public class Promotion implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "detail", length = 150)
    @Size(max = 90)//Layout
    private String detail;

    @Column(name = "tag", length = 30, nullable = false, unique = true)
    @Size(max = 30)//Layout
    @NotNull
    private String tag;

    @Column(name = "active", nullable = false)
    private boolean active = false;

    @OneToMany(mappedBy = "promotion")
    private Set<ProductPromotion> productPromotions;

    @ElementCollection
    @MapKeyColumn(name = "param", length = 150)
    @Column(name = "value", length = 150)
    @CollectionTable(schema = "products",
            name = "promotion_parameters",
            joinColumns = @JoinColumn(name = "id_promotion"))
    private Map<String, String> parameters;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Promotion other = (Promotion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", detail=" + detail + ", active=" + active + ", parameters=" + parameters + '}';
    }

    //============================
    //Getters and Setters
    //============================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Set<ProductPromotion> getProductPromotions() {
        if (productPromotions == null) {
            productPromotions = new HashSet<>();
        }
        return productPromotions;
    }

    public void setProductPromotions(Set<ProductPromotion> productPromotions) {
        this.productPromotions = productPromotions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<String, String> getParameters() {
        if (parameters == null) {
            parameters = new HashMap<String, String>();
        }
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
