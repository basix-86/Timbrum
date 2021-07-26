package it.buch85;

import android.content.Context;

import it.buch85.request.LoginRequest;
import it.buch85.request.LoginRequest.LoginResult;
import it.buch85.request.LoginRequest2;
import it.buch85.request.RecordTimbratura;
import it.buch85.request.ReportRequest;
import it.buch85.request.TimbraturaRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;


/**
 * Created by mbacer on 23/04/14.
 */
public class Timbrum {


    public static String LOGIN_URL             = "/servlet/cp_login";
    public static String TIMBRUS_URL           = "/servlet/ushp_ftimbrus";
    public static String SQL_DATA_PROVIDER_URL = "/servlet/SQLDataProviderServer";

    private final String              username;
    private final String              password;
    private Context context2;
    //private BasicHttpContext context;
	private String host;

    public Timbrum(String host, String username, String password, Context context2) {
        this.host = host;
		this.username = username;
        this.password = password;
        this.context2 = context2;

//        context=new BasicHttpContext();
//        BasicCookieStore cookieStore=new BasicCookieStore();
//        context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
    }

    public ArrayList<RecordTimbratura> getReport(Date date) throws Exception {
//        ReportRequest report = new ReportRequest( new DefaultHttpClient(),context);
//        report.setUrl(host+SQL_DATA_PROVIDER_URL);
//        return report.getTimbrature(new Date());

        return new ArrayList<>();
    }

    public LoginResult login() throws IOException {
//        LoginRequest login = new LoginRequest( new DefaultHttpClient(),context);
//        login.setUrl(host+LOGIN_URL);
//        login.setUsername(username);
//        login.setPassword(password);
//        return login.submit();

        LoginRequest2 login = new LoginRequest2( context2);
        login.setUrl(host+LOGIN_URL);
        login.setUsername(username);
        login.setPassword(password);
        login.submit2();
        return null;
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
