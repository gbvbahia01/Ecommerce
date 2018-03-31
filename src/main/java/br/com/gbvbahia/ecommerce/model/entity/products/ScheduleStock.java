/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gbvbahia.ecommerce.model.cotract.Model;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "schedule_stock", schema = "products")
public class ScheduleStock implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "description", length = 250, nullable = false)
    @Size(max = 250, min = 10)
    private String description;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_solicited", nullable = false)
    private Calendar solicited;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_delivered", nullable = true)
    private Calendar delivered;

    @Column(name = "received_all", nullable = false)
    private boolean receivedAll = false;

    @OneToMany(mappedBy = "scheduleStock", cascade = CascadeType.ALL)
    private Set<ScheduleStockProduct> scheduleStockProduct;
    
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

    public boolean isReceivedAll() {
        return receivedAll;
    }

    public void setReceivedAll(boolean received) {
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

    public Set<ScheduleStockProduct> getScheduleStockProduct() {
        if(scheduleStockProduct == null){
            scheduleStockProduct = new HashSet<>();
        }
        return scheduleStockProduct;
    }

    public void setScheduleStockProduct(Set<ScheduleStockProduct> scheduleStockProduct) {
        this.scheduleStockProduct = scheduleStockProduct;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final ScheduleStock other = (ScheduleStock) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleStock{" + "id=" + id + ", description=" + description + ", solicited=" + solicited + ", delivered=" + delivered + ", receivedAll=" + receivedAll + '}';
    }

}
