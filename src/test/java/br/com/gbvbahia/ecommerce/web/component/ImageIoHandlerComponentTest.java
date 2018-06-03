/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.web.component;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductImageDTO;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.util.EnvironmentVariables;
import java.io.File;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author Guilherme
 */
public class ImageIoHandlerComponentTest {

    @Mock
    private Environment environment;

    private ImageIoHandlerComponent imageIoHandlerComponent;
    private Mapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        imageIoHandlerComponent = new ImageIoHandlerComponent(environment);
        mapper = TestFactory.getDozerForUnitTest();
    }

    /**
     * Test of getFileFromProducFile method, of class ImageIoHandlerComponent.
     */
    @Test
    public void testGetFileFromProducFileNullArgs() throws Exception {
         Resource expectedFile = new ClassPathResource(new StringBuilder("imgs").append(File.separator)
                                                                                .append(KeyPicture.SIZE_420_535.getDefaultImg())
                                                                                .toString());
         String path = expectedFile.getFile().getPath().replace(KeyPicture.SIZE_420_535.getDefaultImg(), "");
         Mockito.when(environment.getRequiredProperty(EnvironmentVariables.IMAGE_PATH_VARIABLE)).thenReturn(path);
         
         File file = imageIoHandlerComponent.getFileFromProducFile(null, null);
         
         assertEquals(expectedFile.getFile(), file);
    }

    /**
     * Test of getFileFromProducFile method, of class ImageIoHandlerComponent.
     */
    @Test
    public void testGetFileFromProducFileNullAndInvalidKey() throws Exception {
        Resource expectedFile = new ClassPathResource(new StringBuilder("imgs").append(File.separator)
                                                                               .append(KeyPicture.SIZE_420_535.getDefaultImg())
                                                                               .toString());
        String path = expectedFile.getFile().getPath().replace(KeyPicture.SIZE_420_535.getDefaultImg(), "");
        Mockito.when(environment.getRequiredProperty(EnvironmentVariables.IMAGE_PATH_VARIABLE)).thenReturn(path);

        File file = imageIoHandlerComponent.getFileFromProducFile(null, "Invalid");

        assertEquals(expectedFile.getFile(), file);
    }
    
    /**
     * Test of getFileFromProducFile method, of class ImageIoHandlerComponent.
     */
    @Test
    public void testGetFileFromProducFileNullAndValidKey() throws Exception {
        Resource expectedFile = new ClassPathResource(new StringBuilder("imgs").append(File.separator)
                                                                               .append(KeyPicture.SIZE_420_535.getDefaultImg())
                                                                               .toString());
        
        String path = expectedFile.getFile().getPath().replace(KeyPicture.SIZE_420_535.getDefaultImg(), "");
        Mockito.when(environment.getRequiredProperty(EnvironmentVariables.IMAGE_PATH_VARIABLE)).thenReturn(path);

        File file = imageIoHandlerComponent.getFileFromProducFile(null, KeyPicture.SIZE_420_535.name());

        assertEquals(expectedFile.getFile(), file);
    }
    
     /**
     * Test of getFileFromProducFile method, of class ImageIoHandlerComponent.
     */
    @Test
    public void testGetFileFromProducFileNoFileAndValidKey() throws Exception {
        Resource expectedFile = new ClassPathResource(new StringBuilder("imgs").append(File.separator)
                                                                               .append(KeyPicture.SIZE_420_535.getDefaultImg())
                                                                               .toString());
        
        ProductImage prodImg = new ProductImage();
        prodImg.setId(1L);
        prodImg.setNamePicture("__50_50._");
        prodImg.setKeyPicture(KeyPicture.SIZE_420_535);

        ProductImageDTO piDto = mapper.map(prodImg, ProductImageDTO.class);
        
        String path = expectedFile.getFile().getPath().replace(KeyPicture.SIZE_420_535.getDefaultImg(), "");
        Mockito.when(environment.getRequiredProperty(EnvironmentVariables.IMAGE_PATH_VARIABLE)).thenReturn(path);

        File file = imageIoHandlerComponent.getFileFromProducFile(piDto, KeyPicture.SIZE_420_535.name());

        
        assertEquals(expectedFile.getFile(), file);
    }
    
    /**
     * Test of getFileFromProducFile method, of class ImageIoHandlerComponent.
     */
    @Test
    public void testGetFileFromProducFileProductAndValidKey() throws Exception {
        Resource expectedFile = new ClassPathResource(new StringBuilder("imgs").append(File.separator)
                                                                               .append("def_50_50.jpg")
                                                                               .toString());
        
        ProductImage prodImg = new ProductImage();
        prodImg.setId(1L);
        prodImg.setNamePicture("def_50_50.jpg");
        prodImg.setKeyPicture(KeyPicture.SIZE_420_535);

        ProductImageDTO piDto = mapper.map(prodImg, ProductImageDTO.class);

        Resource stateFile = new ClassPathResource(new StringBuilder("imgs").append(File.separator)
                                                                            .append(KeyPicture.SIZE_420_535.getDefaultImg())
                                                                            .toString());
        
        String path = stateFile.getFile().getPath().replace(KeyPicture.SIZE_420_535.getDefaultImg(), "");
        Mockito.when(environment.getRequiredProperty(EnvironmentVariables.IMAGE_PATH_VARIABLE)).thenReturn(path);

        File file = imageIoHandlerComponent.getFileFromProducFile(piDto, KeyPicture.SIZE_420_535.name());

        
        assertEquals(expectedFile.getFile(), file);
    }
}
