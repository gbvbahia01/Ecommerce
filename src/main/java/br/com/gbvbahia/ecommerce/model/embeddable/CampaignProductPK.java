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
public class CampaignProductPK implements Serializable {

	private static final long serialVersionUID = 5557638472224841509L;

	@Column(name = "campaign_id", nullable = false)
    @NotNull
    private Long idCampaign;

    @Column(name = "product_id", nullable = false)
    @NotNull
    private Long idProduct;

    public Long getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Long idCampaign) {
        this.idCampaign = idCampaign;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public CampaignProductPK() {
    }

    public CampaignProductPK(Long idCampaign, Long idProduct) {
        this.idCampaign = idCampaign;
        this.idProduct = idProduct;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idCampaign);
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
        final CampaignProductPK other = (CampaignProductPK) obj;
        if (!Objects.equals(this.idCampaign, other.idCampaign)) {
            return false;
        }
        if (!Objects.equals(this.idProduct, other.idProduct)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CapanhaProdutoPK{" + "idCampanha=" + idCampaign + ", idProduto=" + idProduct + '}';
    }

}
