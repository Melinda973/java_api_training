package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void serverInit() throws IOException {
        System.out.println("Server initialization");
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);
        server.setExecutor(Executors.newFixedThreadPool(1)); // creates an executor of 1 thread
        server.createContext("/ping", new PingHandler());
        server.createContext("/api/game/start", new CasePOST());
        server.createContext("/api/game/fire", new FireGame());
        server.start();
    }
}
