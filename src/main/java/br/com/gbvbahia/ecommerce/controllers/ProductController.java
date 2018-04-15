/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.controllers;

import br.com.gbvbahia.ecommerce.component.ImageIoHandlerComponent;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.services.products.ProductImageService;
import br.com.gbvbahia.ecommerce.services.products.ProductService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Controller
public class ProductController extends ControllerCommon {

    private final ImageIoHandlerComponent imageIoHandlerComponent;
    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ImageIoHandlerComponent imageIoHandlerComponent,
                             ProductService productService,
                             ProductImageService productImageService) {

        this.imageIoHandlerComponent = imageIoHandlerComponent;
        this.productService = productService;
        this.productImageService = productImageService;
    }

    @GetMapping("product/{prodImgId}/productimg/{keyPicture}")
    public void renderImageFromDiskResource(@PathVariable String prodImgId,
                                            @PathVariable String keyPicture,
                                            HttpServletResponse response) throws IOException {

        logger.debug("Img for prodImg:{} for key:{} requested.", prodImgId, keyPicture);

        Optional<ProductImage> prodImgOpt = productImageService.findBydId(Long.valueOf(prodImgId));
        
        File imgDisk = imageIoHandlerComponent.getFileFromProducFile(prodImgOpt.get(), keyPicture);
        
        byte[] byteArray = FileUtils.readFileToByteArray(imgDisk);

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}
