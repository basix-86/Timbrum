package it.buch85.request;

import okhttp3.OkHttpClient;

/**
 * Created by mbacer on 23/04/14.
 */
public abstract class AbstractRequest {

    protected final OkHttpClient client;

    protected String url;

    public AbstractRequest(OkHttpClient client) {
        this.client = client;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
