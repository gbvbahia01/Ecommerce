/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.order;

import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.entity.products.Product;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "item", schema = "orders")
public class Item implements Model<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Min(1)
    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @DecimalMin(value = "0.0")
    @Column(name = "unit_price", precision = 2, scale = 10, nullable = false)
    @NotNull
    private Float unitPrice;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_made", nullable = false)
    private Calendar dateMade;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "ID", nullable = false)
    @NotNull
    private Product product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "ID", nullable = false)
    private Order order;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Calendar getDateMade() {
        return dateMade;
    }

    public void setDateMade(Calendar dateMade) {
        this.dateMade = dateMade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.order);
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
        final Item other = (Item) obj;
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", amount=" + amount + ", unitPrice=" + unitPrice + ", dateMade=" + dateMade + ", product=" + product + ", order=" + order + '}';
    }

}
