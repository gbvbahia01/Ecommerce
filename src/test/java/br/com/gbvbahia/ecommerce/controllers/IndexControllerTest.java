package br.com.gbvbahia.ecommerce.controllers;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.controllers.helpers.ItemScreen;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.services.products.CategoryService;
import br.com.gbvbahia.ecommerce.services.products.ProductImageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    private Model model;

    @Mock
    private ProductImageService productImageService;

    @Mock
    private CategoryService categoryService;

    private IndexController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(productImageService, categoryService);
    }

    @Test
    public void testGetIndexPageSlash() throws Exception {

        Mockito.when(productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535)).thenReturn(new ArrayList<>());
        Mockito.when(categoryService.listCategoriesForMenu()).thenReturn(new ArrayList<>());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("hello"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("prodsImg"));
    }

    @Test
    public void testGetIndexPageSlashEcommerce() throws Exception {

        Mockito.when(productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535)).thenReturn(new ArrayList<>());
        Mockito.when(categoryService.listCategoriesForMenu()).thenReturn(new ArrayList<>());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("hello"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("prodsImg"));
    }

    @Test
    public void testModelItems() throws Exception {

        Mockito.when(productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535))
                .thenReturn(Collections.singletonList(TestFactory.makeProductImage(1)));

        Mockito.when(categoryService.listCategoriesForMenu())
                .thenReturn(Collections.singletonList(TestFactory.makeCategory(2)));

        ArgumentCaptor<List<ItemScreen>> argCaptorForProdImd = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<ItemScreen>> argCaptorCategory = ArgumentCaptor.forClass(List.class);

        controller.getIndexPage(model);

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("prodsImg"),
                                                                                     argCaptorForProdImd.capture());

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("categories"),
                argCaptorCategory.capture());

        Assert.assertEquals(1, argCaptorForProdImd.getValue().size());
        Assert.assertEquals(2L, argCaptorCategory.getValue().get(0).getId());
    }
}