package it.buch85;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Date;

import it.buch85.request.LoginRequest;
import it.buch85.request.LoginResult;
import it.buch85.request.RecordTimbratura;
import it.buch85.request.ReportRequest;
import it.buch85.request.RequestException;
import it.buch85.request.TimbraturaRequest;
import it.buch85.request.WorkspaceRequest;
import okhttp3.CookieJar;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;


/**
 * Created by mbacer on 23/04/14.
 */
public class Timbrum {

    private static final String LOGIN_URL = "/servlet/cp_login";
    private static final String TIMBRUS_URL = "/servlet/ushp_ftimbrus";
    private static final String SQL_DATA_PROVIDER_URL = "/servlet/SQLDataProviderServer";
    private static final String WORKSPACE_URL = "/jsp/gsmd_container.jsp?containerCode=MYDESK&pTitle=My%20Workspace";

    private final String username;
    private final String password;
    private final String host;

    private final OkHttpClient client = createClient();

    public Timbrum(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    private OkHttpClient createClient() {
        final OkHttpClient client;
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieJar cookieJar = new JavaNetCookieJar(cookieManager);

        client = new OkHttpClient.Builder()
                .followRedirects(false)
                .cookieJar(cookieJar)
                .build();
        return client;
    }

    public ArrayList<RecordTimbratura> getReport(Date date) throws Exception {
        ReportRequest report = new ReportRequest(client, host + SQL_DATA_PROVIDER_URL);
        return report.getTimbrature(new Date());

    }

    public LoginResult login() throws IOException {
        LoginRequest login = new LoginRequest(client, host + LOGIN_URL, username, password);
        return login.submit();
    }


    public void timbra(String verso, String timbraturaId) throws IOException {
        TimbraturaRequest timbratura = new TimbraturaRequest(client, host + TIMBRUS_URL);
        if (TimbraturaRequest.VERSO_ENTRATA.equals(verso)) {
            timbratura.entrata(timbraturaId);
        } else {
            timbratura.uscita(timbraturaId);
        }
    }

    public String loadTimbraturaId() throws RequestException {
        WorkspaceRequest workspaceRequest = new WorkspaceRequest(client, host + WORKSPACE_URL);
        return workspaceRequest.loadTimbraturaId();
    }


}
