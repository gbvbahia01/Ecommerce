package br.com.gbvbahia.ecommerce.repositories.products;

import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query(name = "Category.listOrderBy")
    List<Category> listAllForMenu(@Param("amountStock") Integer amountStock);
}
