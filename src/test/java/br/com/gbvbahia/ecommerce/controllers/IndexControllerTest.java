package br.com.gbvbahia.ecommerce.controllers;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.controllers.helpers.ItemScreen;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
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

    @Mock
    private ParameterService parameterService;

    private IndexController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(parameterService,
                                         productImageService,
                                         categoryService);
    }

    @Test
    public void testGetIndexPageSlash() throws Exception {

        Mockito.when(productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535)).thenReturn(Collections.emptyList());
        Mockito.when(categoryService.listCategoriesForMenu()).thenReturn(Collections.emptyList());
        Mockito.when(parameterService.listByRange(ParameterService.CONTACT_PARAMETERS)).thenReturn(Collections.emptyList());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("hello"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("contacts"))
                .andExpect(model().attribute("contacts", 0))
                .andExpect(model().attributeExists("prodsImg"));
    }

    @Test
    public void testGetIndexPage() throws Exception {

        Mockito.when(productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535)).thenReturn(Collections.emptyList());
        Mockito.when(categoryService.listCategoriesForMenu()).thenReturn(Collections.emptyList());
        Mockito.when(parameterService.listByRange(ParameterService.CONTACT_PARAMETERS)).thenReturn(Collections.emptyList());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("hello"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("contacts"))
                .andExpect(model().attribute("contacts", 0))
                .andExpect(model().attributeExists("prodsImg"));
    }

    @Test
    public void testModelItems() throws Exception {

        Mockito.when(productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535))
                .thenReturn(Collections.singletonList(TestFactory.makeProductImage(1)));

        Mockito.when(categoryService.listCategoriesForMenu())
                .thenReturn(Collections.singletonList(TestFactory.makeCategory(2)));

        Mockito.when(parameterService.listByRange(ParameterService.CONTACT_PARAMETERS))
                .thenReturn(TestFactory.makeContactParameters());

        ArgumentCaptor<List<ItemScreen>> argCaptorForProdImd = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<ItemScreen>> argCaptorCategory = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<ItemScreen> argCaptorParamEmails = ArgumentCaptor.forClass(ItemScreen.class);
        ArgumentCaptor<ItemScreen> argCaptorParamWhats = ArgumentCaptor.forClass(ItemScreen.class);
        ArgumentCaptor<ItemScreen> argCaptorParamFace = ArgumentCaptor.forClass(ItemScreen.class);
        ArgumentCaptor<Integer> argCaptorParamContacts = ArgumentCaptor.forClass(Integer.class);

        controller.getIndexPage(model);

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("prodsImg"),
                                                                                     argCaptorForProdImd.capture());

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("categories"),
                argCaptorCategory.capture());

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("CONTACT_EMAIL"),
                                                             argCaptorParamEmails.capture());

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("CONTACT_WHATS"),
                                                             argCaptorParamWhats.capture());

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("CONTACT_FACEBOOK"),
                                                             argCaptorParamFace.capture());

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("contacts"),
                                                             argCaptorParamContacts.capture());

        Assert.assertEquals(1, argCaptorForProdImd.getValue().size());
        Assert.assertEquals(2L, argCaptorCategory.getValue().get(0).getId());
        Assert.assertEquals(TestFactory.EMAIL_TEST_VALUE, argCaptorParamEmails.getValue().getValue());
        Assert.assertEquals(TestFactory.WHATS_TEST_VALUE, argCaptorParamWhats.getValue().getValue());
        Assert.assertEquals(TestFactory.FACEB_TEST_VALUE, argCaptorParamFace.getValue().getValue());
        Assert.assertEquals(3, argCaptorParamContacts.getValue().intValue());
    }
}