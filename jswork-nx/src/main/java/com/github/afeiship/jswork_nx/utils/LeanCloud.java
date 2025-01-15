package com.github.afeiship.jswork_nx.utils;

public class LeanCloud {
    private static String appId;
    private static String apKey;
    private static String appUrl;

    // app_Id, app_Key, app_url use object initialization
    public LeanCloud(Options options) {
        appId = options.getKey();
        apKey = options.getSecret();
        appUrl = options.getUrl();
    }

    public String toString() {
        return "LeanCloud{" +
                "appId='" + appId + '\'' +
                ", apKey='" + apKey + '\'' +
                ", appUrl='" + appUrl + '\'' +
                '}';
    }

    // Config类用于封装参数
    public static class Options {
        private final String key;
        private final String secret;
        private final String url;

        public Options(String key, String secret, String url) {
            this.key = key;
            this.secret = secret;
            this.url = url;
        }

        public String getKey() {
            return key;
        }

        public String getSecret() {
            return secret;
        }

        public String getUrl() {
            return url;
        }
    }
}
