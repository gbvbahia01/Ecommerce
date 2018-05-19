package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.services.helpers.products.ProductDTO;
import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import br.com.gbvbahia.ecommerce.repositories.products.ProductRepository;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.products.ProductService;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class ProductServiceImpl extends ServiceCommon<ProductDTO, Product, Long, JpaRepository<Product, Long>> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ParameterService parameterService,
                              DozerBeanMapper dozer,
                              ProductRepository productRepository) {

        super(parameterService, dozer, ProductDTO.class);
        this.productRepository = productRepository;
    }

    @Override
    protected JpaRepository getRepository() {
        return productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> search(String search) {

        StringBuilder clean = super.stringCleanForLike(Like.BETWEEN, search);

        logger.debug("Receive: {}, search for: {}", search, clean.toString());

        return convert(productRepository.searchClean(clean.toString(), clean.toString()));
    }

}
