package br.com.sankhya.luis.model;

import org.json.JSONObject;

public class Intent {
    private String name;
    private double score;

    public Intent(JSONObject json) {
        if (json == null)
            throw new NullPointerException("JSON cannot be null");

        this.loadFromJSON(json);
    }

    public String getName() {
        return this.name;
    }

    public double getScore() {
        return this.score;
    }

    private void loadFromJSON(JSONObject json) {
        this.name = json.optString("intent");
        this.score = json.optDouble("score");
    }
}
