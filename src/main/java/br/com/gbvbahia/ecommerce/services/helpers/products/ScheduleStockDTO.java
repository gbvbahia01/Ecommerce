/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.products;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ScheduleStockDTO implements Serializable {

    private Long id;
    private String description;
    private Calendar solicited;
    private Calendar delivered;
    private Boolean receivedAll;
    private Set<ScheduleStockProductDTO> scheduleStockProduct;

    public Calendar getSolicited() {
        return solicited;
    }

    public void setSolicited(Calendar solicited) {
        this.solicited = solicited;
    }

    public Calendar getDelivered() {
        return delivered;
    }

    public void setDelivered(Calendar delivered) {
        this.delivered = delivered;
    }

    public Boolean isReceivedAll() {
        return receivedAll;
    }

    public void setReceivedAll(Boolean received) {
        this.receivedAll = received;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ScheduleStockProductDTO> getScheduleStockProduct() {
        if (scheduleStockProduct == null) {
            scheduleStockProduct = new HashSet<>();
        }
        return scheduleStockProduct;
    }

    public void setScheduleStockProduct(Set<ScheduleStockProductDTO> scheduleStockProduct) {
        this.scheduleStockProduct = scheduleStockProduct;
    }

    @Override
    public String toString() {
        return "ScheduleStockDTO{" + "id=" + id + ", description=" + description + ", solicited=" + solicited + ", delivered=" + delivered + ", receivedAll=" + receivedAll + '}';
    }

}
