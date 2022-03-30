package fr.lernejo.navy_battle;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;

public class Ordi {
    private final Set set = new Set();
    private final SetPlayer setplayer = new SetPlayer();

    public String choisirCase(int max) {
        SecureRandom random = new SecureRandom();
        int ligne = random.nextInt(max);
        int colonne = random.nextInt(max);
        char colonneC = this.set.coloneinChar(colonne);
        return String.valueOf(colonneC)+ligne;
    }

    public ArrayList<Nav> initialiserBateaux(int[][] p_cell) {
        ArrayList<Nav> listeBateau = new ArrayList<Nav>();
        listeBateau.add(this.createPorteAvion(p_cell));
        listeBateau.add(this.createCroiseur(p_cell));
        listeBateau.add(this.createTorpilleur(p_cell));
        listeBateau.add(this.createContreTorpilleur(p_cell));
        listeBateau.add(this.createContreTorpilleur(p_cell));
        return listeBateau;
    }

    public Nav createPorteAvion( int[][] p_cell) {
        return createNavire(p_cell, 5);
    }
    public Nav createCroiseur( int[][] p_cell) {
        return createNavire(p_cell, 4);
    }
    public Nav createTorpilleur( int[][] p_cell) {
        return createNavire(p_cell, 3);
    }
    public Nav createContreTorpilleur( int[][] p_cell) {
        return createNavire(p_cell, 2);
    }
    public Nav createNavire( int[][] p_cell, int taille) {
        boolean verticale = this.choisirSens();
        int sens = this.caseSens();
        int autreSens = this.autreCase(taille);
        while ( ! this.setplayer.verifCaseBataeu(taille, sens, autreSens, verticale, p_cell)) {
            sens = this.caseSens();
            autreSens = this.autreCase(taille);
        }
        Map<String, Integer> position ;
        if(verticale) {position = this.set.createPosition(sens, autreSens);}
        else {position = this.set.createPosition(autreSens, sens);}
        this.setplayer.placerBateau(taille, verticale, position, p_cell);
        return new Nav(taille, position, verticale);
    }

    public boolean choisirSens() {
        SecureRandom random = new SecureRandom();
        int ligne = random.nextInt(100);
        if (ligne < 49) {
            return true;
        }
        return false;
    }
    public int caseSens() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(10);
    }
    public int autreCase(int p_tailleBateau) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(10 - p_tailleBateau );
    }
}
