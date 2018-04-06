/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.enums.Specifications;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "product_stock", schema = "products")
public class ProductStock implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_stock")
    @SequenceGenerator(sequenceName = "seq_product_stock", name = "seq_product_stock")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Min(0)
    @Column(name = "amount", nullable = false)
    private Integer stockSmount;

    @NotNull
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "specification", length = 150)
    @Column(name = "value", length = 150)
    @CollectionTable(schema = "products",
            name = "stock_specification",
            joinColumns = @JoinColumn(name = "product_stock_id"))
    private Map<String, String> specification;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.specification);
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
        final ProductStock other = (ProductStock) obj;
        try {
            if (!Objects.equals(this.product.getId(), other.product.getId())) {
                return false;
            }
        } catch (NullPointerException npe) {
            return false;
        }
        if (!Objects.equals(this.specification, other.specification)) {
            return false;
        }
        return true;
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

    public Integer getStockSmount() {
        return stockSmount;
    }

    public void setStockSmount(Integer stockSmount) {
        this.stockSmount = stockSmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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
    
    private boolean hasKey(Specifications sp) {
        for (String key : getSpecification().keySet()) {
            if (sp.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
}
