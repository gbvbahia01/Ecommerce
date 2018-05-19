/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.orders;

import br.com.gbvbahia.ecommerce.services.helpers.products.ProductDTO;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ItemDTO implements Serializable {

    private Long id;
    private Integer amount;
    private Float unitPrice;
    private Calendar dateMade;
    private ProductDTO product;
    private OrderDTO order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public Calendar getDateMade() {
        return dateMade;
    }

    public void setDateMade(Calendar dateMade) {
        this.dateMade = dateMade;
    }

    @Override
    public String toString() {
        return "ItemScreen{" + "id=" + id + ", amount=" + amount + ", unitPrice=" + unitPrice
                + ", dateMade=" + dateMade + ", product=" + product + ", orders=" + order + '}';
    }

}
