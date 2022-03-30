package fr.lernejo.navy_battle;

import org.json.JSONObject;

import fr.lernejo.navy_battle.Util;
import fr.lernejo.navy_battle.ClientPOST;

public class Game{

    public boolean finJeux( Integer fin) {
        if(fin == 0 ) {return false;}
        return true;
    }

    public void connexion(String p_adresse, String nb_tour, ClientPOST client_server, Util data) {
        data.addOtherTour(p_adresse, nb_tour );
        client_server.connect();
    }

    public void addConsequenceShipLeft(JSONObject object , String consequence, Boolean shipLeft) {
        object.put("consequence", consequence);
        object.put("shipLeft", shipLeft);
    }
}
