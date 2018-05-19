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
public class CampaignDTO implements Serializable {

    private Long id;
    private Float discount;
    private Calendar dateStart;
    private Calendar dateEnd;
    private String name;
    private String description;
    private boolean active = false;
    private Set<CampaignProductDTO> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public void setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<CampaignProductDTO> getProducts() {
        if (products == null) {
            products = new HashSet<>();
        }
        return products;
    }

    public void setProducts(Set<CampaignProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CampaignDTO{" + "id=" + id + ", discount=" + discount + ", dateStart=" + dateStart
                + ", dateEnd=" + dateEnd + ", name=" + name + ", description=" + description
                + ", active=" + active + '}';
    }

}
