package br.com.gbvbahia.ecommerce.services.impl;

import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import br.com.gbvbahia.ecommerce.repositories.products.ProductImageRepository;
import br.com.gbvbahia.ecommerce.repositories.products.ProductRepository;
import br.com.gbvbahia.ecommerce.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceCommon implements ProductService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductImageRepository productImageRepository) {

        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> search(String search) {

        StringBuilder clean = super.stringCleanForLike(Like.BETWEEN, search);

        logger.debug("Receive: {}, search for: {}", search, clean.toString());

        return productRepository.searchClean(clean.toString(), clean.toString());
    }

}
