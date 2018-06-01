package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.mappers.MapperBuilder;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.repositories.products.ProductImageRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductImageDTO;
import br.com.gbvbahia.ecommerce.services.products.ProductImageService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImageServiceImpl extends ServiceCommon<ProductImageDTO, ProductImage, Long, JpaRepository<ProductImage, Long>> implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    public ProductImageServiceImpl(ParameterService parameterService,
                                   ProductImageRepository productImageRepository) {

        super(parameterService, ProductImageDTO.class);
        this.productImageRepository = productImageRepository;
    }

    @Override
    protected JpaRepository<ProductImage, Long> getRepository() {
        return productImageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageDTO> listActivesByKeyPicture(KeyPicture key) {
        Integer amountProduct = getParameterService().getValueByKeyAsNumber(ParameterService.AMOUNT_PROMOTION_PRODUCT).intValue();
        List<ProductImage> productImages = productImageRepository.listByPromotionTag(true,
                                                                                     true,
                                                                                     key,
                                                                                     amountProduct);
        return convert(productImages);
    }

}
