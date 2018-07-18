/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.products;

import br.com.gbvbahia.ecommerce.model.cotract.Model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "campaign", schema = "products")
public class Campaign implements Model<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_campaign")
    @SequenceGenerator(name = "seq_campaign", sequenceName = "products.seq_campaign", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    @Column(name = "discount", precision = 2, scale = 10, nullable = false)
    @NotNull
    private Float discount;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_start", nullable = false)
    private Calendar dateStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_end", nullable = true)
    private Calendar dateEnd;

    @NotNull
    @Column(name = "name", length = 20, nullable = false)
    @Size(max = 20, min = 3)
    private String name;

    @Column(name = "description", length = 250, nullable = true)
    @Size(max = 250)
    private String description;
    
    @Column(name = "active", nullable = false)
        private boolean active = false;
    
    /**
     * Discounts
     */
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private Set<CampaignProduct> products;

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

    public Set<CampaignProduct> getProducts() {
        if(products == null){
            products = new HashSet<>();
        }
        return products;
    }

    public void setProducts(Set<CampaignProduct> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Campaign other = (Campaign) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CampaignDTO{" + "id=" + id + ", discount=" + discount + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", name=" + name + ", description=" + description + ", active=" + active + '}';
    }


    
    
}
