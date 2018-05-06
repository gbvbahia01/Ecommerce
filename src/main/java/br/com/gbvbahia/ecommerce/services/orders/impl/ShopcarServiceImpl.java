package br.com.gbvbahia.ecommerce.services.orders.impl;

import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.Shopcar;
import br.com.gbvbahia.ecommerce.repositories.orders.ShopcarRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.orders.ShopcarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Service
public class ShopcarServiceImpl extends ServiceCommon<Shopcar, Long, JpaRepository<Shopcar, Long>> implements ShopcarService {

    private final ShopcarRepository shopcarRepository;

    public ShopcarServiceImpl(ParameterService parameterService,
                              ShopcarRepository shopcarRepository) {

        super(parameterService);
        this.shopcarRepository = shopcarRepository;
    }

    @Override
    protected JpaRepository<Shopcar, Long> getRepository() {
        return this.shopcarRepository;
    }

    @Override
    public Shopcar findBySerial(String serial) {
        if (StringUtils.isBlank(serial)) {
            return null;
        }
        return shopcarRepository.findBySerialUniqueId(serial);
    }

    @Override
    public Shopcar findByCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return shopcarRepository.findByCustomer(customer);
    }
}
