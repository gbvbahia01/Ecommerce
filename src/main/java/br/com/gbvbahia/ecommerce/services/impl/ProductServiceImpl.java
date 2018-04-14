package br.com.gbvbahia.ecommerce.services.impl;

import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import br.com.gbvbahia.ecommerce.repositories.products.ProductRepository;
import br.com.gbvbahia.ecommerce.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class ProductServiceImpl extends ServiceCommon<Product, Long, JpaRepository<Product, Long>> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    protected JpaRepository getRepository() {
        return productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> search(String search) {

        StringBuilder clean = super.stringCleanForLike(Like.BETWEEN, search);

        logger.debug("Receive: {}, search for: {}", search, clean.toString());

        return productRepository.searchClean(clean.toString(), clean.toString());
    }

}
