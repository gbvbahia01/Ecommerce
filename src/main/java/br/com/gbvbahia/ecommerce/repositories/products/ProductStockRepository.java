package br.com.gbvbahia.ecommerce.repositories.products;

import br.com.gbvbahia.ecommerce.model.entity.products.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
}
