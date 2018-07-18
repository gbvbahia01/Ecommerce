/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.entity.orders;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.enums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Entity
@Table(name = "order_status", schema = "orders")
public class Order implements Model<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order_status")
    @SequenceGenerator(name = "seq_order_status", sequenceName = "orders.seq_order_status", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private OrderStatus orderStatus = OrderStatus.Made;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @NotNull
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    @NotNull
    @Size(min = 1)
    private Set<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Item> getItems() {
        if (items == null) {
            items = new HashSet<>();
        }
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public List<Item> getListItems() {
        return new ArrayList<>(getItems());
    }

    public void setListItems(List<Item> items) {
        this.items = new HashSet<>(items);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderStatus=" + orderStatus + ", customer=" + customer + '}';
    }

}
