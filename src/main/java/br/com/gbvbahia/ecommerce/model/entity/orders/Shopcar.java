/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.orders;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductStock;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "shopcar", schema = "orders")
public class Shopcar implements Model<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_shopcar")
    @SequenceGenerator(sequenceName = "seq_shopcar", name = "seq_shopcar")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true, nullable = true)
    private Customer customer;

    /**
     * Use as a cookie in client
     */
    @NotNull
    @Size(max = 256, min = 5)
    @Column(name = "serial_uk", nullable = false, unique = true, length = 256)
    private String serialUniqueId;

    @OneToMany(mappedBy = "shopcar", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            targetEntity = ShopcarProduct.class)
    private Set<ShopcarProduct> shopcarProducts;

    public Shopcar() {
    }

    public Shopcar(Customer customer, String serialUniqueId, Set<ShopcarProduct> shopcarProducts) {
        this.customer = customer;
        this.serialUniqueId = serialUniqueId;
        this.shopcarProducts = shopcarProducts;
    }

    public Shopcar(String serialUniqueId, Set<ShopcarProduct> shopcarProducts) {
        this.serialUniqueId = serialUniqueId;
        this.shopcarProducts = shopcarProducts;
    }

    public Shopcar(Customer customer, Set<ShopcarProduct> shopcarProducts) {
        this.customer = customer;
        this.shopcarProducts = shopcarProducts;
    }

    public Shopcar(String serialUniqueId, ProductStock... products) {
        this.serialUniqueId = serialUniqueId;
        addProducts(products);
    }

    public Shopcar(Customer customer, ProductStock... products) {
        this.customer = customer;
        addProducts(products);
    }

    public void addProducts(ProductStock... products) {
        for (ProductStock product : products) {
            ShopcarProduct sp = new ShopcarProduct(product, this);
            if (!getShopcarProductses().contains(sp)) {
                getShopcarProductses().add(new ShopcarProduct(product, this));
            } else {
                getShopcarProductses().stream().filter(sp2 -> sp2.equals(sp)).findFirst().get().addAmount();
            }
        }
    }
    
    public boolean removeShopcarProduct(Long idShopcarProduct) {
        ShopcarProduct toRemove = null;
        for (ShopcarProduct sp : getShopcarProductses()) {
            if (sp.getId().equals(idShopcarProduct)) {
                toRemove = sp;
                break;
            }
        }
        if (toRemove != null) {
            return getShopcarProductses().remove(toRemove);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Shopcar other = (Shopcar) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSerialUniqueId() {
        return serialUniqueId;
    }

    public void setSerialUniqueId(String serialUniqueId) {
        this.serialUniqueId = serialUniqueId;
    }

    public Set<ShopcarProduct> getShopcarProductses() {
        if (shopcarProducts == null) {
            shopcarProducts = new TreeSet<>();
        }
        return shopcarProducts;
    }

    public void setShopcarProductses(Set<ShopcarProduct> shopcarProductses) {
        this.shopcarProducts = shopcarProductses;
    }

    public boolean contains(ProductStock pStock) {
        for (ShopcarProduct ps : getShopcarProductses()) {
            if (Objects.equals(pStock, ps.getProductStock())) {
                return true;
            }
        }
        return false;
    }
}
