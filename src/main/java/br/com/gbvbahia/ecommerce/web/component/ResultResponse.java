package br.com.gbvbahia.ecommerce.web.component;

public enum ResultResponse {

    SUCCESS_JSON("{\"msg\":\"success\"}"),
    ERROR_JSON("error");

    public final String result;

    ResultResponse(String result) {
        this.result = result;
    }
}
