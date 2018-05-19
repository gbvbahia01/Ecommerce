/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.orders;

import br.com.gbvbahia.ecommerce.services.helpers.products.ProductStockDTO;

import java.io.Serializable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ShopcarProductDTO implements Serializable, Comparable<ShopcarProductDTO> {

    private Long id;
    private ProductStockDTO productStock;
    private ShopcarDTO shopcar;
    private Integer amountProducts = 1;

    public ShopcarProductDTO() {
        super();
    }

    public ShopcarProductDTO(ProductStockDTO product, ShopcarDTO shopcar) {
        this.productStock = product;
        this.shopcar = shopcar;
    }

    public ShopcarProductDTO(ProductStockDTO product, ShopcarDTO shopcar, Integer amount) {
        this.productStock = product;
        this.shopcar = shopcar;
        this.amountProducts = amount;
    }

    public void addAmount() {
        amountProducts++;
    }

    public void removeAmount() {
        amountProducts--;
        if (amountProducts < 0) {
            amountProducts = 0;
        }
    }

    public String toString() {
        return "ShopcarProducts{" + "ProductStock=" + productStock + ", shopcar=" + shopcar + ", amountProducts=" + amountProducts + '}';
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

    public ProductStockDTO getProductStock() {
        return productStock;
    }

    public void setProductStock(ProductStockDTO product) {
        this.productStock = product;
    }

    public ShopcarDTO getShopcar() {
        return shopcar;
    }

    public void setShopcar(ShopcarDTO shopcar) {
        this.shopcar = shopcar;
    }

    public Integer getAmountProducts() {
        return amountProducts;
    }

    public void setAmountProducts(Integer amountProducts) {
        this.amountProducts = amountProducts;
    }

    @Override
    public int compareTo(ShopcarProductDTO o) {
        if (o == null) {
            return 1;
        }
        String my;
        try {
            my = this.getProductStock().getProduct().getName();
        } catch (NullPointerException np) {
            return -1;
        }
        String its;
        try {
            its = o.getProductStock().getProduct().getName();
        } catch (NullPointerException e) {
            return 1;
        }
        int i = my.compareToIgnoreCase(its);
        if (i == 0) {
            my = this.getProductStock().getSpecificationsString();
            its = o.getProductStock().getSpecificationsString();
            i = my.compareToIgnoreCase(its);
        }
        return i;
    }

}
