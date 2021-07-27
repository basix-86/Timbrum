package it.buch85.request;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class McIDRequest {

    private OkHttpClient client;

    public McIDRequest(OkHttpClient client) {
        this.client = client;
    }

    protected String url = "https://saas.hrzucchetti.it/hrpergon/jsp/gsmd_container.jsp?containerCode=MYDESK&pTitle=My%20Workspace";

    public String submit() throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        return getMCid(response.body().string());
    }


    public static String getMCid(String body) {
        Scanner scanner = new Scanner(body);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains("splinker10.m_cID")) {
                String[] split = line.split("splinker10.m_cID='");

                return split[1].replace("';", "");
            }
            // process the line
        }
        return "";

//        reader.
//        int firstChar = body.indexOf("splinker10.m_cID='") + 1;
//        body = body.substring(firstChar);
//        return body.substring(0, body.indexOf("';"));
    }

}
