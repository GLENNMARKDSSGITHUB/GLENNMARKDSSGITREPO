package com.dss.util.enums;

public enum StringUrl {
    DSS3_URL("http://localhost:9005"),
    DSS4_URL("http://localhost:9006"),
    DSS5_URL("http://localhost:9007"),
    DSS6_URL("http://localhost:9008");

    private final String url;
    StringUrl(String dssUrl) {
        this.url = dssUrl;
    }

    public String getUrl() {
        return url;
    }
}
