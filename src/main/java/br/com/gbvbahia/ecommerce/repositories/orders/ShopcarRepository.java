package br.com.gbvbahia.ecommerce.repositories.orders;

import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.Shopcar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public interface ShopcarRepository extends JpaRepository<Shopcar, Long> {

    Shopcar findBySerialUniqueId(String serial);
    Shopcar findByCustomer(Customer customer);

}
