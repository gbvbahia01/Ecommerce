package br.com.gbvbahia.ecommerce.web.controllers;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.commons.ParameterDTO;
import br.com.gbvbahia.ecommerce.services.dto.products.CategoryDTO;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductImageDTO;
import br.com.gbvbahia.ecommerce.services.orders.ShopCartService;
import br.com.gbvbahia.ecommerce.services.products.CategoryService;
import br.com.gbvbahia.ecommerce.services.products.ProductImageService;
import org.dozer.Mapper;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IndexControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Model model;

    @Mock
    private ProductImageService productImageService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ShopCartService shopCartService;

    @Mock
    private ParameterService parameterService;

    private IndexController controller;

    private Mapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mapper = TestFactory.getDozerForUnitTest();

        controller = new IndexController(parameterService,
                                         productImageService,
                                         categoryService,
                                         shopCartService);
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
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("contacts"))
                .andExpect(model().attribute("contacts", 0))
                .andExpect(model().attributeExists("promotionItems"));
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
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("contacts"))
                .andExpect(model().attribute("contacts", 0))
                .andExpect(model().attributeExists("promotionItems"));
    }

    @Test
    public void testModelItems() throws Exception {

        Mockito.when(productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535))
                .thenReturn(Collections.singletonList(mapper.map(TestFactory.makeProductImage(1),
                                                                 ProductImageDTO.class)));

        Mockito.when(categoryService.listCategoriesForMenu())
                .thenReturn(Collections.singletonList(mapper.map(TestFactory.makeCategory(2),
                                                                 CategoryDTO.class)));

        Mockito.when(parameterService.listByRange(ParameterService.CONTACT_PARAMETERS))
                .thenReturn(TestFactory.makeContactParameters());

        ArgumentCaptor<List<ProductImageDTO>> argCaptorForProdImd = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<CategoryDTO>> argCaptorCategory = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<ParameterDTO> argCaptorParamEmails = ArgumentCaptor.forClass(ParameterDTO.class);
        ArgumentCaptor<ParameterDTO> argCaptorParamWhats = ArgumentCaptor.forClass(ParameterDTO.class);
        ArgumentCaptor<ParameterDTO> argCaptorParamFace = ArgumentCaptor.forClass(ParameterDTO.class);
        ArgumentCaptor<Integer> argCaptorParamContacts = ArgumentCaptor.forClass(Integer.class);

        controller.getIndexPage(model, request, response);

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("promotionItems"),
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
        Assert.assertEquals(Long.valueOf(2L), argCaptorCategory.getValue().get(0).getId());
        Assert.assertEquals(TestFactory.EMAIL_TEST_VALUE, argCaptorParamEmails.getValue().getValue());
        Assert.assertEquals(TestFactory.WHATS_TEST_VALUE, argCaptorParamWhats.getValue().getValue());
        Assert.assertEquals(TestFactory.FACEB_TEST_VALUE, argCaptorParamFace.getValue().getValue());
        Assert.assertEquals(3, argCaptorParamContacts.getValue().intValue());
    }
}