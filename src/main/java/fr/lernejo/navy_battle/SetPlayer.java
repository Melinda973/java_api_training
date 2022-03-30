package fr.lernejo.navy_battle;

import java.util.Map;

public class SetPlayer extends Set{


    public boolean verifCaseBataeu(int p_taille, int caseSens, int autreCase, boolean p_vertical, int[][] p_cell) {
        if (p_vertical) {
            for ( int i = autreCase ; i < autreCase + p_taille; i++) {
                if (p_cell[i][caseSens] != 0) {
                    return false;
                }
            }
        }else {
            for ( int i = autreCase ; i < autreCase + p_taille; i++) {
                if (p_cell[caseSens][i] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placerBateau(int p_taille, boolean p_vertical, Map<String, Integer> p_postion , int[][] p_cell) {
        if (p_vertical) {
            this.placerBateauVerticale(p_taille, p_postion, p_cell);
        }else {
            this.placerBateauHorizontale(p_taille, p_postion, p_cell);
        }
    }
    public void placerBateauVerticale(int p_taille ,Map<String, Integer> p_postion , int[][] p_cell) {
        int i  = p_postion.get("ligne");
        while (p_taille != 0 ) {
            p_cell[i][p_postion.get("collone")] = 1;
            p_taille --;
            i++;
        }
    }
    public void placerBateauHorizontale(int p_taille ,Map<String, Integer> p_postion , int[][] p_cell) {
        int i  = p_postion.get("collone");
        while (p_taille != 0 ) {
            p_cell[p_postion.get("ligne")][i] = 1;
            p_taille --;
            i++;
        }
    }

    public void toucherAdverse(String p_casetoucher,  int[][] p_cell) {
        Map<String, Integer> caseVisiter =  this.dissocierCase(p_casetoucher);
        p_cell[caseVisiter.get("ligne")][caseVisiter.get("collone")] = 2;

    }
}
