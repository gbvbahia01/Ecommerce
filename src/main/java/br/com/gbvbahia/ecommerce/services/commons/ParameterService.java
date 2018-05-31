package br.com.gbvbahia.ecommerce.services.commons;

import br.com.gbvbahia.ecommerce.services.dto.commons.ParameterDTO;
import br.com.gbvbahia.ecommerce.services.ServiceContract;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
public interface ParameterService extends ServiceContract<ParameterDTO, String> {

    String AMOUNT_STOCK_CATEGORY = "AMOUNT_STOCK_CATEGORY";
    String AMOUNT_CATEGORY_MENU = "AMOUNT_CATEGORY_MENU";
    String AMOUNT_PROMOTION_PRODUCT = "AMOUNT_PROMOTION_PRODUCT";
    String EMAIL_CONTACT = "CONTACT_EMAIL";
    String WHATS_CONTACT = "CONTACT_WHATS";
    String FACEBOOK_CONTACT = "CONTACT_FACEBOOK";

    String[] CONTACT_PARAMETERS = {EMAIL_CONTACT, WHATS_CONTACT, FACEBOOK_CONTACT};

    String getValueByKey(String key);

    Number getValueByKeyAsNumber(String key);

    List<ParameterDTO> listByRange(String... keys);

}
