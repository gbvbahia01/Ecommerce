/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.web.controllers;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.web.component.ImageIoHandlerComponent;
import br.com.gbvbahia.ecommerce.web.controllers.errors.AppExceptionHandler;
import br.com.gbvbahia.ecommerce.web.controllers.products.ProductController;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductImageDTO;
import br.com.gbvbahia.ecommerce.services.products.ProductImageService;
import br.com.gbvbahia.ecommerce.services.products.ProductService;
import org.apache.commons.io.FileUtils;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
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

import java.io.File;

import static org.junit.Assert.assertEquals;

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
    @Mock
    private ParameterService parameterService;

    private ProductController controller;
    private MockMvc mockMvc;
    private Mapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mapper = TestFactory.getDozerForUnitTest();

        controller = new ProductController(parameterService,
                                           imageIoHandlerComponent,
                                           productService,
                                           productImageService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new AppExceptionHandler(parameterService))
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

        ProductImageDTO piDto = mapper.map(prodImg, ProductImageDTO.class);

        Resource stateFile = new ClassPathResource(new StringBuilder("imgs").append(File.separator)
                                                                            .append(KeyPicture.SIZE_420_535.getDefaultImg())
                                                                            .toString());
        
        Mockito.when(productImageService.findById(1L)).thenReturn(piDto);
        Mockito.when(imageIoHandlerComponent.getFileFromProducFile(piDto,
                                                                   KeyPicture.SIZE_420_535.name()))
                    .thenReturn(stateFile.getFile());
        
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
