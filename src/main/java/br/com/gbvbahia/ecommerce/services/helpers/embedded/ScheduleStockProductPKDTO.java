/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.embedded;

import java.io.Serializable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ScheduleStockProductPKDTO implements Serializable {

	private static final long serialVersionUID = -1855898707340931469L;

    private Long idProduct;
    private Long idScheduleStock;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdScheduleStock() {
        return idScheduleStock;
    }

    public void setIdScheduleStock(Long idScheduleStock) {
        this.idScheduleStock = idScheduleStock;
    }

    @Override
    public String toString() {
        return "ScheduleStockProductPKDTO{" + "idProduct=" + idProduct + ", idScheduleStock=" + idScheduleStock + '}';
    }

}
