package br.com.sankhya.luis.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiago Peres
 * @version 1.0
 */
public class LuisResult {
    private String query;
    private LuisIntent topScoringIntent;
    private List<LuisIntent> intents;
    private List<LuisEntity> entities;

    /**
     * Constrói um resultado LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON
     * @throws IllegalArgumentException se o objeto json for nulo
     */
    public LuisResult(JSONObject json) {
        if (json == null)
            throw new IllegalArgumentException("O objeto JSON não pode ser nulo");

        this.loadFromJSON(json);
    }

    /**
     * Obtém a consulta enviada para o LUIS.
     *
     * @return a consulta enviada
     */
    public String getQuery() {
        return this.query;
    }

    /**
     * Obtém a intenção que possui maior pontuação.
     *
     * @return a intenção com maior pontuação
     */
    public LuisIntent getTopScoringIntent() {
        return this.topScoringIntent;
    }

    /**
     * Obtém as intenções encontradas no texto da consulta.
     *
     * @return uma lista de intenções
     */
    public List<LuisIntent> getIntents() {
        return this.intents;
    }

    /**
     * Obtém as entidades encontradas no texto da consulta.
     *
     * @return uma lista de entidades
     */
    public List<LuisEntity> getEntities() {
        return this.entities;
    }

    /**
     * Tem como objetivo popular o resultado LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON
     */
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
