package audrey.chessapp.model;

import java.util.ArrayList;

public class Partie {

    private final Plateau plateau = new Plateau();
    private ArrayList<Deplacement> deplacements = new ArrayList<>();
    private joueurs joueurActuel;
    public enum joueurs {BLANC, NOIR};
    public boolean theEnd = false;


    public void newGame(){
        this.plateau.initBord();
        this.theEnd = false;
        joueurActuel = joueurs.BLANC;
    }


    public joueurs getJoueurActuel() {
        return joueurActuel;
    }

    public boolean isTheEnd() {
        return theEnd;
    }

    public Deplacement clickOnCase(String idPane){ // == createMove()

        int row = this.getRow(idPane);
        int column = this.getColumn(idPane);
        System.out.println("Joueur actuel : "+this.joueurActuel);
        //envoi au plateau la case cliquée
        Deplacement deplacement = this.plateau.actionOnCase(row,column, this.joueurActuel);
        if(deplacement != null){
            this.theEnd = this.plateau.isTheEnd();
            deplacements.add(deplacement);
            if(!this.theEnd){
                this.changeJoueur();
            }
        }
        return deplacement;
    }

    public boolean trySelected(String idPane){
        int row = this.getRow(idPane);
        int column = this.getColumn(idPane);
        Case caseS = this.plateau.getOneCase(row, column);
        if (caseS == null){
            return false;
        }

        //Si aucune case selectionnee + case possede pion meme joueur -> true
        if(this.plateau.getCaseSelected() == null && !caseS.isEmpty() && caseS.getPiece().getColor() == this.joueurActuel){
            return true;
        }
        //Tous les autres cas -> false
        return false;
    }
    public boolean hasCaseSelected(){
        return this.plateau.getCaseSelected() == null ? false : true;
    }

    private int getRow(String name){
        String[] nameSplit = name.split("");
        return Integer.parseInt(nameSplit[nameSplit.length-1]);
    }
    private int getColumn(String name){
        String[] nameSplit = name.split("");
        return Integer.parseInt(nameSplit[nameSplit.length-2]);

    }

    private void changeJoueur(){
        this.joueurActuel = this.joueurActuel == joueurs.BLANC ? joueurs.NOIR : joueurs.BLANC;
    }

    public ArrayList<Case> getPotentialMoves(){
        return this.plateau.getPotentialCases();
    }
}
