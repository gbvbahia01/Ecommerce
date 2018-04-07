package br.com.gbvbahia.ecommerce.repositories.products;

import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> {

    /**
     * Search products using nameClean and descriptionClean.
     * @param nameClean String to use at nameClean column.
     * @param descriptionClean String to use at descriptionClean column.
     * @return A list with products. Empty if not found.
     */
    @Query(name = "Product.searchClean")
    List<Product> searchClean(@Param("nameClean") String nameClean,
                              @Param("descriptionClean") String descriptionClean);
}
