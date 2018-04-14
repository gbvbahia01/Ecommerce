/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services.impl;

import java.util.Optional;

/**
 *
 * @author Guilherme
 */
public interface ServiceContract<T, ID> {
    /**
     * Define quickly access to repository findBydId to all services.
     * @param id
     * @return A optional with a result of search.
     */
    public Optional<T> findBydId(ID id);
}
