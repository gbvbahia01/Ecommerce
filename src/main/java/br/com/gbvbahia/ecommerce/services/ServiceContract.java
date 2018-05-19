/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.services;

import java.util.Optional;

/**
 *
 * @author Guilherme
 */
public interface ServiceContract<DTO, ID> {
    /**
     * Define quickly access to repository findById to all services.
     * @param id
     * @return A optional with a result of search.
     */
    DTO findById(ID id);
}
