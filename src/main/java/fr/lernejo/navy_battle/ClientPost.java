package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ClientPost {
    private final int port;
    private final HttpClient serveurC;

    public ClientPost(int p_port) {
        this.port = p_port;
        this.serveurC = HttpClient.newHttpClient();
    }

    public void connectOther(String p_otherAdress) {
        System.out.println("Second server connexion");

        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(p_otherAdress + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"hello\"}"))
            .build();
        CompletableFuture<HttpResponse<String>> completableFuture =
            this.serveurC.sendAsync(requetePost, HttpResponse.BodyHandlers.ofString());
        completableFuture
            .thenApplyAsync(HttpResponse::headers)
            .thenAcceptAsync(System.out::println);
        HttpResponse<String> response = completableFuture.join();
        int status = response.statusCode();
        String body = response.body();
        System.out.print("body : " + body + "status : " + status );

    }
}
