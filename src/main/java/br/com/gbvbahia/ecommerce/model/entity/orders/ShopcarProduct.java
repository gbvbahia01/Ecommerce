/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.orders;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductStock;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "shopcar_product_stock", schema = "orders")
public class ShopcarProduct implements Model<Long>, Comparable<ShopcarProduct> {

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
    @JoinColumn(name = "shopcar_id", nullable = false, unique = false)
    private Shopcar shopcar;

    @NotNull
    @Column(name = "amount", nullable = false, unique = false)
    private Integer amountProducts = 1;

    public ShopcarProduct() {
    }

    public ShopcarProduct(ProductStock product, Shopcar shopcar) {
        this.productStock = product;
        this.shopcar = shopcar;
    }

    public ShopcarProduct(ProductStock product, Shopcar shopcar, Integer amount) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.shopcar);
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
        final ShopcarProduct other = (ShopcarProduct) obj;
        if (!Objects.equals(this.productStock, other.productStock)) {
            return false;
        }
        if (!Objects.equals(this.shopcar, other.shopcar)) {
            return false;
        }
        return true;
    }

    @Override
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

    public ProductStock getProductStock() {
        return productStock;
    }

    public void setProductStock(ProductStock product) {
        this.productStock = product;
    }

    public Shopcar getShopcar() {
        return shopcar;
    }

    public void setShopcar(Shopcar shopcar) {
        this.shopcar = shopcar;
    }

    public Integer getAmountProducts() {
        return amountProducts;
    }

    public void setAmountProducts(Integer amountProducts) {
        this.amountProducts = amountProducts;
    }

    @Override
    public int compareTo(ShopcarProduct o) {
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
