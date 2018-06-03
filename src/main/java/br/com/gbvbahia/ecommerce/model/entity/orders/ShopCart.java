/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.orders;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductStock;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "shopcart", schema = "orders")
public class ShopCart implements Model<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_shopcart")
    @SequenceGenerator(sequenceName = "seq_shopcart", name = "seq_shopcart")
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

    @OneToMany(mappedBy = "shopCart", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            targetEntity = ShopCartProduct.class)
    private Set<ShopCartProduct> shopCartProducts;

    public ShopCart() {
    }

    public ShopCart(Customer customer, String serialUniqueId, Set<ShopCartProduct> shopCartProducts) {
        this.customer = customer;
        this.serialUniqueId = serialUniqueId;
        this.shopCartProducts = shopCartProducts;
    }

    public ShopCart(String serialUniqueId, Set<ShopCartProduct> shopCartProducts) {
        this.serialUniqueId = serialUniqueId;
        this.shopCartProducts = shopCartProducts;
    }

    public ShopCart(Customer customer, Set<ShopCartProduct> shopCartProducts) {
        this.customer = customer;
        this.shopCartProducts = shopCartProducts;
    }

    public ShopCart(String serialUniqueId, ProductStock... products) {
        this.serialUniqueId = serialUniqueId;
        addProducts(products);
    }

    public ShopCart(Customer customer, ProductStock... products) {
        this.customer = customer;
        addProducts(products);
    }

    public void addProducts(ProductStock... products) {
        for (ProductStock product : products) {
            ShopCartProduct sp = new ShopCartProduct(product, this);
            if (!getShopCartProducts().contains(sp)) {
                getShopCartProducts().add(new ShopCartProduct(product, this));
            } else {
                getShopCartProducts().stream().filter(sp2 -> sp2.equals(sp)).findFirst().get().addAmount();
            }
        }
    }
    
    public boolean removeShopcarProduct(Long idShopcarProduct) {
        ShopCartProduct toRemove = null;
        for (ShopCartProduct sp : getShopCartProducts()) {
            if (sp.getId().equals(idShopcarProduct)) {
                toRemove = sp;
                break;
            }
        }
        if (toRemove != null) {
            return getShopCartProducts().remove(toRemove);
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
        final ShopCart other = (ShopCart) obj;
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

    public Set<ShopCartProduct> getShopCartProducts() {
        if (shopCartProducts == null) {
            shopCartProducts = new TreeSet<>();
        }
        return shopCartProducts;
    }

    public void setShopCartProducts(Set<ShopCartProduct> shopCartProducts) {
        this.shopCartProducts = shopCartProducts;
    }

    public boolean contains(ProductStock pStock) {
        for (ShopCartProduct ps : getShopCartProducts()) {
            if (Objects.equals(pStock, ps.getProductStock())) {
                return true;
            }
        }
        return false;
    }
}
