package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.repositories.products.CategoryRepository;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.commons.ParameterDTO;
import br.com.gbvbahia.ecommerce.services.products.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 01/05/18
 */
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ParameterService parameterService;

    private CategoryService categoryService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


        categoryService = new CategoryServiceImpl(parameterService,
                                                  categoryRepository);
    }

    @Test
    public void testListCategoriesForMenu_Limited() {
        Integer expectedAmount = 2;
        String expectedLimited = "limited";
        Integer expectedLimitMenu = 12;

        ParameterDTO amountParameter = new ParameterDTO(ParameterService.AMOUNT_STOCK_CATEGORY,
                                                        "2",
                                                        true);
        ParameterDTO menuMaxParameter = new ParameterDTO(ParameterService.AMOUNT_CATEGORY_MENU,
                                                         "12",
                                                         true);

        ArgumentCaptor<Integer> arg1Captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> arg2Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Pageable> arg3Captor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.when(parameterService.findById(ParameterService.AMOUNT_STOCK_CATEGORY)).thenReturn(amountParameter);
        Mockito.when(parameterService.findById(ParameterService.AMOUNT_CATEGORY_MENU)).thenReturn(menuMaxParameter);

        categoryService.listCategoriesForMenu();

        Mockito.verify(categoryRepository, Mockito.times(1)).listAllForMenu(arg1Captor.capture(),
                                                                            arg2Captor.capture(),
                                                                            arg3Captor.capture());
        Assert.assertEquals(expectedAmount, arg1Captor.getValue());
        Assert.assertEquals(expectedLimited, arg2Captor.getValue());
        Assert.assertEquals(expectedLimitMenu, Integer.valueOf(arg3Captor.getValue().getPageSize()));
    }

    @Test
    public void testListCategoriesForMenu_Unlimited() {
        Integer expectedAmount = 0;
        String expectedLimited = "unlimited";
        Integer expectedLimitMenu = 12;

        ParameterDTO amountParameter = new ParameterDTO(ParameterService.AMOUNT_STOCK_CATEGORY,
                                                        "0",
                                                        false);
        ParameterDTO menuMaxParameter = new ParameterDTO(ParameterService.AMOUNT_CATEGORY_MENU,
                                                         "12",
                                                         true);

        ArgumentCaptor<Integer> arg1Captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> arg2Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Pageable> arg3Captor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.when(parameterService.findById(ParameterService.AMOUNT_STOCK_CATEGORY)).thenReturn(amountParameter);
        Mockito.when(parameterService.findById(ParameterService.AMOUNT_CATEGORY_MENU)).thenReturn(menuMaxParameter);

        categoryService.listCategoriesForMenu();

        Mockito.verify(categoryRepository, Mockito.times(1)).listAllForMenu(arg1Captor.capture(),
                                                                            arg2Captor.capture(),
                                                                            arg3Captor.capture());
        Assert.assertEquals(expectedAmount, arg1Captor.getValue());
        Assert.assertEquals(expectedLimited, arg2Captor.getValue());
        Assert.assertEquals(expectedLimitMenu, Integer.valueOf(arg3Captor.getValue().getPageSize()));
    }
}