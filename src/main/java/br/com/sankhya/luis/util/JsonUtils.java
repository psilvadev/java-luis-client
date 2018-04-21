package br.com.sankhya.luis.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class JsonUtils {
    public static Map<String, Object> jsonObjectToMap(JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keysItr = jsonObject.keys();

        while (keysItr.hasNext()) {
            try {
                String key = keysItr.next();
                Object value = jsonObject.get(key);

                if (value instanceof JSONObject) {
                    value = jsonObjectToMap((JSONObject) value);
                } else if (value instanceof JSONArray) {
                    value = jsonArrayToList((JSONArray) value);
                }

                map.put(key, value);
            } catch (JSONException e) {
                System.out.println("LUIS Exception" + e.getMessage());

                break;
            }
        }

        return map;
    }

    public static List<Object> jsonArrayToList(JSONArray jsonArray) {
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Object value = jsonArray.get(i);

                if (value instanceof JSONObject) {
                    value = jsonObjectToMap((JSONObject) value);
                } else if (value instanceof JSONArray) {
                    value = jsonArrayToList((JSONArray) value);
                }

                list.add(value);
            } catch (JSONException e) {
                System.out.println("LUIS Exception" + e.getMessage());

                break;
            }
        }

        return list;
    }
}
