/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.orders;

import br.com.gbvbahia.ecommerce.services.helpers.customers.CustomerDTO;
import br.com.gbvbahia.ecommerce.services.helpers.products.ProductStockDTO;

import java.io.Serializable;
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
public class ShopcarDTO implements Serializable {

    private Long id;
    private CustomerDTO customer;
    private String serialUniqueId;
    private Set<ShopcarProductDTO> shopcarProducts;

    public ShopcarDTO() {
    }

    public ShopcarDTO(CustomerDTO customer, String serialUniqueId, Set<ShopcarProductDTO> shopcarProducts) {
        this.customer = customer;
        this.serialUniqueId = serialUniqueId;
        this.shopcarProducts = shopcarProducts;
    }

    public ShopcarDTO(String serialUniqueId, Set<ShopcarProductDTO> shopcarProducts) {
        this.serialUniqueId = serialUniqueId;
        this.shopcarProducts = shopcarProducts;
    }

    public ShopcarDTO(CustomerDTO customer, Set<ShopcarProductDTO> shopcarProducts) {
        this.customer = customer;
        this.shopcarProducts = shopcarProducts;
    }

    public ShopcarDTO(String serialUniqueId, ProductStockDTO... products) {
        this.serialUniqueId = serialUniqueId;
        addProducts(products);
    }

    public ShopcarDTO(CustomerDTO customer, ProductStockDTO... products) {
        this.customer = customer;
        addProducts(products);
    }

    public void addProducts(ProductStockDTO... products) {
        for (ProductStockDTO product : products) {
            ShopcarProductDTO sp = new ShopcarProductDTO(product, this);
            if (!getShopcarProductses().contains(sp)) {
                getShopcarProductses().add(new ShopcarProductDTO(product, this));
            } else {
                getShopcarProductses().stream().filter(sp2 -> sp2.equals(sp)).findFirst().get().addAmount();
            }
        }
    }
    
    public boolean removeShopcarProduct(Long idShopcarProduct) {
        ShopcarProductDTO toRemove = null;
        for (ShopcarProductDTO sp : getShopcarProductses()) {
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

    //============================
    //Getters and Setters
    //============================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public String getSerialUniqueId() {
        return serialUniqueId;
    }

    public void setSerialUniqueId(String serialUniqueId) {
        this.serialUniqueId = serialUniqueId;
    }

    public Set<ShopcarProductDTO> getShopcarProductses() {
        if (shopcarProducts == null) {
            shopcarProducts = new TreeSet<>();
        }
        return shopcarProducts;
    }

    public void setShopcarProductses(Set<ShopcarProductDTO> shopcarProductses) {
        this.shopcarProducts = shopcarProductses;
    }

    public boolean contains(ProductStockDTO pStock) {
        for (ShopcarProductDTO ps : getShopcarProductses()) {
            if (Objects.equals(pStock, ps.getProductStock())) {
                return true;
            }
        }
        return false;
    }
}
