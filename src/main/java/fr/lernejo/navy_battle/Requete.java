package fr.lernejo.navy_battle;

import java.util.HashMap;
import fr.lernejo.navy_battle.ClientPOST;

public class Requete {

    private final BuildMap json= new BuildMap();
    private final Boat boat = new Boat();

    public HashMap<String, Integer> fireOther(ClientPOST client_serveur,String addr, String case_) {
        HashMap<String, String> requette = client_serveur.fireOther(addr, case_);
        if(requette.get("status").equals("202") ) {
            HashMap<String, Integer> info = new HashMap<>();
            HashMap<String, String> requettebody = this.json.jsonToMap(requette.get("body"));
            info.put("consequence", boat.toucherCoulerRater(requettebody.get("consequence")));
            if(requettebody.get("shipLeft").equals("true")){ info.put("shipLeft", 0);
            }else { info.put("shipLeft", 1);}
            return info;
        }
        return null;
    }

}
