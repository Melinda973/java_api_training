package fr.lernejo.navy_battle;

import java.util.HashMap;

public class Util {
    private final HashMap<String, String> data = new HashMap<String, String>();

    public void addData(String p_key, String p_value) {
        this.data.put(p_key, p_value);
    }

    public String getData(String p_key) {
        return this.data.get(p_key);
    }
    public void addOtherTour(String addr, String nb_tour) {
        this.data.put("adresseOtherServeur", addr);
        this.data.put("monTour", nb_tour);
    }
}
