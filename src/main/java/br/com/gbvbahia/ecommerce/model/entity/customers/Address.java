/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.customers;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.gbvbahia.ecommerce.model.cotract.Model;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "address", schema = "customers")
public class Address implements Model<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
    @SequenceGenerator(sequenceName = "seq_address", name = "seq_address")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "street", length = 150, nullable = false)
    @Size(max = 150, min = 3)
    private String street;

    @NotNull
    @Column(name = "house_number", length = 20, nullable = false)
    @Size(max = 20, min = 1)
    private String number;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Size(max = 10, min = 8)
    @Column(name = "zip", nullable = false,
            unique = false, length = 10)
    private String zip;

    @NotNull
    @Column(name = "uf", nullable = false, length = 15)
    private String uf;

    @NotNull
    @Size(max = 50, min = 3)
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @NotNull
    @Size(max = 100, min = 3)
    @Column(name = "neighborhood", nullable = false, length = 100)
    private String neighborhood;

    @Size(max = 200)
    @Column(name = "complement", nullable = true,
            unique = false, length = 200)
    private String complement;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "ID")
    @NotNull
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.number);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + ", number=" + number + ", zip=" + zip + ", uf=" + uf + ", city=" + city + ", neighborhood=" + neighborhood + ", complement=" + complement + '}';
    }

}
