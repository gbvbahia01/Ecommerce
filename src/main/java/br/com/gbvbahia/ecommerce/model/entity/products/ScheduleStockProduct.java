/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.embeddable.ScheduleStockProductPK;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "schedule_stock_product", schema = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"scheduleStock_id", "product_id"}))
public class ScheduleStockProduct implements Model<ScheduleStockProductPK> {

    @EmbeddedId
    private ScheduleStockProductPK id = new ScheduleStockProductPK();

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "ID", updatable = false, insertable = false)
    @NotNull
    private Product product;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "scheduleStock_id", referencedColumnName = "ID", updatable = false, insertable = false)
    private ScheduleStock scheduleStock;

    @NotNull
    @Min(1)
    @Column(name = "amount", nullable = false, unique = false)
    private Integer amount;

    @Column(name = "received", nullable = false)
    private boolean received = false;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ScheduleStock getScheduleStock() {
        return scheduleStock;
    }

    public void setScheduleStock(ScheduleStock scheduleStock) {
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
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final ScheduleStockProduct other = (ScheduleStockProduct) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleStockProduct{" + "id=" + id + ", product=" + product + ", scheduleStock=" + scheduleStock + ", amount=" + amount + ", received=" + received + '}';
    }

}
