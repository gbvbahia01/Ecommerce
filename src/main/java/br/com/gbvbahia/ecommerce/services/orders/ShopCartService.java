package br.com.gbvbahia.ecommerce.services.orders;

import br.com.gbvbahia.ecommerce.services.dto.orders.ShopCartDTO;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.services.ServiceContract;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public interface ShopCartService extends ServiceContract<ShopCartDTO, Long> {

    ShopCartDTO findBySerial(String serial);

    ShopCartDTO findByCustomer(Customer customer);

}
