/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.dto.products;

import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ProductImageDTO implements Serializable {

    private Long id;
    private ProductDTO product;
    private String namePicture;
    private KeyPicture keyPicture;
    private Set<ProductImageDTO> otherSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getNamePicture() {
        if (namePicture == null && getKeyPicture() != null) {
            return getKeyPicture().getDefaultImg();
        }
        return namePicture;
    }

    public void setNamePicture(String namePicture) {
        this.namePicture = namePicture;
    }

    public KeyPicture getKeyPicture() {
        return keyPicture;
    }

    public void setKeyPicture(KeyPicture keyPicture) {
        this.keyPicture = keyPicture;
    }

    public Set<ProductImageDTO> getOtherSize() {
        if (otherSize == null) {
            otherSize = new LinkedHashSet<>();
        }
        return otherSize;
    }

    public void setOtherSize(Set<ProductImageDTO> otherSize) {
        this.otherSize = otherSize;
    }

    public void addOtherSize(ProductImageDTO pi) {
        getOtherSize().add(pi);
    }

    public ProductImageDTO getByTag(String keyPicture) {
        for (ProductImageDTO pi : getOtherSize()) {
            if (pi.getKeyPicture().equals(keyPicture)) {
                return pi;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ProductImageDTO {" + "id=" + id + ", product=" + product +
                ", namePicture=" + namePicture + ", keyPicture=" + keyPicture
                + ", otherSize=" + otherSize + '}';
    }

}
