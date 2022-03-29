package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {

    public static void main(String args[]){
        int port = Integer.parseInt(args[0]);
        if (args.length > 0 ) {
            try{
                Server server = new Server(port);
                server.serverInit();
            }catch (Exception e) {}
        }
        if (args.length > 1 ) {
            try{
                String addr = args[1];
                ClientPOST client_server = new ClientPOST(port);
                client_server.connexion(addr);
            }catch (Exception e) {}
        }
    }
}
