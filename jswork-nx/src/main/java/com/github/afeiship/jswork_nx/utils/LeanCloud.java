package com.github.afeiship.jswork_nx.utils;

public class LeanCloud {
    private static String appId;
    private static String apKey;
    private static String appUrl;

    // app_Id, app_Key, app_url use object initialization
    public LeanCloud(Options options) {
        appId = options.key();
        apKey = options.secret();
        appUrl = options.url();
    }

    // Print LeanCloud config
    public String toString() {
        return "appId: " + appId + ", apKey: " + apKey + ", appUrl: " + appUrl;
    }

    // Options record
    public record Options(String key, String secret, String url) {
    }
}
