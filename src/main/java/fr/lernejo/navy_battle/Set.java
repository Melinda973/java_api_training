package fr.lernejo.navy_battle;

import java.util.HashMap;
import java.util.Map;

public class Set {
    public int coloneinInt(char lettre) {
        int ascii = lettre;
        if(ascii > 64 && ascii <75) {
            return ascii - 65;
        }
        return -1;
    }
    public char coloneinChar(int nombre) {
        int ascii = nombre + 65;
        return (char) ascii;
    }
    public boolean saisieColonneValid(char lettre) {
        int colonne = this.coloneinInt(lettre);
        if( colonne == -1) {
            return false;
        }
        return true;
    }
    public boolean saisieLigneValid(int ligne) {
        if( ligne >= 0 && ligne < 10) {
            return true;
        }
        return false;
    }

    public Map<String, Integer> dissocierCase(String p_postion) {
        int collone = this.coloneinInt(p_postion.charAt(0));
        int ligne = Integer.parseInt(p_postion.substring(1,p_postion.length()));
        Map<String, Integer> dissocier = new HashMap<String, Integer>();
        dissocier.put("collone", collone);
        dissocier.put("ligne", ligne);
        return dissocier;
    }

    public Map<String, Integer> createPosition(int collonne, int ligne) {
        Map<String, Integer> position = new HashMap<>();
        position.put("ligne", ligne);
        position.put("collone", collonne);
        return position;
    }

    public String  createCase(Map<String, Integer> p_postion) {
        char collone = this.coloneinChar(p_postion.get("collone"));
        return  String.valueOf(collone) + p_postion.get("ligne") ;
    }
    public void  afficherMonPlateau(String p_message, int[][] p_cell) {
        System.out.println(p_message);
        System.out.println(" \t A \t B \t C \t D \t E \t F \t G \t H \t I \t J");
        for ( int ligne = 0; ligne < p_cell.length; ligne++) {
            System.out.print(ligne + "\t");
            for (int collone = 0; collone < p_cell[0].length ; collone ++) {
                if (p_cell[ligne][collone] == 0 ) System.out.print( "  \t ");
                if (p_cell[ligne][collone] == 1 ) System.out.print( "B \t ");
                if (p_cell[ligne][collone] == 2 ) System.out.print( "T \t ");
            }
            System.out.print("\n");
        }
    }
    public void  afficherOtherPlateau(String p_message, int[][] p_cell) {
        System.out.println(p_message);
        System.out.println("\t A \t B \t C \t D \t E \t F \t G \t H \t I \t J");
        for ( int ligne = 0; ligne < p_cell.length; ligne++) {
            System.out.print(ligne + "\t");
            for (int collone = 0; collone < p_cell[0].length ; collone ++) {
                if (p_cell[ligne][collone] == 0 ) System.out.print( "  \t ");
                if (p_cell[ligne][collone] == 1 ) System.out.print( ". \t ");
                if (p_cell[ligne][collone] == 2 ) System.out.print( "T \t ");
            }
            System.out.print("\n");
        }

    }
}
