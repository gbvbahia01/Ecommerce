/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public enum OrderStatus {
    Made(100, false), Paid(200, false), Receipt(300, false), Sending(400, false),
    Sent(500, false), Delivered(600, true), Canceled(-1, false);

    private int flow;
    private boolean reviewAllowed;

    private OrderStatus(int orderFlow, boolean reviewAllowed) {
        this.flow = orderFlow;
        this.reviewAllowed = reviewAllowed;
    }

    public OrderStatus getNextFlow() {
        switch (flow) {
            case 100:
                return Paid;
            case 200:
                return Receipt;
            case 300:
                return Sending;
            case 400:
                return Sent;
            case 500:
                return Delivered;
            case 600:
                return Delivered;
            default:
                return null;
        }
    }

    public static OrderStatus getByFlow(int flow) {
        for (OrderStatus os : OrderStatus.values()) {
            if (os.flow == flow) {
                return os;
            }
        }
        return null;
    }

    public static List<OrderStatus> getByReviewAllowed(boolean reviewAllowed) {
        List<OrderStatus> toReturn = new ArrayList<>();
        for (OrderStatus os : OrderStatus.values()) {
            if (os.reviewAllowed == reviewAllowed) {
                toReturn.add(os);
            }
        }
        return toReturn;
    }
    
}
