package br.com.gbvbahia.ecommerce.services.impl;

import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.repositories.products.ProductImageRepository;
import br.com.gbvbahia.ecommerce.services.ProductImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class ProductImageServiceImpl extends ServiceCommon<ProductImage, Long, JpaRepository<ProductImage, Long>> implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    public ProductImageServiceImpl(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Override
    protected JpaRepository<ProductImage, Long> getRepository() {
        return productImageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImage> listActivesByKeyPicture(KeyPicture key) {
        return productImageRepository.listByPromotionTag(true, true, key);
    }

}
