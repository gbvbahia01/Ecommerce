package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.exceptions.NotFoundException;
import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductStock;
import br.com.gbvbahia.ecommerce.repositories.products.ProductRepository;
import br.com.gbvbahia.ecommerce.repositories.products.ProductStockRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductDTO;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductStockDTO;
import br.com.gbvbahia.ecommerce.services.products.ProductService;
import org.dozer.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends ServiceCommon<ProductDTO, Product, Long, JpaRepository<Product, Long>> implements ProductService {

    private final ProductRepository productRepository;
    private final ProductStockRepository productStockRepository;

    public ProductServiceImpl(ParameterService parameterService,
                              ProductRepository productRepository,
                              ProductStockRepository productStockRepository,
                              Mapper mapper) {

        super(parameterService, mapper, ProductDTO.class);
        this.productRepository = productRepository;
        this.productStockRepository = productStockRepository;
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

    @Override
    public ProductStockDTO findByProductStockId(Long id) {
        if (id == null) {
            throw new NotFoundException("ProductStock id CANNOT be null.");
        }

        Optional<ProductStock> optional = productStockRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException("No ProductStock found for id:["+ id + "]");
        }

        return getMapper().map(optional.get(), ProductStockDTO.class);
    }


}
