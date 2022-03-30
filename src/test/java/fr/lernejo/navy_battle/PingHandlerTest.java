package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PingHandlerTest {
    @Test
    void handle() {
        try {
            Util data = new Util();
            data.addData("monPort", "9095");

            ClientPOST clientServeur = new ClientPOST(data);
            Jeu jeu = new Jeu(clientServeur, data);
            Server server = new Server(data, jeu);

            server.serverInit();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9095/ping"))
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("ping!"))
                .build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(),200);
            Assertions.assertEquals(response.body(),"OK");
        }catch(Exception e) {}
    }
}
