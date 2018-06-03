/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.orders;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductStock;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "shopcart_product_stock", schema = "orders")
public class ShopCartProduct implements Model<Long>, Comparable<ShopCartProduct> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_shc_prod_stk")
    @SequenceGenerator(sequenceName = "seq_shc_prod_stk", name = "seq_shc_prod_stk")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_stock_id", nullable = false, unique = false)
    private ProductStock productStock;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "shopcart_id", nullable = false, unique = false)
    private ShopCart shopCart;

    @NotNull
    @Column(name = "amount", nullable = false, unique = false)
    private Integer amountProducts = 1;

    public ShopCartProduct() {
    }

    public ShopCartProduct(ProductStock product, ShopCart shopCart) {
        this.productStock = product;
        this.shopCart = shopCart;
    }

    public ShopCartProduct(ProductStock product, ShopCart shopCart, Integer amount) {
        this.productStock = product;
        this.shopCart = shopCart;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.shopCart);
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
        final ShopCartProduct other = (ShopCartProduct) obj;
        if (!Objects.equals(this.productStock, other.productStock)) {
            return false;
        }
        if (!Objects.equals(this.shopCart, other.shopCart)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShopcarProducts{" + "ProductStock=" + productStock + ", shopCart=" + shopCart + ", amountProducts=" + amountProducts + '}';
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

    public ProductStock getProductStock() {
        return productStock;
    }

    public void setProductStock(ProductStock product) {
        this.productStock = product;
    }

    public ShopCart getShopCart() {
        return shopCart;
    }

    public void setShopCart(ShopCart shopCart) {
        this.shopCart = shopCart;
    }

    public Integer getAmountProducts() {
        return amountProducts;
    }

    public void setAmountProducts(Integer amountProducts) {
        this.amountProducts = amountProducts;
    }

    @Override
    public int compareTo(ShopCartProduct o) {
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
