/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.component;

import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.util.EnvironmentVariables;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Component
public class ImageIoHandlerComponent {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final Environment environment;

    public ImageIoHandlerComponent(Environment environment) {
        this.environment = environment;
    }

    public File getFileFromProducFile(ProductImage productImage, String keyPicture) {

        final String imgFolder = environment.getRequiredProperty(EnvironmentVariables.IMAGE_PATH_VARIABLE);
        try {
            if (productImage == null) {
                logger.warn("A null ProductImage id was requested");
                final KeyPicture keyImg = KeyPicture.valueOf(keyPicture);
                return new File(imgFolder, keyImg.getDefaultImg());
            }

            File imgDisk;
            imgDisk = new File(imgFolder, productImage.getNamePicture());
            if (!imgDisk.exists()) {
                logger.warn("A nonexistent file was requested: [" + productImage.getNamePicture() + "]");
                imgDisk = new File(imgFolder, productImage.getKeyPicture().getDefaultImg());
            }
            return imgDisk;
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error("A error occurs trying to get a IMG from disk", e);

            return new File(imgFolder, KeyPicture.SIZE_420_535.getDefaultImg());
        }
    }
}
