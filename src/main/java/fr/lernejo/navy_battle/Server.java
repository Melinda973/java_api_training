package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private final Util data;
    private final Jeu jeu;

    public Server(Util data, Jeu jeu) {
        this.data = data;
        this.jeu = jeu;
    }

    public void serverInit() throws IOException {
        System.out.println("Initialisation de mon serveur sur le port ; " + this.data.getData("monPort"));
        InetSocketAddress addrToBind = new InetSocketAddress(Integer.parseInt(this.data.getData("monPort")));
        try {
            HttpServer httpSrv = HttpServer.create(addrToBind, 0);
            httpSrv.setExecutor(Executors.newFixedThreadPool(1));
            httpSrv.createContext("/ping", new PingHandler());
            httpSrv.createContext("/api/game/start", new CasePOST(this.data));
            httpSrv.createContext("/api/game/fire", new FireGame(this.data, this.jeu));
            httpSrv.start();
        }catch (Exception e) {}

    }
}
