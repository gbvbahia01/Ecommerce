package br.com.gbvbahia.ecommerce.model.enums;

public enum KeyPicture {

    SIZE_420_535("def_420_535.png", "420", "535");

    private final String defaultImg;
    private final String width;
    private final String height;

    KeyPicture(String defaultImg, String width, String height) {
        this.defaultImg = defaultImg;
        this.width = width;
        this.height = height;
    }

    //=====================
    // Getters and Setters
    //=====================
    public String getDefaultImg() {
        return defaultImg;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
