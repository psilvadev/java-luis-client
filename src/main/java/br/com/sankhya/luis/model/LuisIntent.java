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
     * Cria uma intenção LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON a ser convertido
     * @throws IllegalArgumentException se o objeto JSON for nulo
     */
    public LuisIntent(JSONObject json) {
        if (json == null)
            throw new IllegalArgumentException("O objeto JSON não pode ser nulo");

        this.loadFromJSON(json);
    }

    /**
     * Obtém o nome da intenção.
     *
     * @return uma string representando o nome da intenção
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém a pontuação atribuída à intenção detectada.
     *
     * @return um double representando a pontuação atribuída
     */
    public double getScore() {
        return this.score;
    }

    /**
     * Tem como objetivo popular a intenção LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON a ser convertido
     */
    private void loadFromJSON(JSONObject json) {
        this.name = json.optString("intent");
        this.score = json.optDouble("score");
    }
}
