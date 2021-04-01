package audrey.chessapp.model;

import java.util.ArrayList;

public class Partie {

    private final Plateau plateau = new Plateau();
    private ArrayList<Deplacement> deplacements = new ArrayList<>();
    private joueurs joueurActuel;
    public enum joueurs {BLANC, NOIR};

    public void newGame(){
        this.plateau.initBord();
        joueurActuel = joueurs.BLANC;
    }

    public void createMove(){

    }

    public void cancelMove(){

    }

    public joueurs getJoueurActuel() {
        return joueurActuel;
    }

    public Plateau getPlateau() {
        return plateau;
    }

}
