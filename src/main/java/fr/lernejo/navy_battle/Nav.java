package fr.lernejo.navy_battle;

import java.util.Arrays;
import java.util.Map;

public class Nav {

    private final int taille;
    private final Map<String, Integer> postion;
    private final boolean vertical;
    private final boolean[] caseBateau;

    public Nav( int p_taille , Map<String, Integer> p_postion, boolean p_vertical) {
        this.taille =p_taille;
        this.postion = p_postion;
        this.vertical = p_vertical;
        this.caseBateau = new boolean[this.taille];
        Arrays.fill(caseBateau, Boolean.FALSE);
    }
    public int getTaille() {
        return this.taille;
    }
    public boolean getVertical() {
        return this.vertical;
    }
    public Map<String, Integer> getPostion() {
        return this.postion;
    }
    public boolean[] getCaseBateau() {
        return this.caseBateau;
    }
    public void toucherBateau( int p_position, int p_saisie) {
        this.caseBateau[p_saisie - p_position] = true;
    }

    public boolean checkCouler() {
        for (boolean place : this.caseBateau) {
            if(!place) {
                return false;
            }
        }
        return true;
    }
}
