package br.com.sankhya.luis.model;

import br.com.sankhya.luis.util.JsonUtils;
import org.json.JSONObject;

import java.util.Map;

public class Entity {
    private String name;
    private String type;
    private int startIndex;
    private int endIndex;
    private double score;
    private Map<String, Object> resolution;

    public Entity(JSONObject json) {
        if (json == null)
            throw new NullPointerException("JSON cannot be null");

        this.loadFromJSON(json);
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public double getScore() {
        return this.score;
    }

    public Map<String, Object> getResolution() {
        return this.resolution;
    }

    private void loadFromJSON(JSONObject json) {
        this.name = json.optString("entity");
        this.type = json.optString("type");
        this.startIndex = json.optInt("startIndex");
        this.endIndex = json.optInt("endIndex");
        this.score = json.optDouble("score");

        JSONObject resolution = json.optJSONObject("resolution");
        this.resolution = (resolution != null) ? JsonUtils.jsonObjectToMap(resolution) : null;
    }
}
