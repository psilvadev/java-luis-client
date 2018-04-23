package br.com.sankhya.luis.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * @author Tiago Peres
 * @version 1.0
 */
public class JsonUtils {
    /**
     * Converts a JSONObject to a Map<String, Object>
     *
     * @param jsonObject A JSONObject that needs to be converted to a Map<String, Object>
     * @return A Map<String, Object> that contains the data of the JSONObject
     */
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

    /**
     * Converts a JSONArray to a List<Object>
     *
     * @param jsonArray A JSONArray that needs to be converted to a List<Object>
     * @return A List<Object> that contains the data of the JSONArray
     */
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
