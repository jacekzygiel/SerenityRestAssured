package com.zygiel;

public enum PostcodesEndpoints {
    LOOKUP("postcodes/{postcode}"),
    NEAREST("postcodes?lon={lon}&lat={lat}"),
    RANDOM("/random/postcodes"),
    VALIDATE("postcodes/{postcode}/validate");


    private final String url;

    PostcodesEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
