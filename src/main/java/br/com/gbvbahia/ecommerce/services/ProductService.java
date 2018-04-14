package br.com.gbvbahia.ecommerce.services;

import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import br.com.gbvbahia.ecommerce.services.impl.ServiceContract;

import java.util.List;

public interface ProductService extends ServiceContract<Product, Long>{

    /**
     *
     * Search products using nameClean and descriptionClean.
     * @param search a string that will be used to search at both attributes.
     * @return A list with products. Empty if not found.
     */
    List<Product> search(String search);
}
