package fr.lernejo.navy_battle;

import java.util.ArrayList;

public class Boat {

        private final Set set = new Set();

        public boolean checkCouler(ArrayList<Nav> ok ) {
            for (Nav navire : ok) {
                if(!navire.checkCouler()) {
                    return false;
                }
            }
            return true;
        }

        public boolean toucher(Nav boat, String case_) {
            int colonne = this.set.coloneinInt(case_.charAt(0));
            int ligne = Integer.parseInt(case_.substring(1,case_.length()));
            if(boat.getVertical() && colonne == boat.getPostion().get("colonne")) {
                return this.verifSize(ligne, boat.getPostion().get("ligne"), boat );
            }else if( !boat.getVertical() && ligne == boat.getPostion().get("ligne")) {
                return this.verifSize(colonne, boat.getPostion().get("colonne"), boat);
            }
            return false;
        }

        public boolean verifSize(int place,int start ,Nav boat) {
            if( (start + boat.getTaille()) > place && place >= start) {
                boat.toucherBateau(start, place);
                return true;
            }
            return false;
        }


        public int toucherCoulerRater(String info) {
            switch (info) {
                case "miss": return 0;
                case "hit": return 1;
                case "sunk": return 2;
            }
            return -1;
        }
}
