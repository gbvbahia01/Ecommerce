package br.com.gbvbahia.ecommerce.services.products;

import br.com.gbvbahia.ecommerce.services.dto.products.ProductDTO;
import br.com.gbvbahia.ecommerce.services.ServiceContract;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public interface ProductService extends ServiceContract<ProductDTO, Long> {

    /**
     *
     * Search products using nameClean and descriptionClean.
     * @param search a string that will be used to search at both attributes.
     * @return A list with products. Empty if not found.
     */
    List<ProductDTO> search(String search);
}
