package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    private static final String API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String word = request.getParameter("word");
        String definition = fetchDefinitionFromApi(word);

        // Debug
        System.out.println("Word: " + word);
        System.out.println("Definition: " + definition);

        request.setAttribute("word", word);
        request.setAttribute("definition", definition);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    private String fetchDefinitionFromApi(String word) {
        StringBuilder definition = new StringBuilder();
        try {
            URL url = new URL(API_URL + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JsonElement jsonElement = JsonParser.parseString(response.toString());

                if (jsonElement.isJsonArray() && jsonElement.getAsJsonArray().size() > 0) {
                    JsonObject jsonObject = jsonElement.getAsJsonArray().get(0).getAsJsonObject();

                    JsonElement meaningElement = jsonObject.get("meanings");

                    if (meaningElement != null && meaningElement.isJsonArray()) {
                        for (JsonElement meaning : meaningElement.getAsJsonArray()) {
                            JsonObject meaningObject = meaning.getAsJsonObject();

                            JsonElement partOfSpeechElement = meaningObject.get("partOfSpeech");
                            if (partOfSpeechElement != null && !partOfSpeechElement.isJsonNull()) {
                                String partOfSpeech = partOfSpeechElement.getAsString();
                                definition.append("<b>Part of Speech:</b> ").append(partOfSpeech).append("<br>");
                            }

                            JsonElement definitionElement = meaningObject.get("definitions");
                            if (definitionElement != null && definitionElement.isJsonArray()
                                    && definitionElement.getAsJsonArray().size() > 0) {

                                JsonElement firstDefinition = definitionElement.getAsJsonArray().get(0);
                                String definitionText = firstDefinition.getAsJsonObject().get("definition")
                                        .getAsString();
                                definition.append("<b>Definition:</b> ").append(definitionText).append("<br>");
                            }

                            JsonElement synonymsElement = meaningObject.get("synonyms");
                            if (synonymsElement != null && synonymsElement.isJsonArray()
                                    && synonymsElement.getAsJsonArray().size() > 0) {
                                definition.append("<b>Synonyms:</b> ");
                                for (JsonElement synonym : synonymsElement.getAsJsonArray()) {
                                    definition.append(synonym.getAsString()).append(", ");
                                }
                                definition.append("<br>");
                            }

                            JsonElement antonymsElement = meaningObject.get("antonyms");
                            if (antonymsElement != null && antonymsElement.isJsonArray()
                                    && antonymsElement.getAsJsonArray().size() > 0) {
                                definition.append("<b>Antonyms:</b> ");
                                for (JsonElement antonym : antonymsElement.getAsJsonArray()) {
                                    definition.append(antonym.getAsString()).append(", ");
                                }
                                definition.append("<br>");
                            }
                        }
                    }
                }
            } else {
                System.out.println("API returned error code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (definition.length() == 0) {
            return "Không tìm thấy định nghĩa cho từ này.";
        }

        return definition.toString();
    }
}
