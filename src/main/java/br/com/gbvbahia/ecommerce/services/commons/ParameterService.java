package br.com.gbvbahia.ecommerce.services.commons;

import br.com.gbvbahia.ecommerce.model.entity.commons.Parameter;
import br.com.gbvbahia.ecommerce.services.ServiceContract;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
public interface ParameterService extends ServiceContract<Parameter, String> {

    String AMOUNT_STOCK_CATEGORY = "AMOUNT_STOCK_CATEGORY";
    String AMOUNT_CATEGORY_MENU = "AMOUNT_CATEGORY_MENU";
    String EMAIL_CONTACT =  "CONTACT_EMAIL";
    String WHATS_CONTACT = "CONTACT_WHATS";
    String FACEBOOK_CONTACT = "CONTACT_FACEBOOK";

    String getValueByKey(String key);
    Number getValueByKeyAsNumber(String key);

}
