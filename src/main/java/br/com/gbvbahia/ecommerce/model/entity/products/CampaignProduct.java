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
import br.com.gbvbahia.ecommerce.model.embeddable.CampaignProductPK;
/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "campaign_product", schema = "products")
public class CampaignProduct implements Model<CampaignProductPK> {

	@EmbeddedId
    private CampaignProductPK id = new CampaignProductPK();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "campaign_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Campaign campaign;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Product product;

    @Column(name = "active", nullable = false)
    private boolean active = false;

    public CampaignProductPK getId() {
        return id;
    }

    public void setId(CampaignProductPK id) {
        this.id = id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final CampaignProduct other = (CampaignProduct) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CampaignProduct{" + "id=" + id + ", campaign=" + campaign + ", product=" + product + ", active=" + active + '}';
    }
    
    
}
