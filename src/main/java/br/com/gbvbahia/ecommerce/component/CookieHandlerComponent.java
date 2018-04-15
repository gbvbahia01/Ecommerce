package br.com.gbvbahia.ecommerce.component;

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
        Cookie cookie = null;
        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (int i = 0; i < userCookies.length; i++) {
                if (userCookies[i].getName().equals(name)) {
                    cookie = userCookies[i];
                    return cookie;
                }
            }
        }
        return cookie;
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
        if (total > Integer.MAX_VALUE) {
            logger.warn("The amount: " + days + " in days are more than Integer.MAX_VALUE in seconds");
            return Integer.MAX_VALUE;
        } else {
            return total.intValue();
        }
    }
}
