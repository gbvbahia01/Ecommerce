/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.cotract;

import java.io.Serializable;

/**
 *
 * @author Guilherme
 */
public interface Model<ID extends Serializable> {
    
    ID getId();
}
