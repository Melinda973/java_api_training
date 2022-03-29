package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import java.net.http.HttpRequest.BodyPublishers;
import java.util.concurrent.CompletableFuture;

public class ClientPOST {

    private final int port;
    private final HttpClient client_server;

    public ClientPOST(int port) {
        this.port = port;
        this.client_server = HttpClient.newHttpClient();
    }

    public void connexion(String adrr) {
        System.out.println("Connexion to a second waiting server...");
        HttpRequest request = requestPost(adrr);
        HashMap<String, String> reponse = respRequest(request);
    }

    public HttpRequest requestPost(String adrr) {
        return HttpRequest.newBuilder()
            .uri(URI.create(adrr + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}"))
            .build();
    }

    public HashMap<String, String> respRequest(HttpRequest req){
        CompletableFuture<HttpResponse<String>> completableFuture = client_server.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        completableFuture.thenApplyAsync(HttpResponse::headers);
        HttpResponse<String> response = completableFuture.join();
        HashMap<String, String> reponseInfo = new HashMap<>();
        reponseInfo.put("status", Integer.toString(response.statusCode()));
        reponseInfo.put("body", response.body());
        return reponseInfo;
    }
}
