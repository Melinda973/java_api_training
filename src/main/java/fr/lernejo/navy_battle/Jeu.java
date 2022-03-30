package fr.lernejo.navy_battle;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Jeu {

    private final int TAILLE_PLATEAU = 10;
    private final int[][] monPlateau = new int[TAILLE_PLATEAU][TAILLE_PLATEAU];
    private final int[][] adversePlateau = new int[TAILLE_PLATEAU][TAILLE_PLATEAU];
    private final ArrayList<Nav> armada ;
    private final Set set= new Set();
    private final Set2 set2 = new Set2();
    private final SetPlayer setplayer = new SetPlayer();
    private final Game game = new Game();
    private final Ordi ordi = new Ordi();
    private final Boat boat = new Boat();
    private final Requete requete = new Requete();
    private final ClientPOST serveurclient;
    private final Util data;

    public Jeu(ClientPOST serveurclient,Util data) {
        this.serveurclient = serveurclient;
        this.data = data;
        this.armada = this.ordi.initialiserBateaux(this.monPlateau);
    }

    public void jouer() {
        System.out.println("Welcome... and good luck !");
        boolean continuer = true;
        while(continuer) {
            if(this.data.getData("My turn").equals("true")) {
                continuer = this.lancerAttaque();
                this.data.addData("My turn", "false");
                this.set.afficherMonPlateau("My set", monPlateau);
                this.set.afficherOtherPlateau("Your set", adversePlateau);
            }
        }
        System.out.println("The end...");

    }

    public boolean lancerAttaque() {
        String caseChoisi = ordi.choisirCase(this.TAILLE_PLATEAU);
        System.out.println(this.data.getData("monPort") + " j'attaque : " + caseChoisi);
        while (set2.checkDejaVisiter(adversePlateau, caseChoisi)) { caseChoisi = ordi.choisirCase(this.TAILLE_PLATEAU);}
        return getInfoAdverse(caseChoisi);
    }

    public boolean getInfoAdverse( String p_caseChoisi) {
        HashMap<String, Integer> info = this.requete.fireOther(this.serveurclient ,this.data.getData("adresseOtherServeur"), p_caseChoisi);
        this.modifierInfoAdverse(p_caseChoisi, info);
        return this.game.finJeux(info.get("shipLeft"));
    }

    public void modifierInfoAdverse (String p_caseChoisi, HashMap<String, Integer> p_info) {
        if(p_info.get("consequence") == 0) {adversePlateau[set.dissocierCase(p_caseChoisi).get("ligne")][set.dissocierCase(p_caseChoisi).get("collone")] = 1;
        }else {adversePlateau[set.dissocierCase(p_caseChoisi).get("ligne")][set.dissocierCase(p_caseChoisi).get("collone")] = 2;}
    }

    public JSONObject subirAttaque() {
        JSONObject obj = new JSONObject();
        for (Nav navire : this.armada) {
            boolean toucher = this.boat.toucher(navire, this.data.getData("caseAdverseVisit"));
            if(toucher) { return this.attaqueToucher(navire);}
        }
        this.game.addConsequenceShipLeft(obj , "miss", true );
        return obj;
    }

    public ArrayList<Nav> getArmade(){
        return this.armada;
    }

    public JSONObject attaqueToucher(Nav p_navire) {
        JSONObject obj = new JSONObject();
        this.setplayer.toucherAdverse(this.data.getData("caseAdverseVisit"),this.monPlateau);
        boolean couler = p_navire.checkCouler();
        boolean allCouler = boat.checkCouler(this.armada);
        if(couler) { this.game.addConsequenceShipLeft(obj , "sunk", !allCouler );
        }else { this.game.addConsequenceShipLeft(obj , "hit", !allCouler );}
        return obj;
    }
}
