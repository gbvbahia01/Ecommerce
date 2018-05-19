/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.embedded;


import java.io.Serializable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class CampaignProductPKDTO implements Serializable {

    private Long idCampaign;
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

    public CampaignProductPKDTO() {
    }

    public CampaignProductPKDTO(Long idCampaign, Long idProduct) {
        this.idCampaign = idCampaign;
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "CampaignProductPKDTO{" + "idCampanha=" + idCampaign + ", idProduto=" + idProduct + '}';
    }

}
