package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {

    public static void main(String args[]){
        Util data = new Util();
        Game game = new Game();
        ClientPOST client_server = new ClientPOST(data);
        Jeu jeu = new Jeu(client_server, data);
        Server server = new Server(data, jeu);

        if (args.length > 0 ) {data.addData("monPort", args[0]);
            try{
                server.serverInit();
                if (args.length > 1 ) {game.connexion(args[1], "false", client_server, data);}
                else {
                    while(data.getData("connectionEffectuer") == null) {}
                    game.connexion("http://localhost:"+ data.getData("otherPort"), "true", client_server, data);
                }
                //jeu.jouer();
                //La classe jeu n'est pas terminée
            }catch (Exception e) {}
        }
    }
}

