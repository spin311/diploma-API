package com.example.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class OpenAiService {

    public String getOpenAiChat(String chatInput) {

        try{
            HttpURLConnection conn = getHttpConnectionForOpenAi(chatInput);

            // Get response code
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // Read response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }

        }
        catch(Exception e){
            e.printStackTrace();
            return "Failed to get chat from OpenAI";
        }
    }

    private static HttpURLConnection getHttpConnectionForOpenAi(String chatInput) throws IOException {
        String apiKey = System.getenv("OPENAI_API_KEY");
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + apiKey);
        conn.setDoOutput(true);
        String jsonInputString = "{"
                + "\"model\":\"gpt-3.5-turbo-0125\","
                + "\"messages\":[{\"role\":\"user\",\"content\":\"" + chatInput.trim() + "\"}],"
                + "\"max_tokens\":150"
                + "}";
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return conn;
    }
}
