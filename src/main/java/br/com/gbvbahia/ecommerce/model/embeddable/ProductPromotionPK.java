/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.embeddable;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
@Embeddable
public class ProductPromotionPK implements  Serializable {

	private static final long serialVersionUID = -4609729375792506471L;

	@Column(name = "product_id", nullable = false)
    @NotNull
    private Long idProduct;

    @Column(name = "promotion_id", nullable = false)
    @NotNull
    private Long idPromotion;

    public ProductPromotionPK() {
    }

    public ProductPromotionPK(Long idProduct, Long idPromotion) {
        this.idProduct = idProduct;
        this.idPromotion = idPromotion;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(Long idPromotion) {
        this.idPromotion = idPromotion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idPromotion);
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
        final ProductPromotionPK other = (ProductPromotionPK) obj;
        if (!Objects.equals(this.idProduct, other.idProduct)) {
            return false;
        }
        if (!Objects.equals(this.idPromotion, other.idPromotion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductPromotionPK{" + "idProduct=" + idProduct + ", idPromotion=" + idPromotion + '}';
    }

}
