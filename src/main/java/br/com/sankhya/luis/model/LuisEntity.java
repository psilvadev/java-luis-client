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
     * Constrói uma entidade LUIS a partir de um objeto JSON.
     *
     * @param json objeto JSON
     * @throws IllegalArgumentException se o objeto json for nulo
     */
    public LuisEntity(JSONObject json) {
        if (json == null)
            throw new IllegalArgumentException("O objeto JSON não pode ser nulo");

        this.loadFromJSON(json);
    }

    /**
     * Obtém o nome da entidade extraída pelo LUIS.
     *
     * @return o nome da entidade
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém o tipo da entidade.
     *
     * @return o tipo da entidade
     */
    public String getType() {
        return this.type;
    }

    /**
     * Obtém o índice no enunciado em que a entidade começa.
     *
     * @return o índice que a entidade começa
     */
    public int getStartIndex() {
        return this.startIndex;
    }

    /**
     * Obtém o índice no enunciado em que a entidade termina.
     *
     * @return o índice que a entidade termina
     */
    public int getEndIndex() {
        return this.endIndex;
    }

    /**
     * Obtém a pontuação atribuída pelo LUIS à entidade detectada.
     *
     * @return a pontuação da entidade
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
     * @param json objeto JSON
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
