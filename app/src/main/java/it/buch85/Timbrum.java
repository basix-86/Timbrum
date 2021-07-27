package it.buch85;

import android.content.Context;

import it.buch85.request.LoginResult;
import it.buch85.request.LoginRequest;
import it.buch85.request.RecordTimbratura;

import java.io.IOException;
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
        LoginRequest login = new LoginRequest();
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
