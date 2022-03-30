package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class FireGameTest {

    @org.junit.jupiter.api.Test
    void FireGameGood() throws IOException, InterruptedException {
        Server server= new Server(5002);
        server.serverInit();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:5002/api/game/fire?cell=F2"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .GET()
            .build();
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        int expected = HttpURLConnection.HTTP_OK;
        int result = resp.statusCode();
        Assertions.assertThat(result).as("Response should be 200 OK").isEqualTo(expected);
    }

    @org.junit.jupiter.api.Test
    void FireGameFails() throws IOException, InterruptedException {
        Server server = new Server(5003);
        server.serverInit();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:5003/api/game/fire"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .GET()
            .build();
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        int expected = HttpURLConnection.HTTP_BAD_REQUEST;
        int result = resp.statusCode();
        Assertions.assertThat(result).as("Response should be 400 Bad Request").isEqualTo(expected);
    }

    @org.junit.jupiter.api.Test
    void FireGameFails2() throws IOException, InterruptedException {
        Server server = new Server(5004);
        server.serverInit();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:5004/api/game/fire?cell"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .GET()
            .build();
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        int expected = HttpURLConnection.HTTP_BAD_REQUEST;
        int result = resp.statusCode();
        Assertions.assertThat(result).as("Response should be 400 Bad Request").isEqualTo(expected);
    }

    @org.junit.jupiter.api.Test
    void BadFireGame() throws IOException, InterruptedException {
        Server server = new Server(5005);
        server.serverInit();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:5005/api/game/fire?cell=F2"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .DELETE()
            .build();
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        int expected = HttpURLConnection.HTTP_NOT_FOUND;
        int result = resp.statusCode();
        Assertions.assertThat(result).as("Response should be 404 Not Found").isEqualTo(expected);
    }
}
