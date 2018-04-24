package br.com.sankhya.luis;

import br.com.sankhya.luis.model.LuisResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

/**
 * @author Tiago Peres
 * @version 1.0
 */
public class LuisClient {
    private final String URL = "https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/";
    private String appId;
    private String appKey;
    private boolean staging;
    private boolean verbose;

    /**
     * Cria um cliente LUIS.
     *
     * @param appId  ID do aplicativo
     * @param appKey chave de assinatura
     * @throws IllegalArgumentException se o ID do aplicativo ou chave de assinatura forem inválidos
     */
    public LuisClient(String appId, String appKey) {
        this(appId, appKey, false);
    }

    /**
     * Cria um cliente LUIS.
     *
     * @param appId   ID do aplicativo
     * @param appKey  chave de assinatura
     * @param staging usar ou não a versão staging
     * @throws IllegalArgumentException se o ID do aplicativo ou chave de assinatura forem inválidos
     */
    public LuisClient(String appId, String appKey, boolean staging) {
        this(appId, appKey, staging, true);
    }

    /**
     * Cria um cliente LUIS.
     *
     * @param appId   ID do aplicativo
     * @param appKey  chave de assinatura
     * @param staging usar ou não a versão staging
     * @param verbose usar ou não a versão detalhada
     * @throws IllegalArgumentException se o ID do aplicativo ou chave de assinatura forem inválidos
     */
    public LuisClient(String appId, String appKey, boolean staging, boolean verbose) {
        if (appId == null)
            throw new IllegalArgumentException("O ID do aplicativo não pode ser nulo");

        if (appId.isEmpty())
            throw new IllegalArgumentException("O ID do aplicativo não pode ser vazio");

        if (Pattern.compile("\\s").matcher(appId).find())
            throw new IllegalArgumentException("ID de aplicativo inválido");

        if (appKey == null)
            throw new IllegalArgumentException("A chave de assinatura não pode ser nulo");

        if (appKey.isEmpty())
            throw new IllegalArgumentException("A chave de assinatura não pode ser vazio");

        if (Pattern.compile("\\s").matcher(appKey).find())
            throw new IllegalArgumentException("Chave de assinatura inválida");

        this.appId = appId;
        this.appKey = appKey;
        this.staging = staging;
        this.verbose = verbose;
    }

    /**
     * Inicia o procedimento de previsão para o texto do usuário.
     *
     * @param query texto que precisa ser analisado e previsto
     * @return um resultado LUIS contendo o conteúdo da resposta enviada pela API
     * @throws Exception se o serviço estiver indisponível
     */
    public LuisResult query(String query) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();

        HttpGet request = this.buildRequest(query);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity == null) {
            throw new Exception("O serviço está temporariamente indisponível. Tente novamente mais tarde.");
        }

        return new LuisResult(new JSONObject(EntityUtils.toString(entity)));
    }

    /**
     * @param query texto que precisa ser analisado e previsto
     * @return um objeto contendo a requisição
     * @throws URISyntaxException se a sintaxe do URI estiver incorreta
     */
    private HttpGet buildRequest(String query) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(this.URL + this.appId + "?");

        builder.setParameter("q", query);
        builder.setParameter("staging", String.valueOf(this.staging));
        builder.setParameter("verbose", String.valueOf(this.verbose));

        URI uri = builder.build();
        HttpGet request = new HttpGet(uri);
        request.setHeader("Ocp-Apim-Subscription-Key", this.appKey);

        return request;
    }
}
