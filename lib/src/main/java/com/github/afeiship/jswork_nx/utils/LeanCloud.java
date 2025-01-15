package com.github.afeiship.jswork_nx.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;

import org.json.JSONObject;

public class LeanCloud {
    public static class LcOptions {
        public String baseURL;
        public String lcId;
        public String lcKey;

        public LcOptions() {
            this.baseURL = System.getenv("LEANCLOUD_URL");
            this.lcId = System.getenv("LEANCLOUD_ID");
            this.lcKey = System.getenv("LEANCLOUD_KEY");
        }

        public LcOptions(String baseURL, String lcId, String lcKey) {
            this.baseURL = baseURL;
            this.lcId = lcId;
            this.lcKey = lcKey;
        }
    }

    public static Map<String, String> headers(LcOptions options) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-LC-Id", options.lcId);
        headers.put("X-LC-Key", options.lcKey);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    public static JSONObject get(String id, LcOptions options) throws Exception {
        LcOptions finalOptions = new LcOptions(options.baseURL, options.lcId, options.lcKey);
        String url = finalOptions.baseURL + "/" + id;
        Map<String, String> headers = headers(finalOptions);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        headers.forEach(connection::setRequestProperty);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        return new JSONObject(content.toString());
    }

    public static JSONObject set(String id, Object value, LcOptions options) throws Exception {
        LcOptions finalOptions = new LcOptions(options.baseURL, options.lcId, options.lcKey);
        String url = finalOptions.baseURL + "/" + id;
        Map<String, String> headers = headers(finalOptions);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("PUT");
        headers.forEach(connection::setRequestProperty);

        connection.setDoOutput(true);
        connection.getOutputStream().write(new JSONObject().put("value", value).toString().getBytes());

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        return new JSONObject(content.toString());
    }

    public static Object val(String id, LcOptions options) throws Exception {
        JSONObject res = get(id, options);
        return res.opt("value");
    }

    private final String id;
    private final LcOptions options;

    public LeanCloud(String id, LcOptions options) {
        this.id = id;
        this.options = options;
    }

    public JSONObject get() throws Exception {
        return get(this.id, this.options);
    }

    public Object val() throws Exception {
        return val(this.id, this.options);
    }

    public JSONObject set(Object value) throws Exception {
        return set(this.id, value, this.options);
    }
}