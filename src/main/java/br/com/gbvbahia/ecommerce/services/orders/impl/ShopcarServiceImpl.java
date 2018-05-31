package br.com.gbvbahia.ecommerce.services.orders.impl;

import br.com.gbvbahia.ecommerce.services.dto.orders.ShopcarDTO;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.Shopcar;
import br.com.gbvbahia.ecommerce.repositories.orders.ShopcarRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.orders.ShopcarService;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Service
public class ShopcarServiceImpl extends ServiceCommon<ShopcarDTO, Shopcar, Long, JpaRepository<Shopcar, Long>> implements ShopcarService {

    private final ShopcarRepository shopcarRepository;

    public ShopcarServiceImpl(ParameterService parameterService,
                              DozerBeanMapper dozer,
                              ShopcarRepository shopcarRepository) {

        super(parameterService, dozer, ShopcarDTO.class);
        this.shopcarRepository = shopcarRepository;
    }

    @Override
    protected JpaRepository<Shopcar, Long> getRepository() {
        return this.shopcarRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ShopcarDTO findBySerial(String serial) {
        if (StringUtils.isBlank(serial)) {
            return null;
        }
        return convert(shopcarRepository.findBySerialUniqueId(serial));
    }

    @Override
    @Transactional(readOnly = true)
    public ShopcarDTO findByCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return convert(shopcarRepository.findByCustomer(customer));
    }
}
