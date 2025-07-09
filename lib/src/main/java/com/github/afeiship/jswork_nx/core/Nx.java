package com.github.afeiship.jswork_nx.core;

import com.github.afeiship.jswork_nx.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

public class Nx {
    public static void log(Object... messages) {
        String prefix = "🍏 [jsw-nx-log]: ";
        logger(prefix, messages);
    }

    public static void error(String message) {
        String prefix = "❌ [jsw-nx-error]: ";
        message = prefix + message;
        System.err.println(message);
    }

    public static void warn(String message) {
        String prefix = "⚠️ [jsw-nx-warn]: ";
        message = prefix + message;
        System.err.println(message);
    }

    public static void info(String message) {
        String prefix = "🌈 [jsw-nx-info]: ";
        message = prefix + message;
        System.out.println(message);
    }

    // println use string format
    public static void println(String format, Object... args) {
        String message = String.format(format, args);
        System.out.println(message);
    }

    // sprintf use string format
    public static String sprintf(String format, Object... args) {
        return String.format(format, args);
    }

    private static void logger(String tag, Object... messages) {
        StringBuilder logMessage = new StringBuilder();
        for (Object msg : messages) {
            if (msg != null) {
                logMessage.append(msg.toString()).append(" ");
            } else {
                logMessage.append("null ");
            }
        }
        System.out.println(tag + logMessage);
    }


    public static Object get(JSONObject jsonObject, String key) {
        return get(jsonObject, key, null);
    }

    public static Object get(JSONObject jsonObject, String key, Object defaultValue) {
        if (jsonObject == null || key == null) {
            return defaultValue;
        }

        String[] keys = key.split("\\.");
        JSONObject currentObject = jsonObject;
        Object currentValue = null;

        for (int i = 0; i < keys.length; i++) {
            String currentKey = keys[i];

            if (i == keys.length - 1) {
                // Last key in the path
                if (!currentObject.has(currentKey)) {
                    return defaultValue;
                }
                currentValue = currentObject.opt(currentKey);
            } else {
                // Intermediate key
                if (!currentObject.has(currentKey)) {
                    return defaultValue;
                }
                Object next = currentObject.opt(currentKey);
                if (next instanceof JSONObject) {
                    currentObject = (JSONObject) next;
                } else if (next instanceof JSONArray) {
                    // If an intermediate key points to a JSONArray, we can't traverse further with dot notation
                    return defaultValue;
                } else {
                    // If an intermediate key points to a primitive, we can't traverse further
                    return defaultValue;
                }
            }
        }

        if (currentValue == null) {
            return defaultValue;
        }

        if (defaultValue instanceof String) {
            return String.valueOf(currentValue);
        } else if (defaultValue instanceof Integer) {
            if (currentValue instanceof Integer) {
                return currentValue;
            } else if (currentValue instanceof String) {
                try {
                    return Integer.parseInt((String) currentValue);
                } catch (NumberFormatException e) {
                    return defaultValue;
                }
            }
            return jsonObject.optInt(key, (Integer) defaultValue); // Fallback to original optInt if type mismatch
        } else if (defaultValue instanceof Boolean) {
            if (currentValue instanceof Boolean) {
                return currentValue;
            } else if (currentValue instanceof String) {
                return Boolean.parseBoolean((String) currentValue);
            }
            return jsonObject.optBoolean(key, (Boolean) defaultValue); // Fallback
        } else if (defaultValue instanceof Long) {
            if (currentValue instanceof Long) {
                return currentValue;
            } else if (currentValue instanceof String) {
                try {
                    return Long.parseLong((String) currentValue);
                } catch (NumberFormatException e) {
                    return defaultValue;
                }
            }
            return jsonObject.optLong(key, (Long) defaultValue); // Fallback
        } else if (defaultValue instanceof Double) {
            if (currentValue instanceof Double) {
                return currentValue;
            } else if (currentValue instanceof String) {
                try {
                    return Double.parseDouble((String) currentValue);
                } catch (NumberFormatException e) {
                    return defaultValue;
                }
            }
            return jsonObject.optDouble(key, (Double) defaultValue); // Fallback
        } else if (defaultValue instanceof JSONObject) {
            if (currentValue instanceof JSONObject) {
                return currentValue;
            } else if (currentValue instanceof String) {
                return JsonUtil.fromJson((String) currentValue, JSONObject.class);
            }
            return defaultValue;
        } else if (defaultValue instanceof JSONArray) {
            if (currentValue instanceof JSONArray) {
                return currentValue;
            } else if (currentValue instanceof String) {
                return JsonUtil.fromJson((String) currentValue, JSONArray.class);
            }
            return defaultValue;
        }
        return currentValue;
    }

    public static void set(JSONObject jsonObject, String key, Object value) {
        if (jsonObject == null || key == null) {
            return;
        }
        try {
            String[] keys = key.split("\\.");
            JSONObject currentObject = jsonObject;

            for (int i = 0; i < keys.length - 1; i++) {
                String currentKey = keys[i];
                if (!currentObject.has(currentKey) || !(currentObject.opt(currentKey) instanceof JSONObject)) {
                    JSONObject newObject = new JSONObject();
                    currentObject.put(currentKey, newObject);
                    currentObject = newObject;
                } else {
                    currentObject = currentObject.getJSONObject(currentKey);
                }
            }

            String lastKey = keys[keys.length - 1];
            if (value instanceof JSONObject || value instanceof JSONArray) {
                currentObject.put(lastKey, JsonUtil.toJson(value));
            } else {
                currentObject.put(lastKey, value);
            }
        } catch (Exception e) {
            // Handle exception, maybe log it
            e.printStackTrace();
        }
    }
}
