package me.tiagobento;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FineService {

    private static final String FINE_SERVICE_URL = "traffic-violation-service-1-demo-test-tiago-1.apps-crc.testing";

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @SuppressWarnings("unchecked")
    public static Map<String, Object> calculate(Map<String, Object> violation) throws Exception {

        var objMapper = new ObjectMapper();

        var request = HttpRequest.newBuilder() //
                .uri(URI.create("http://" + FINE_SERVICE_URL + "/Traffic%20Violation/Fine")) //
                .header("Content-Type", "application/json") //
                .POST(BodyPublishers.ofString(objMapper.writeValueAsString(Map.of("Violation", violation)))) //
                .build();

        var response = HTTP_CLIENT.send(request, BodyHandlers.ofString());

        return objMapper.readValue(response.body(), HashMap.class);
    }
}
