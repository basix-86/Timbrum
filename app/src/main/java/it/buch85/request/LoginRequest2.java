package it.buch85.request;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mbacer on 11/04/14.
 */
public class LoginRequest2 {
    String username;
    String password;
    private static String USERNAME_FIELD = "m_cUserName";
    private static String PASSWORD_FIELD = "m_cPassword";
    private static String ACTION_FIELD = "m_cAction";
    private static String ACTION_FIELD_VALUE = "login";

    private static String REDIRECT_OK_URL = "/jsp/home.jsp";
    private Context context;
    protected String url;


    public LoginRequest2(Context context) {
        this.context = context;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private int mStatusCode;

    public void submit2() throws IOException {



        RequestQueue queue = Volley.newRequestQueue(context);
//for POST requests, only the following line should be changed to

        StringRequest sr = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("HttpClient", "status code: " + mStatusCode +" success! response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HttpClient", "error: " + error.toString());
                    }
                }) {

            ;

            @Override
            protected void deliverResponse(String response) {
                super.deliverResponse(response);
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(USERNAME_FIELD, username);
                params.put(PASSWORD_FIELD, password);
                params.put(ACTION_FIELD, ACTION_FIELD_VALUE);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                mStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }
}
