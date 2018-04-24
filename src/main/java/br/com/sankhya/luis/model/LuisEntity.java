package br.com.sankhya.luis.model;

import br.com.sankhya.luis.util.JsonUtils;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author Tiago Peres
 * @version 1.0
 */
public class LuisEntity {
    private String name;
    private String type;
    private int startIndex;
    private int endIndex;
    private double score;
    private Map<String, Object> resolution;

    /**
     * Cria uma entidade LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON a ser convertido
     * @throws IllegalArgumentException se o objeto JSON for nulo
     */
    public LuisEntity(JSONObject json) {
        if (json == null)
            throw new IllegalArgumentException("O objeto JSON não pode ser nulo");

        this.loadFromJSON(json);
    }

    /**
     * Obtém o nome da entidade.
     *
     * @return uma string representando o nome da entidade
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém o tipo da entidade.
     *
     * @return uma string representando o tipo da entidade
     */
    public String getType() {
        return this.type;
    }

    /**
     * Obtém o índice no enunciado em que a entidade começa.
     *
     * @return um inteiro representando o índice em que a entidade começa
     */
    public int getStartIndex() {
        return this.startIndex;
    }

    /**
     * Obtém o índice no enunciado em que a entidade termina.
     *
     * @return um inteiro representando o índice em que a entidade termina
     */
    public int getEndIndex() {
        return this.endIndex;
    }

    /**
     * Obtém a pontuação atribuída à entidade detectada.
     *
     * @return um double representando a pontuação atribuída
     */
    public double getScore() {
        return this.score;
    }

    /**
     * Obtém uma matriz de valores.
     *
     * @return uma matriz de valores
     */
    public Map<String, Object> getResolution() {
        return this.resolution;
    }

    /**
     * Tem como objetivo popular a entidade LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON a ser convertido
     */
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
