package br.com.gbvbahia.ecommerce.util;

import org.apache.commons.codec.binary.Base64;
import java.util.UUID;

/**
 * Project: ecommerce
 *
 * @author Guilherme Bahia
 * @version 1.0
 * @since 11/06/18
 */
public final class UniqueId {

    private UniqueId() {
    }

    /*
        http://stackoverflow.com/questions/772802/storing-uuid-as-base64-string
         */
    public static String createUniqueId() {
        Base64 base64 = new Base64();
        UUID uuid = UUID.randomUUID();
        return base64.encodeToString(uuid.toString().getBytes());
    }
}
