package br.com.sankhya.luis.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LuisResult {
    private String query;
    private LuisIntent topScoringIntent;
    private List<LuisIntent> intents;
    private List<LuisEntity> entities;

    public LuisResult(JSONObject json) {
        if (json == null)
            throw new NullPointerException("JSON cannot be null");

        this.loadFromJSON(json);
    }

    public String getQuery() {
        return this.query;
    }

    public LuisIntent getTopScoringIntent() {
        return this.topScoringIntent;
    }

    public List<LuisIntent> getIntents() {
        return this.intents;
    }

    public List<LuisEntity> getEntities() {
        return this.entities;
    }

    private void loadFromJSON(JSONObject json) {
        this.query = json.optString("query");
        this.intents = new ArrayList<>();
        this.entities = new ArrayList<>();

        JSONObject jsonTopScoringIntent = json.optJSONObject("topScoringIntent");
        this.topScoringIntent = (jsonTopScoringIntent != null) ? new LuisIntent(jsonTopScoringIntent) : null;

        JSONArray jsonIntents = json.optJSONArray("intents");

        if (jsonIntents == null) {
            this.intents.add(this.topScoringIntent);
        } else {
            for (int i = 0; i < jsonIntents.length(); i++) {
                JSONObject jsonIntent = jsonIntents.optJSONObject(i);

                if (jsonIntent != null) {
                    this.intents.add(new LuisIntent(jsonIntent));
                }
            }
        }

        JSONArray jsonEntities = json.optJSONArray("entities");

        if (jsonEntities != null) {
            for (int i = 0; i < jsonEntities.length(); i++) {
                JSONObject jsonEntity = jsonEntities.optJSONObject(i);

                if (jsonEntity != null) {
                    this.entities.add(new LuisEntity(jsonEntity));
                }
            }
        }
    }
}
