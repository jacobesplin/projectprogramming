package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

public class API {
    public String pullData(String api) {
        try {
            URL url = new URL(api);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Mobile Safari/537.36");
            InputStream responseStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(responseStream);
            Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
            String streamToString = streamOfString.collect(Collectors.joining());
            return streamToString;

        } catch (IOException e) {

        }
        return "";
    }

    public String postData(String urlApi, String json) {
        try {
            URL url = new URL(urlApi);
            // URLConnection con = url.openConnection();
            HttpsURLConnection http;
            try {
                http = (HttpsURLConnection) url.openConnection();
                http.setRequestMethod("POST"); // PUT is another valid option
                http.setDoOutput(true);
                String buildJson = json;

                byte[] out = buildJson.getBytes(StandardCharsets.UTF_8);
                int length = out.length;

                http.setFixedLengthStreamingMode(length);
                http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                // http.setRequestMethod("GET");
                // http.setRequestProperty("accept", "application/json");
                http.connect();

                try (java.io.OutputStream os = http.getOutputStream()) {
                    os.write(out);
                }
                InputStream responseStream;
                responseStream = http.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(responseStream);
                Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
                String streamToString = streamOfString.collect(Collectors.joining());
                return streamToString;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return "{\"info\":\"Trouble calling API at this time\"}";
    }

}
