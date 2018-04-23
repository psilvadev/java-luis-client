package br.com.sankhya.luis.model;

import org.json.JSONObject;

/**
 * @author Tiago Peres
 * @version 1.0
 */
public class LuisIntent {
    private String name;
    private double score;

    /**
     * Constrói uma intenção LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON
     * @throws IllegalArgumentException se o objeto json for nulo
     */
    public LuisIntent(JSONObject json) {
        if (json == null)
            throw new IllegalArgumentException("O objeto JSON não pode ser nulo");

        this.loadFromJSON(json);
    }

    /**
     * Obtém o nome da intenção extraída pelo LUIS.
     *
     * @return o nome da intenção
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém a pontuação atribuída pelo LUIS à intenção detectada.
     *
     * @return a pontuação da intenção
     */
    public double getScore() {
        return this.score;
    }

    /**
     * Tem como objetivo popular a intenção LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON
     */
    private void loadFromJSON(JSONObject json) {
        this.name = json.optString("intent");
        this.score = json.optDouble("score");
    }
}
