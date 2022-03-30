package fr.lernejo.navy_battle;

import java.util.Map;

public class Set2 extends Set{

    public boolean checkDejaVisiter(int[][] plateau, String visit) {
        Map<String, Integer> dissocier = this.dissocierCase(visit);
        if (plateau[dissocier.get("ligne")][dissocier.get("collone")] == 1 || plateau[dissocier.get("ligne")][dissocier.get("collone")] == 2) {
            return true;
        }
        return false;
    }

}
