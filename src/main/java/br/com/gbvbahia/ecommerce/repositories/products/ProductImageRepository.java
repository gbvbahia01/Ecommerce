package br.com.gbvbahia.ecommerce.repositories.products;

import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    /**
     * Get app Products by promotion tag filtering by actives campaigns and/or CampaignProduct.
     *
     * @param actCamProd Actives CampaignProduct.
     * @param actCam Actives Campaigns.
     * @param key KeyPicture tag size.
     * @return All ProductImages founded. Empty list if any Product was found.
     */
    @Query(name = "ProductImage.withCampaign")
    public List<ProductImage> listByPromotionTag(@Param("actCam") boolean actCam,
                                                  @Param("actCamProd") boolean actCamProd,
                                                  @Param("keyPicture") KeyPicture key);

}
