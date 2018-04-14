/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.controllers;

import br.com.gbvbahia.ecommerce.component.ImageIoHandlerComponent;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.services.ProductImageService;
import br.com.gbvbahia.ecommerce.services.ProductService;
import java.io.File;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author Guilherme
 */
public class ProductControllerTest {
    
    @Mock
    private ProductService productService;
    @Mock
    private ProductImageService productImageService;
    @Mock
    private ImageIoHandlerComponent imageIoHandlerComponent;
    
    private ProductController controller;
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ProductController(imageIoHandlerComponent, productService, productImageService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }


    /**
     * Test of renderImageFromDB method, of class ProductController.
     */
    @Test
    public void testRenderImageFromDiskResource() throws Exception {
        ProductImage prodImg = new ProductImage();
        prodImg.setId(1L);
        prodImg.setNamePicture("def_420_535.png");
        prodImg.setKeyPicture(KeyPicture.SIZE_420_535);
        
        Resource stateFile = new ClassPathResource(new StringBuilder("imgs").append(File.separator)
                                                                            .append(KeyPicture.SIZE_420_535.getDefaultImg())
                                                                            .toString());
        
        Optional<ProductImage> optProdImg = Optional.of(prodImg);
        Mockito.when(productImageService.findBydId(1L)).thenReturn(optProdImg);
        Mockito.when(imageIoHandlerComponent.getFileFromProducFile(prodImg, KeyPicture.SIZE_420_535.name())).thenReturn(stateFile.getFile());
        
        StringBuilder url = new StringBuilder("/product/").append(prodImg.getId())
                                                          .append("/productimg/")
                                                          .append(KeyPicture.SIZE_420_535.name());
        
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(url.toString()))
                                                  .andExpect(MockMvcResultMatchers.status().isOk())
                                                  .andReturn().getResponse();
        
        byte[] reponseBytes = response.getContentAsByteArray();
        
        assertEquals(FileUtils.readFileToByteArray(stateFile.getFile()).length, reponseBytes.length);
    }
    
}
