package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestPost = HttpRequest.newBuilder()
            .uri(URI.create(adrr + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}"))
            .build();
        CompletableFuture<HttpResponse<String>> cf = client_server.sendAsync(requestPost, HttpResponse.BodyHandlers.ofString());
        cf.thenApplyAsync(HttpResponse::headers).thenAcceptAsync(System.out::println);
        HttpResponse<String> response = cf.join();
        System.out.println("Response: " + response.statusCode() + " : " + response.body());
    }
}
