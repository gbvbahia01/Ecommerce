/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.dto.products;

import br.com.gbvbahia.ecommerce.model.enums.Specifications;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ProductStockDTO implements Serializable {

    private Long id;
    private Integer stockAmount;
    private ProductDTO product;
    private Map<String, String> specification;

    private boolean hasKey(Specifications sp) {
        for (String key : getSpecification().keySet()) {
            if (sp.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("stockAmount", stockAmount)
                .append("product", product)
                .append("specification", specification)
                .toString();
    }

    //============================
    //Getters and Setters
    //============================
    public String getSpecificationsString() {
        StringBuilder builder = new StringBuilder("");
        for (String key : getSpecification().keySet()) {
            builder.append(key).append(":");
            builder.append(getSpecification().get(key));
        }
        return builder.toString();
    }

    public Map<String, String> getSpecification() {
        if (specification == null) {
            specification = new TreeMap<>();
        }
        return specification;
    }

    public void setSpecification(Map<String, String> specification) {
        this.specification = specification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public boolean isColor() {
        return hasKey(Specifications.COLOR);
    }

    public boolean isSize() {
        return hasKey(Specifications.SIZE);
    }
    
    public boolean isVoltage() {
        return hasKey(Specifications.VOLTAGE);
    }
    
}
