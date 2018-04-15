/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.enums;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public enum Specifications {
    COLOR("productstock.specification.color"),
    VOLTAGE("productstock.specification.voltage"),
    SIZE("productstock.specification.size");

    private final String key;

    Specifications(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
