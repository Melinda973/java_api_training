package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        if (args.length < 1) {
            System.err.println("Arguments are missing!");
        } else if (args.length == 1) {
            Server server = new Server(port);
            server.serverInit();
        } else {
            String addr = args[1];
            ClientPOST clientServer = new ClientPOST(port);
            clientServer.connexion(addr);
        }
    }
}
