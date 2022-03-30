package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length > 1 ) {
            try{
                int port = Integer.parseInt(args[0]);
                String adresseOtherServeur = args[1];
                Server server = new Server(port);
                server.serverInit();
                ClientPost serveurClient = new ClientPost(port);
                serveurClient.connectOther(adresseOtherServeur);

            }catch (Exception e) {}
        }else if (args.length > 0 ) {
            try{
                int port = Integer.parseInt(args[0]);
                Server server = new Server(port);
                server.serverInit();
            }catch (Exception e) {}
        }
    }
}

