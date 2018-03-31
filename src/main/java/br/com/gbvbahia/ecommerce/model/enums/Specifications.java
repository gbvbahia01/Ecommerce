/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.enums;

/**
 *
 * @author Guilherme
 */
public enum Specifications {
    COLOR("productstock.specification.color"),
    VOLTAGE("productstock.specification.voltage"),
    SIZE("productstock.specification.size");

    private final String key;

    private Specifications(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
