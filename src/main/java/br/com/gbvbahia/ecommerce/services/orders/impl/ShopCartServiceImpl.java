package br.com.gbvbahia.ecommerce.services.orders.impl;

import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.ShopCart;
import br.com.gbvbahia.ecommerce.repositories.orders.ShopCartRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.orders.ShopCartDTO;
import br.com.gbvbahia.ecommerce.services.orders.ShopCartService;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
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
public class ShopCartServiceImpl extends ServiceCommon<ShopCartDTO, ShopCart, Long, JpaRepository<ShopCart, Long>> implements ShopCartService {

    private final ShopCartRepository shopCartRepository;

    public ShopCartServiceImpl(ParameterService parameterService,
                               ShopCartRepository shopCartRepository,
                               Mapper mapper) {

        super(parameterService, mapper, ShopCartDTO.class);
        this.shopCartRepository = shopCartRepository;
    }

    @Override
    protected JpaRepository<ShopCart, Long> getRepository() {
        return this.shopCartRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ShopCartDTO findBySerial(String serial) {
        if (StringUtils.isBlank(serial)) {
            return null;
        }
        return convert(shopCartRepository.findBySerialUniqueId(serial));
    }

    @Override
    @Transactional(readOnly = true)
    public ShopCartDTO findByCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return convert(shopCartRepository.findByCustomer(customer));
    }

    @Override
    public ShopCartDTO saveShopcar(ShopCartDTO shopCartDTO) {
        ShopCart shopCart = convert(shopCartDTO, new ShopCart());
        shopCartRepository.save(shopCart);
        return convert(shopCart);
    }
}
