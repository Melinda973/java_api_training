package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Arguments are missing!");
            return;
        } else if (args.length == 1) {
            int port = Integer.parseInt(args[0]);
            Server server = new Server(port);
            server.serverInit();
        } else {
            int port = Integer.parseInt(args[0]);
            String adresseOtherServeur = args[1];
            Server server = new Server(port);
            server.serverInit();
            ClientPOST clientServer = new ClientPOST(port);
            clientServer.connexion(adresseOtherServeur);
        }
    }
}
