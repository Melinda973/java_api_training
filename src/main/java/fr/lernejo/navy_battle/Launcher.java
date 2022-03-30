package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Arguments are missing !");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        server.serverInit();
    }
}
