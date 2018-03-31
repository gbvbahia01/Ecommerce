/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.embeddable;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
@Embeddable
public class ScheduleStockProductPK implements Serializable {

	private static final long serialVersionUID = -1855898707340931469L;

	@Column(name = "product_id", nullable = false)
    @NotNull
    private Long idProduct;

    @Column(name = "scheduleStock_id", nullable = false)
    @NotNull
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
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idScheduleStock);
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
        final ScheduleStockProductPK other = (ScheduleStockProductPK) obj;
        if (!Objects.equals(this.idProduct, other.idProduct)) {
            return false;
        }
        if (!Objects.equals(this.idScheduleStock, other.idScheduleStock)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleStockProductPK{" + "idProduct=" + idProduct + ", idScheduleStock=" + idScheduleStock + '}';
    }

}
