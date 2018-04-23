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

public class LuisClient {
    private final String URL = "https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/";
    private String appId;
    private String appKey;
    private boolean staging;
    private boolean verbose;

    public LuisClient(String appId, String appKey) {
        this(appId, appKey, false);
    }

    public LuisClient(String appId, String appKey, boolean staging) {
        this(appId, appKey, staging, true);
    }

    public LuisClient(String appId, String appKey, boolean staging, boolean verbose) {
        this.appId = appId;
        this.appKey = appKey;
        this.staging = staging;
        this.verbose = verbose;
    }

    public LuisResult query(String query) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();

        HttpGet request = this.buildRequest(query);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity == null) {
            throw new Exception("Service is temporarily unavailable, please try again later.");
        }

        return new LuisResult(new JSONObject(EntityUtils.toString(entity)));
    }

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
