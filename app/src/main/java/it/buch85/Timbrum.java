package it.buch85;

import it.buch85.request.LoginResult;
import it.buch85.request.LoginRequest;
import it.buch85.request.RecordTimbratura;
import it.buch85.request.ReportRequest;
import okhttp3.CookieJar;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by mbacer on 23/04/14.
 */
public class Timbrum {


    public static String LOGIN_URL             = "/servlet/cp_login";
    public static String TIMBRUS_URL           = "/servlet/ushp_ftimbrus";
    public static String SQL_DATA_PROVIDER_URL = "/servlet/SQLDataProviderServer";

    private final String              username;
    private final String              password;
	private String host;
    private final OkHttpClient client;

    public Timbrum(String host, String username, String password) {
        this.host = host;
		this.username = username;
        this.password = password;

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieJar cookieJar = new JavaNetCookieJar(cookieManager);

        client = new OkHttpClient.Builder()
                .followRedirects(false)
                .cookieJar(cookieJar)
                .build();
    }

    public ArrayList<RecordTimbratura> getReport(Date date) throws Exception {
        ReportRequest report = new ReportRequest(client);
        report.setUrl(host+SQL_DATA_PROVIDER_URL);
        return report.getTimbrature(new Date());

    }

    public LoginResult login() throws IOException {
        LoginRequest login = new LoginRequest(client);
        login.setUrl(host+LOGIN_URL);
        login.setUsername(username);
        login.setPassword(password);
        return login.submit();
    }


    public void timbra(String verso) throws IOException {
//        TimbraturaRequest timbratura = new TimbraturaRequest(new DefaultHttpClient(),context2);
//        timbratura.setUrl(host+TIMBRUS_URL);
//        if (TimbraturaRequest.VERSO_ENTRATA.equals(verso)) {
//           timbratura.entrata();
//        } else {
//           timbratura.uscita();
//        }
    }

}
