/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.orders;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.products.Product;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Objects;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "review", schema = "orders")
public class Review implements Model<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_review")
    @SequenceGenerator(name = "seq_review", sequenceName = "orders.seq_review", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Product product;

    @NotNull
    @Max(5)
    @Min(0)
    @Column(name = "stars", nullable = false)
    private int stars;

    @Size(max = 500)
    @Column(name = "text", nullable = true, length = 500)
    private String reviewText;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "date_time", nullable = false, updatable = false)
    private Calendar dateTime;
    
    @Column(name = "moderate", nullable = false)
    boolean moderate = false;

    public Review() {
        dateTime = Calendar.getInstance();
    }

    public Review(Customer customer, Product product) {
        this();
        this.customer = customer;
        this.product = product;
    }

    public Review(Customer customer, Product product, int stars, String reviewText) {
        this(customer, product);
        this.stars = stars;
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", stars=" + stars + ", reviewText=" + reviewText + ", dateTime=" + dateTime + ", moderate=" + moderate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.product);
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
        final Review other = (Review) obj;
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

//============================
//Getters and Setters
//============================
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public boolean isModerate() {
        return moderate;
    }

    public void setModerate(boolean moderate) {
        this.moderate = moderate;
    }
}
