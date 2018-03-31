/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.embeddable.ProductPromotionPK;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "product_promotion", schema = "products")
public class ProductPromotion implements Model<ProductPromotionPK> {

    @EmbeddedId
    private ProductPromotionPK id = new ProductPromotionPK();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "ID", updatable = false, insertable = false)
    private Product product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "ID", updatable = false, insertable = false)
    private Promotion promotion;

    @Column(name = "active", nullable = false)
    private boolean active = false;

    @Override
    public ProductPromotionPK getId() {
        return id;
    }

    public void setId(ProductPromotionPK id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final ProductPromotion other = (ProductPromotion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
