/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.embeddable.Measures;
import br.com.gbvbahia.ecommerce.util.StringHelper;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "product", schema = "products")
public class Product implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
    @SequenceGenerator(name = "seq_product", sequenceName = "products.seq_product", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", length = 30, nullable = false)
    @Size(max = 30, min = 3)
    private String name;

    @NotNull
    @Column(name = "name_clean", length = 32, nullable = false)
    @Size(max = 32, min = 3)
    private String nameClean;

    @NotNull
    @Column(name = "branch", length = 25, nullable = false)
    @Size(max = 25, min = 2)
    private String branch;

    @NotNull
        @Column(name = "description", length = 250, nullable = false)
    @Size(max = 250, min = 10)
    private String description;

    @NotNull
    @Column(name = "description_clean", length = 252, nullable = false)
    @Size(max = 252, min = 10)
    private String descriptionClean;

    @NotNull
    @Embedded
    private Measures measures;
    
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductStock productStock;

    @DecimalMin(value = "0.0")
    @Column(name = "price", precision = 2, scale = 10, nullable = false)
    @NotNull
    private Float price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductSubCategory> subCategories;

    /**
     * Discounts
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CampaignProduct> campaigns;

    /**
     * Pictures
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductImage> productImages;

    /**
     * Use to get the right price of product.
     * @return the price with campaign discount if has or regular product price.
     */
    public float getCampaignPrice() {
        Float priceDesc = price;
        Float discount = getDiscountPercent();
        if (discount > 0.0F) {
            priceDesc = priceDesc * (1 - (discount / 100.0F));
        }
        return priceDesc;
    }

    public Float getDiscountPercent() {
        Float discount = 0.0F;
        Campaign active = getActiveCampaign();
        if (active != null) {
            discount = active.getDiscount();
        }
        return discount;
    }

    public Campaign getActiveCampaign() {
        for (CampaignProduct cp : getCampaigns()) {
            if (cp.isActive()) {
                return cp.getCampaign();
            }
        }
        return null;
    }

    public ProductImage searchImgBykeyPicture(String keyPicture) {
        try {
            return getProductImages().stream().filter(pi -> pi.getKeyPicture().equals(keyPicture)).findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.branch);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.branch, other.branch)) {
            return false;
        }
        if (!Objects.equals(this.measures, other.measures)) {
            return false;
        }
        if (!Objects.equals(this.productStock, other.productStock)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", branch=" + branch + ", description=" + description
                + ", measures=" + measures + ", price=" + price + '}';
    }

    @PrePersist
    @PreUpdate
    public void defineCleanStrings() {
        this.descriptionClean = StringHelper.cleanToSearchAppend(description, "|");
         this.nameClean = StringHelper.cleanToSearchAppend(name, "|");
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        defineCleanStrings();
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        defineCleanStrings();
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<CampaignProduct> getCampaigns() {
        if (campaigns == null) {
            campaigns = new HashSet<>();
        }
        return campaigns;
    }

    public void setCampaigns(Set<CampaignProduct> campaigns) {
        this.campaigns = campaigns;
    }

    public Set<ProductImage> getProductImages() {
        if (productImages == null) {
            productImages = new HashSet<>();
        }
        return productImages;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public String getNameClean() {
        return nameClean;
    }

    public String getDescriptionClean() {
        return descriptionClean;
    }

    public ProductStock getProductStock() {
        return productStock;
    }

    public void setProductStock(ProductStock productStock) {
        this.productStock = productStock;
    }

    public Set<ProductSubCategory> getSubCategories() {
        if (subCategories == null) {
            subCategories = new HashSet<>();
        }
        return subCategories;
    }

    public void setSubCategories(Set<ProductSubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
