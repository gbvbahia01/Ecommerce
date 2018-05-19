/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.helpers.orders;

import br.com.gbvbahia.ecommerce.services.helpers.customers.CustomerDTO;
import br.com.gbvbahia.ecommerce.services.helpers.products.ProductDTO;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class ReviewDTO implements Serializable {

    private Long id;
    private CustomerDTO customer;
    private ProductDTO product;
    private int stars;
    private String reviewText;
    private Calendar dateTime;
    boolean moderate = false;

    public ReviewDTO() {
        dateTime = Calendar.getInstance();
    }

    public ReviewDTO(CustomerDTO customer, ProductDTO product) {
        this();
        this.customer = customer;
        this.product = product;
    }

    public ReviewDTO(CustomerDTO customer, ProductDTO product, int stars, String reviewText) {
        this(customer, product);
        this.stars = stars;
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", stars=" + stars + ", reviewText=" + reviewText + ", dateTime=" + dateTime + ", moderate=" + moderate + '}';
    }

    //============================
//Getters and Setters
//============================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
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
