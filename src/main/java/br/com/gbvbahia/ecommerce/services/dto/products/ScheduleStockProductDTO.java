/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.dto.products;

import br.com.gbvbahia.ecommerce.model.embeddable.ScheduleStockProductPK;

import java.io.Serializable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ScheduleStockProductDTO implements Serializable {

    private ScheduleStockProductPK id = new ScheduleStockProductPK();
    private ProductDTO product;
    private ScheduleStockDTO scheduleStock;
    private Integer amount;
    private boolean received = false;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ScheduleStockDTO getScheduleStock() {
        return scheduleStock;
    }

    public void setScheduleStock(ScheduleStockDTO scheduleStock) {
        this.scheduleStock = scheduleStock;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public ScheduleStockProductPK getId() {
        return id;
    }

    public void setId(ScheduleStockProductPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ScheduleStockProductDTO{" + "id=" + id + ", product=" + product + ", scheduleStock=" + scheduleStock + ", amount=" + amount + ", received=" + received + '}';
    }

}
