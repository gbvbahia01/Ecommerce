/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public class StringHelper {

    /**
     * Trim, remove accents and put all string in lowercase mode.
     *
     * @param search
     * @return
     */
    public static String cleanToSearch(String search) {
        return StringUtils.lowerCase(StringUtils.stripAccents(StringUtils.trim(search)));
    }

    /**
     * Trim, remove accents and put all string in lowercase mode.
     * Insert in the beginning and at the string end.
     * @param search
     * @param append
     * @return
     */
    public static String cleanToSearchAppend(String search, String append) {
        return append + cleanToSearch(search) + append;
    }
}
