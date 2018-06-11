package br.com.gbvbahia.ecommerce.services.commons.impl;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.model.entity.commons.Parameter;
import br.com.gbvbahia.ecommerce.repositories.commons.ParameterRepository;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
public class ParameterServiceImplTest {

    private ParameterService parameterService;

    @Mock
    private ParameterRepository parameterRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        parameterService = new ParameterServiceImpl(parameterRepository, TestFactory.getDozerForUnitTest());
    }

    @Test
    public void testGetValueByKey_Null() {
        Mockito.when(parameterRepository.findById(ParameterService.AMOUNT_STOCK_CATEGORY)).thenReturn(Optional.empty());

        String value = parameterService.getValueByKey(ParameterService.AMOUNT_STOCK_CATEGORY);
        Assert.assertNull(value);
    }

    @Test
    public void testGetValueByKey_NotNull() {
        String valueExpected = "5";
        Parameter parameter = new Parameter(ParameterService.AMOUNT_STOCK_CATEGORY, valueExpected);
        Mockito.when(parameterRepository.findById(ParameterService.AMOUNT_STOCK_CATEGORY)).thenReturn(Optional.of(parameter));

        String value = parameterService.getValueByKey(ParameterService.AMOUNT_STOCK_CATEGORY);
        Assert.assertNotNull(value);
        Assert.assertEquals(valueExpected, value);
    }

    @Test
    public void getValueByKeyAsNumber_Null() {
        Mockito.when(parameterRepository.findById(ParameterService.AMOUNT_STOCK_CATEGORY)).thenReturn(Optional.empty());

        Number value = parameterService.getValueByKeyAsNumber(ParameterService.AMOUNT_STOCK_CATEGORY);
        Assert.assertNull(value);
    }

    @Test
    public void getValueByKeyAsNumber_NotNull() {
        String valueExpected = "5.5";
        Parameter parameter = new Parameter(ParameterService.AMOUNT_STOCK_CATEGORY, valueExpected);

        Mockito.when(parameterRepository.findById(ParameterService.AMOUNT_STOCK_CATEGORY)).thenReturn(Optional.of(parameter));

        Number value = parameterService.getValueByKeyAsNumber(ParameterService.AMOUNT_STOCK_CATEGORY);

        Assert.assertNotNull(value);
        Assert.assertEquals(Double.valueOf(valueExpected), value);
    }
}