/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.dto.orders;

import br.com.gbvbahia.ecommerce.services.dto.customers.CustomerDTO;
import br.com.gbvbahia.ecommerce.model.enums.OrderStatus;

import java.io.Serializable;
import java.util.*;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class OrderDTO implements Serializable {

    private Long id;
    private OrderStatus orderStatus = OrderStatus.Made;
    private CustomerDTO customer;
    private Set<ItemDTO> items;

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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Set<ItemDTO> getItems() {
        if (items == null) {
            items = new HashSet<>();
        }
        return items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }

    public List<ItemDTO> getListItems() {
        return new ArrayList<>(getItems());
    }

    public void setListItems(List<ItemDTO> items) {
        this.items = new HashSet<>(items);
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "id=" + id + ", orderStatus=" + orderStatus + ", customer=" + customer + '}';
    }

}
