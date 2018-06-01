/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.controllers.products;

import br.com.gbvbahia.ecommerce.component.ImageIoHandlerComponent;
import br.com.gbvbahia.ecommerce.controllers.ControllerCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductImageDTO;
import br.com.gbvbahia.ecommerce.services.products.ProductImageService;
import br.com.gbvbahia.ecommerce.services.products.ProductService;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

    public ProductController(ParameterService parameterService,
                             ImageIoHandlerComponent imageIoHandlerComponent,
                             ProductService productService,
                             ProductImageService productImageService) {

        super(parameterService);
        this.imageIoHandlerComponent = imageIoHandlerComponent;
        this.productService = productService;
        this.productImageService = productImageService;
    }

    @GetMapping("product/{prodImgId}/productimg/{keyPicture}")
    public void renderImageFromDiskResource(@PathVariable String prodImgId,
                                            @PathVariable String keyPicture,
                                            HttpServletResponse response) throws IOException {

        logger.debug("Img for prodImg:{} for key:{} requested.", prodImgId, keyPicture);

        ProductImageDTO prodImgDto = productImageService.findById(Long.valueOf(prodImgId));
        
        File imgDisk = imageIoHandlerComponent.getFileFromProducFile(prodImgDto, keyPicture);
        
        byte[] byteArray = FileUtils.readFileToByteArray(imgDisk);

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}
