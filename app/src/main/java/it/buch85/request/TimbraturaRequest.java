package it.buch85.request;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TimbraturaRequest {

    public static final String VERSO_FIELD = "verso";
    public static final String VERSO_ENTRATA = "E";
    public static final String VERSO_USCITA = "U";

    private OkHttpClient client;

    protected String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public TimbraturaRequest(OkHttpClient client) {
        this.client = client;
    }

    private void timbraVerso(String verso, String mcId) throws IOException {

        RequestBody formBody = new FormBody.Builder()
                .add(VERSO_FIELD, verso)
                .add("m_cID", mcId)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println("Login form get: " + response);
    }

    public void entrata(String mcId) throws IOException {
        timbraVerso(VERSO_ENTRATA,mcId);
    }

    public void uscita(String mcId) throws IOException {
        timbraVerso(VERSO_USCITA, mcId);
    }
}
