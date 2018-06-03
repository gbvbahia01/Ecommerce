package br.com.gbvbahia.ecommerce.repositories.orders;

import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public interface ShopCartRepository extends JpaRepository<ShopCart, Long> {

    ShopCart findBySerialUniqueId(String serial);
    ShopCart findByCustomer(Customer customer);

}
