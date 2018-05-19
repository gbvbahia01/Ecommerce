package br.com.gbvbahia.ecommerce.services;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import org.apache.commons.lang3.NotImplementedException;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class ServiceCommonTest {

    private ServiceCommon serviceCommon;

    @Mock
    private ParameterService parameterService;
    @Mock
    private DozerBeanMapper dozer;

    @Before
    public void setUp() {
        serviceCommon = new ServiceCommon(parameterService, dozer, Object.class) {
            @Override
            protected JpaRepository getRepository() {
                throw new NotImplementedException("Unit test environment");
            }
        };
    }

    @Test
    public void stringCleanForLikeINITTest() {
        String parameterSearch = "NõtWêllLîkéPÃramèter";
        String expectedParameter = "%notwelllikeparameter";

        StringBuilder result = serviceCommon.stringCleanForLike(ServiceCommon.Like.INIT, parameterSearch);

        Assert.assertEquals("Like.INIT fails", result.toString(), expectedParameter);

    }

    @Test
    public void stringCleanForLikeBETWEENTest() {
        String parameterSearch = "NõtWêllLîkéPÃramèter";
        String expectedParameter = "%notwelllikeparameter%";

        StringBuilder result = serviceCommon.stringCleanForLike(ServiceCommon.Like.BETWEEN, parameterSearch);

        Assert.assertEquals("Like.BETWEEN fails", result.toString(), expectedParameter);

    }

    @Test
    public void stringCleanForLikeENDTest() {
        String parameterSearch = "NõtWêllLîkéPÃramèter";
        String expectedParameter = "notwelllikeparameter%";

        StringBuilder result = serviceCommon.stringCleanForLike(ServiceCommon.Like.END, parameterSearch);

        Assert.assertEquals("Like.END fails", result.toString(), expectedParameter);

    }
}
