package com.dss.util.enums;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

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
