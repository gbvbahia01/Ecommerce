/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.products;

import br.com.gbvbahia.ecommerce.services.helpers.embedded.MeasuresDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String nameClean;
    private String branch;
    private String description;
    private String descriptionClean;
    private MeasuresDTO measures;
    private Set<ProductStockDTO> productStock;
    private Float price;
    private float campaignPrice;
    private float discount;
    private SubCategoryDTO subCategory;
    private Set<CampaignProductDTO> campaigns;
    private Set<ProductImageDTO> productImages;

    public CampaignDTO getActiveCampaign() {
        for (CampaignProductDTO cp : getCampaigns()) {
            if (cp.isActive()) {
                return cp.getCampaign();
            }
        }
        return null;
    }

    public ProductImageDTO searchImgBykeyPicture(String keyPicture) {
        try {
            return getProductImages().stream().filter(pi -> pi.getKeyPicture().equals(keyPicture)).findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "id=" + id + ", name=" + name + ", branch=" + branch + ", description=" + description
                + ", measures=" + measures + ", price=" + price + '}';
    }

    //============================
    //Getters and Setters
    //============================
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public String getDescription() {
        return description;
    }

    public MeasuresDTO getMeasures() {
        return measures;
    }

    public Float getPrice() {
        return price;
    }

    public SubCategoryDTO getSubCategory() {
        return subCategory;
    }

    public Set<CampaignProductDTO> getCampaigns() {
        if (campaigns == null) {
            campaigns = new HashSet<>();
        }
        return campaigns;
    }

    public Set<ProductImageDTO> getProductImages() {
        if (productImages == null) {
            productImages = new HashSet<>();
        }
        return productImages;
    }

    public String getNameClean() {
        return nameClean;
    }

    public String getDescriptionClean() {
        return descriptionClean;
    }

    public Set<ProductStockDTO> getProductStock() {
        if (productStock == null) {
            productStock = new HashSet<>();
        }
        return productStock;
    }

    public float getCampaignPrice() {
        return campaignPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setNameClean(String nameClean) {
        this.nameClean = nameClean;
    }

    public void setDescriptionClean(String descriptionClean) {
        this.descriptionClean = descriptionClean;
    }

    public void setCampaignPrice(float campaignPrice) {
        this.campaignPrice = campaignPrice;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMeasures(MeasuresDTO measures) {
        this.measures = measures;
    }

    public void setProductStock(Set<ProductStockDTO> productStock) {
        this.productStock = productStock;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setSubCategory(SubCategoryDTO subCategory) {
        this.subCategory = subCategory;
    }

    public void setCampaigns(Set<CampaignProductDTO> campaigns) {
        this.campaigns = campaigns;
    }

    public void setProductImages(Set<ProductImageDTO> productImages) {
        this.productImages = productImages;
    }
}
