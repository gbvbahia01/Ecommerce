package br.com.gbvbahia.ecommerce.services.orders;

import br.com.gbvbahia.ecommerce.services.helpers.orders.ShopcarDTO;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.Shopcar;
import br.com.gbvbahia.ecommerce.services.ServiceContract;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public interface ShopcarService extends ServiceContract<ShopcarDTO, Long> {

    ShopcarDTO findBySerial(String serial);

    ShopcarDTO findByCustomer(Customer customer);

}
