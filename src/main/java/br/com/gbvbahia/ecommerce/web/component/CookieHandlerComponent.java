package br.com.gbvbahia.ecommerce.web.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Component
public class CookieHandlerComponent {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    public static final int MAX_COOKIES_DAYS = (int) (24.8D * 24L * 60L * 60L);
    /**
     * @param key the key used to identify the cookie in the client.
     * @param value the value stored in the client.
     * @param days Sets the maximum age in days for this Cookie. A positive value indicates
     * that the cookie will expire after that many days have passed. Note that the value is the
     * maximum age when the cookie will expire, not the cookie's current age. A negative value means
     * that the cookie is not stored persistently and will be deleted when the Web browser exits. A
     * zero value causes the cookie to be deleted.
     */
    public void setCookie(HttpServletRequest request,
                          HttpServletResponse response,
                          String key,
                          String value,
                          int days) {

        int expirySeconds = getCookieSeconds(days);
        logger.debug("Cookie asked[ key:{}, value:{}, expirySeconds:{}]", key, value, expirySeconds);

        Cookie cookie = getCookie(request, key);

        if (cookie != null) {
            cookie.setValue(value);
        } else {
            cookie = new Cookie(key, value);
            cookie.setPath(request.getContextPath());
        }
        cookie.setMaxAge(expirySeconds);
        response.addCookie(cookie);
        logger.debug("Cookie set[ key:{} value:{} was set for {} seconds]", key, value, expirySeconds);
    }

    public Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (int i = 0; i < userCookies.length; i++) {
                if (userCookies[i].getName().equals(name)) {
                    return userCookies[i];
                }
            }
        }
        return null;
    }

    /**
     * The maximum supported days are 24.8 days.<br>
     * Will tray to return: days * 24L * 60L * 60L;
     * This will convert days in seconds.
     * @param days limited at 2147483647 (Integer.MAX_VALUE)
     * @return Seconds
     */
    public int getCookieSeconds(int days) {
        Long total = days * 24L * 60L * 60L;
        if (total > MAX_COOKIES_DAYS) {
            logger.warn("The amount: {} * 24L * 60L * 60L are more than {}.", days, MAX_COOKIES_DAYS);
            return MAX_COOKIES_DAYS;
        } else {
            return total.intValue();
        }
    }
}
