package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

class FireGameTest {

    @Test
    void handle() {
        try {
            Util data = new Util();
            data.addData("monPort", "9097");
                ClientPOST clientServeur = new ClientPOST(data);
            Jeu jeu = new Jeu(clientServeur, data);
            Server server = new Server(data, jeu);
            server.serverInit();
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest requeteGet = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire?cell=B2")).build();
            CompletableFuture<HttpResponse<String>> completableFutureGet = client.sendAsync(requeteGet, HttpResponse.BodyHandlers.ofString());
            completableFutureGet.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> responseGET = completableFutureGet.join();
            org.junit.jupiter.api.Assertions.assertEquals(responseGET.statusCode(), 202);
            org.junit.jupiter.api.Assertions.assertEquals(data.getData("caseAdverseVisit"), "B2");

            HttpRequest requestPUT = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9097/api/game/fire"))
                .header("Accept", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("ping!"))
                .build();
            int statusCodePUT = client.sendAsync(requestPUT, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
            org.junit.jupiter.api.Assertions.assertEquals(statusCodePUT, 404);

            HttpRequest requeteGetErr = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire?cell=BI2")).build();
            CompletableFuture<HttpResponse<String>> completableFutureGetErr = client.sendAsync(requeteGetErr, HttpResponse.BodyHandlers.ofString());
            completableFutureGetErr.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> responseGETErr = completableFutureGetErr.join();
            org.junit.jupiter.api.Assertions.assertEquals(responseGETErr.statusCode(), 400);


            HttpRequest requestGetManque = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire")).build();
            int statusCodeGetmanque = client.sendAsync(requestGetManque, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
            org.junit.jupiter.api.Assertions.assertEquals(statusCodeGetmanque, 400);


            HttpRequest requestGetKO = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire?ici=B2")).build();
            int statusCodeGetKO = client.sendAsync(requestGetKO, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
            Assertions.assertEquals(statusCodeGetKO, 400);
        } catch (Exception e) {}
    }
}
