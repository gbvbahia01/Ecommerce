package br.com.gbvbahia.ecommerce.services.products;

import br.com.gbvbahia.ecommerce.services.dto.products.CategoryDTO;
import br.com.gbvbahia.ecommerce.services.ServiceContract;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
public interface CategoryService extends ServiceContract<CategoryDTO, Long> {

    List<CategoryDTO> listCategoriesForMenu();
}
