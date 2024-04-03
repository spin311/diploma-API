package com.example.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OpenAiService {

    public String getOpenAiChat(String chatInput) {

        try{
            HttpURLConnection conn = getHttpConnectionForOpenAi(chatInput);

            // Read response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return extractMessageFromJSONResponse(response.toString());
            }

        }
        catch(Exception e){
            e.printStackTrace();
            return "Failed to get chat from OpenAI";
        }
    }

    private static HttpURLConnection getHttpConnectionForOpenAi(String chatInput) throws IOException {
        String apiKey = getApiKey();
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + apiKey);
        conn.setDoOutput(true);
        String jsonInputString = "{"
                + "\"model\":\"gpt-3.5-turbo-0125\","
                + "\"messages\":[{\"role\":\"user\",\"content\":\"" + chatInput + "\"}],"
                + "\"max_tokens\":150"
                + "}";
        try(OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
            writer.write(jsonInputString);
            writer.flush();
        }
        return conn;
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }

    private static String getApiKey() {
        try {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("OPENAI_API_KEY");

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
