package it.buch85.request;

import java.io.IOException;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WorkspaceRequest extends AbstractRequest {

    public WorkspaceRequest(OkHttpClient client) {
        super(client);
    }

    public String submit() throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        return getTimbraturaId(response.body().string());
    }


    public static String getTimbraturaId(String body) {
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

    }

}
