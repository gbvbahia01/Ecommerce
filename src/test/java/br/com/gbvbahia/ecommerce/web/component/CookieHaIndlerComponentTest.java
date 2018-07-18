package br.com.gbvbahia.ecommerce.web.component;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class CookieHaIndlerComponentTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ParameterService parameterService;

    private CookieHandlerComponent cookieHaIndlerComponent;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cookieHaIndlerComponent = new CookieHandlerComponent(parameterService);
        Mockito.when(parameterService.getValueByKey(ParameterService.APP_COOKIE_SHOPCAR_KEY)).thenReturn("ECOMMERCE_SHOPCART_COOKIE_KEY");
    }

    @Test
    public void testGetCookieSeconds() {
        int arg3DaysArgument = 3;
        int expected3DaysSeconds = 259200;

        assertEquals(expected3DaysSeconds, cookieHaIndlerComponent.getCookieSeconds(arg3DaysArgument));
    }

    @Test
    public void testGetCookieSecondsMaxInteger() {
        int argMaxDaysArgument = Integer.MAX_VALUE / 24 / 60 / 60 + 10;
        int expectedMaxDaysSeconds = CookieHandlerComponent.MAX_COOKIES_DAYS;

        assertEquals(expectedMaxDaysSeconds, cookieHaIndlerComponent.getCookieSeconds(argMaxDaysArgument));
    }


    @Test
    public void testSetCookie() {
        Cookie[] cookies = new Cookie[3];

        Cookie one = new Cookie("Cookie_1","1");
        Cookie two = new Cookie("Cookie_2","2");
        Cookie found = new Cookie("Cookie_Found","4");

        cookies[0] = one;
        cookies[1] = two;
        cookies[2] = found;

        ArgumentCaptor<Cookie> argumentCaptor = ArgumentCaptor.forClass(Cookie.class);

        Mockito.when(request.getCookies()).thenReturn(cookies);

        cookieHaIndlerComponent.setCookie(request, response, "Cookie_Found", "3", 1);

        Mockito.verify(response, Mockito.only()).addCookie(argumentCaptor.capture());

        Cookie result = argumentCaptor.getValue();

        assertEquals(found, result);
        assertEquals("3", result.getValue());
    }

    @Test
    public void testSetCookieNew() {
        Cookie[] cookies = new Cookie[2];

        Cookie one = new Cookie("Cookie_1","1");
        Cookie two = new Cookie("Cookie_2","2");

        cookies[0] = one;
        cookies[1] = two;

        ArgumentCaptor<Cookie> argumentCaptor = ArgumentCaptor.forClass(Cookie.class);

        Mockito.when(request.getCookies()).thenReturn(cookies);

        cookieHaIndlerComponent.setCookie(request, response, "Cookie_Found", "3", 1);

        Mockito.verify(response, Mockito.only()).addCookie(argumentCaptor.capture());

        Cookie result = argumentCaptor.getValue();

        assertNotNull(result);
        assertEquals("3", result.getValue());
    }

    @Test
    public void testGetCookieNullCookies() {
        Cookie cookie = cookieHaIndlerComponent.getCookie(request, "nullCookie");

        assertNull(cookie);
    }

    @Test
    public void testGetCookieNotFoundCookie() {
        Cookie[] cookies = new Cookie[2];

        Cookie one = new Cookie("Cookie_1","1");
        Cookie two = new Cookie("Cookie_2","2");

        cookies[0] = one;
        cookies[1] = two;

        Mockito.when(request.getCookies()).thenReturn(cookies);

        Cookie cookie = cookieHaIndlerComponent.getCookie(request, "notFoundCookie");

        assertNull(cookie);
    }

    @Test
    public void testGetCookieFoundCookie() {
        Cookie[] cookies = new Cookie[3];

        Cookie one = new Cookie("Cookie_1","1");
        Cookie two = new Cookie("Cookie_2","2");
        Cookie found = new Cookie("Cookie_Found","3");

        cookies[0] = one;
        cookies[1] = two;
        cookies[2] = found;

        Mockito.when(request.getCookies()).thenReturn(cookies);

        Cookie cookie = cookieHaIndlerComponent.getCookie(request, "Cookie_Found");

        assertEquals(found, cookie);
    }
}