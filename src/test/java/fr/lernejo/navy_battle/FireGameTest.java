package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

class FireGameTest {

    @Test
    void handle() {
        try {
            Server server = new Server(9097);
            server.serverInit();
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest requestPUT = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9097/api/game/fire"))
                .header("Accept", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("ping!"))
                .build();
            int statusCodePUT = client.sendAsync(requestPUT, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
            Assertions.assertEquals(statusCodePUT, 404);

            HttpRequest requestGet = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire?cell=B2")).build();
            CompletableFuture<HttpResponse<String>> completableFutureGet = client.sendAsync(requestGet, HttpResponse.BodyHandlers.ofString());
            completableFutureGet.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> responseGET = completableFutureGet.join();
            Assertions.assertEquals(responseGET.statusCode(), 202);
            Assertions.assertEquals(responseGET.body(), "{\"consequence\":\"miss\",\"shipLeft\":false}");

            HttpRequest requestGetManque = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire")).build();
            int statusCodeGetmanque = client.sendAsync(requestGetManque, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
            Assertions.assertEquals(statusCodeGetmanque, 400);

            HttpRequest requestGetKO = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire?ici=B2")).build();
            int statusCodeGetKO = client.sendAsync(requestGetKO, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
            Assertions.assertEquals(statusCodeGetKO, 400);

        } catch (Exception e) {}
    }
}
