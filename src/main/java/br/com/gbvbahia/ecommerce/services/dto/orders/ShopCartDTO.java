/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.dto.orders;

import br.com.gbvbahia.ecommerce.services.dto.customers.CustomerDTO;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductStockDTO;

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
public class ShopCartDTO implements Serializable {

    private Long id;
    private CustomerDTO customer;
    private String serialUniqueId;
    private Set<ShopCartProductDTO> shopCartProducts;

    public ShopCartDTO() {
    }

    public ShopCartDTO(CustomerDTO customer, String serialUniqueId, Set<ShopCartProductDTO> shopCartProducts) {
        this.customer = customer;
        this.serialUniqueId = serialUniqueId;
        this.shopCartProducts = shopCartProducts;
    }

    public ShopCartDTO(String serialUniqueId, Set<ShopCartProductDTO> shopCartProducts) {
        this.serialUniqueId = serialUniqueId;
        this.shopCartProducts = shopCartProducts;
    }

    public ShopCartDTO(CustomerDTO customer, Set<ShopCartProductDTO> shopCartProducts) {
        this.customer = customer;
        this.shopCartProducts = shopCartProducts;
    }

    public ShopCartDTO(String serialUniqueId, ProductStockDTO... products) {
        this.serialUniqueId = serialUniqueId;
        addProducts(products);
    }

    public ShopCartDTO(CustomerDTO customer, ProductStockDTO... products) {
        this.customer = customer;
        addProducts(products);
    }

    public void addProducts(ProductStockDTO... products) {
        for (ProductStockDTO product : products) {
            ShopCartProductDTO sp = new ShopCartProductDTO(product, this);
            if (!getShopCartProducts().contains(sp)) {
                getShopCartProducts().add(new ShopCartProductDTO(product, this));
            } else {
                getShopCartProducts().forEach(sp2 -> {
                    if (sp2.equals(sp)){

                    }
                });
            }
        }
    }
    
    public boolean removeShopcarProduct(Long idShopcarProduct) {
        ShopCartProductDTO toRemove = null;
        for (ShopCartProductDTO sp : getShopCartProducts()) {
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

    public Set<ShopCartProductDTO> getShopCartProducts() {
        if (shopCartProducts == null) {
            shopCartProducts = new TreeSet<>();
        }
        return shopCartProducts;
    }

    public void setShopCartProducts(Set<ShopCartProductDTO> shopCartProductses) {
        this.shopCartProducts = shopCartProductses;
    }

    public boolean contains(ProductStockDTO pStock) {
        for (ShopCartProductDTO ps : getShopCartProducts()) {
            if (Objects.equals(pStock, ps.getProductStock())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCartDTO that = (ShopCartDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
