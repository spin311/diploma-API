package com.example.controller;

import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/openai")
public class OpenAiController {



    @CrossOrigin(origins = "*")
    @PostMapping("/getOpenAiChat")
    public String getOpenAiChat(@RequestBody String chatInput) {

        String apiKey = System.getenv("OPENAI_API_KEY");
        try{
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer chat-key-1234");
            conn.setDoOutput(true);
            String jsonInputString = "{"
                    + "\"model\":\"gpt-3.5-turbo-0125\","
                    + "\"messages\":[{\"role\":\"user\",\"content\":\"" + chatInput.trim() + "\"}],"
                    + "\"max_tokens\":150"
                    + "}";
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int code = conn.getResponseCode();
            return conn.getRes
        }
        catch(Exception e){
            e.printStackTrace();
            return "Failed to get chat from OpenAI";
        }


        return "Hello OpenAI!";
    }
}
