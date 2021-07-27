package it.buch85.timbrum.request;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginRequest {

    private OkHttpClient client;

    public LoginRequest(OkHttpClient client) {
        this.client = client;
    }

    String username = "test";
    String password = "test";
    private static String USERNAME_FIELD = "m_cUserName";
    private static String PASSWORD_FIELD = "m_cPassword";
    private static String ACTION_FIELD = "m_cAction";
    private static String ACTION_FIELD_VALUE = "login";

    private static String REDIRECT_OK_URL = "/jsp/home.jsp";
    protected String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginResult submit() throws IOException {

        RequestBody formBody = new FormBody.Builder()
                .add(USERNAME_FIELD, username)
                .add(PASSWORD_FIELD, password)
                .add(ACTION_FIELD, ACTION_FIELD_VALUE)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        String message = "";

        if (response.code() == 302) {
            if (response.header("Location").endsWith(REDIRECT_OK_URL)) {
                return new LoginResult(true, message);
            }
        } else {
            message = response.header("JSURL-Message");
        }
        return new LoginResult(false, message);
    }
}
