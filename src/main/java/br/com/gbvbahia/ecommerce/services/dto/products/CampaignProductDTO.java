/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.dto.products;

import br.com.gbvbahia.ecommerce.services.dto.embedded.CampaignProductPKDTO;

import java.io.Serializable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class CampaignProductDTO implements Serializable {

    private CampaignProductPKDTO id = new CampaignProductPKDTO();
    private CampaignDTO campaign;
    private ProductDTO product;
    private boolean active = false;

    public CampaignProductPKDTO getId() {
        return id;
    }

    public void setId(CampaignProductPKDTO id) {
        this.id = id;
    }

    public CampaignDTO getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignDTO campaign) {
        this.campaign = campaign;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CampaignProductDTO{" + "id=" + id + ", campaign=" + campaign
                + ", product=" + product + ", active=" + active + '}';
    }

}
