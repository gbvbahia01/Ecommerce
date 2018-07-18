/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
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
@Table(name = "product_image", schema = "products")
public class ProductImage implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_image")
    @SequenceGenerator(name = "seq_product_image", sequenceName = "products.seq_product_image", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    /**
     * URL to find the picture on hard disk.
     */
    @NotNull
    @Column(name = "name_disk", length = 255, nullable = false, unique = true)
    @Size(max = 255)
    private String namePicture;

    /**
     * A key for identify the picture
     */
    @Column(name = "key_disk", length = 30, nullable = false, unique = false)
    @Size(max = 30)
    @Enumerated(EnumType.STRING)
    @NotNull
    private KeyPicture keyPicture;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "product_image_dif_size", schema = "products",
            joinColumns = {
                @JoinColumn(name = "img_id",
                        referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = @JoinColumn(name = "img_id_other_size",
                    referencedColumnName = "id", nullable = false))
    private Set<ProductImage> otherSize;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNamePicture() {
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

    public Set<ProductImage> getOtherSize() {
        if (otherSize == null) {
            otherSize = new LinkedHashSet<>();
        }
        return otherSize;
    }

    public void setOtherSize(Set<ProductImage> otherSize) {
        this.otherSize = otherSize;
    }

    public void addOtherSize(ProductImage pi) {
        getOtherSize().add(pi);
    }

    public ProductImage getByTag(String keyPicture) {
        for (ProductImage pi : getOtherSize()) {
            if (pi.getKeyPicture().equals(keyPicture)) {
                return pi;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.namePicture);
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
        final ProductImage other = (ProductImage) obj;
        if (!Objects.equals(this.namePicture, other.namePicture)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductImage{" + "id=" + id + ", product=" + product +
                ", namePicture=" + namePicture + ", keyPicture=" + keyPicture
                + ", otherSize=" + otherSize + '}';
    }



}
