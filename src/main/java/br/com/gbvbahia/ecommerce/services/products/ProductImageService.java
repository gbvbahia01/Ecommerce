package br.com.gbvbahia.ecommerce.services.products;

import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.services.ServiceContract;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public interface ProductImageService extends ServiceContract<ProductImage, Long> {

    /**
     * List all ProductsImage by KeyPicture that are in a active campaign.
     * @param key Enum keyPicture that represents a image size.
     * @return A list with all ProductImage fonded or a empty list if any one was founded.
     */
    List<ProductImage> listActivesByKeyPicture(KeyPicture key);
    
}
