
package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import javax.servlet.ServletException;
// import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {

    private static final String API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"; // give the api url

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String word = request.getParameter("word");
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

                // parse gson String element
                JsonElement jsonElement = JsonParser.parseString(response.toString());

                if (jsonElement.isJsonArray() && jsonElement.getAsJsonArray().size() > 0) {

                    JsonObject jsonObject = jsonElement.getAsJsonArray().get(0).getAsJsonObject();

                    // extract meaning and detail
                    JsonElement meaningElement = jsonObject.get("meanings");

                    if (meaningElement != null && meaningElement.isJsonArray()
                            && meaningElement.getAsJsonArray().size() > 0) {
                        // loop through meaning array
                        for (JsonElement meaning : meaningElement.getAsJsonArray()) {
                            JsonObject meaningObject = meaning.getAsJsonObject();

                            // extract part of speech
                            JsonElement partOfSpeechElement = meaningObject.get("partOfSpeech");
                            if (partOfSpeechElement != null && partOfSpeechElement.isJsonNull()) {
                                String partOfSpeech = partOfSpeechElement.getAsString();
                                definition.append("<b>partOfSpeech:</b> ").append(partOfSpeech).append("<br>");
                            }

                            // extract definition
                            JsonElement definitionElement = meaningObject.get("definitions");
                            if (definitionElement != null && definitionElement.isJsonArray()
                                    && definitionElement.getAsJsonArray().size() > 0) {

                                JsonElement firstDefinition = definitionElement.getAsJsonArray().get(0);
                                String definitionText = firstDefinition.getAsJsonObject().get("definition")
                                        .getAsString();
                                definition.append("<b>Definition:</b> ").append(definitionText).append("<br>");
                            }

                        }
                    }
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return word;
    }

}
