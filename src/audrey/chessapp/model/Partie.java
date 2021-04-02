package audrey.chessapp.model;

import audrey.chessapp.model.pieces.Pion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Partie {

    private final Plateau plateau = new Plateau();
    private ArrayList<Deplacement> deplacements = new ArrayList<>();
    private joueurs joueurActuel;
    public enum joueurs {BLANC, NOIR};
    public boolean theEnd = false;

    /**
     * Creation d'une nouvelle partie avec instanciation du plateau (et de toutes les cases et pieces de ce plateau)
     * instanciation du premier joueur -> BLANC
     */
    public void newGame(){
        this.plateau.initBoard();
        this.deplacements = new ArrayList<>();
        this.theEnd = false;
        this.joueurActuel = joueurs.BLANC;
    }

    /**
     *
     * @param idPane String sous la forme "paneCase"+Column+Row : example -> paneCase25
     *          with Column && Row = coordonnées de la case dernièrement cliquée
     * @return Déplacement
     * @throws NullPointerException :  Renvoie l'exception si pas de déplacement possible. Ne signifie pas forcément une erreur -> Nouvelle sélection/déselection de case
     */
    public Deplacement createMove(String idPane) throws NullPointerException{

        int row = this.getRow(idPane);
        int column = this.getColumn(idPane);
        System.out.println("Joueur actuel : "+this.joueurActuel);
        //envoi au plateau la case cliquée
        Deplacement deplacement = null;
        try{
            deplacement = this.plateau.actionOnCase(row,column, this.joueurActuel);
            this.theEnd = this.plateau.isTheEnd();
            deplacements.add(deplacement);
            if(!this.theEnd){ this.changeJoueur();} //Changement du joueur uniquement si la partie n'est pas finie.
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            throw new NullPointerException("Pas de déplacement.");
        }

        return deplacement;
    }

    /**
     * Verification de la possibilité de selection ou non
     * @param idPane String sous la forme "paneCase"+Column+Row : example -> paneCase25
     *      *          with Column && Row = coordonnées de la case dernièrement cliquée
     * @return  true -> si aucune case déjà sélectionnée et si la case possède un pion du joueur actuel
     *          false -> tous les autres cas
     */
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

    /**
     * Recherche de la ligne correspondante suivant le nom de la case
     * @param name String sous la forme "paneCase"+Column+Row : example -> paneCase25
     * @return int Coordonnée y de la case
     */
    private int getRow(String name){
        String[] nameSplit = name.split("");
        return Integer.parseInt(nameSplit[nameSplit.length-1]);
    }

    /**
     * Recherche de la colonne correspondante suivant le nom de la case
     * @param name String sous la forme "paneCase"+Column+Row : example -> paneCase25
     * @return int Coordonnée x de la case
     */
    private int getColumn(String name){
        String[] nameSplit = name.split("");
        return Integer.parseInt(nameSplit[nameSplit.length-2]);

    }

    /**
     * Modification du joueur actuel
     */
    private void changeJoueur(){
        this.joueurActuel = this.joueurActuel == joueurs.BLANC ? joueurs.NOIR : joueurs.BLANC;
    }

    /**
     * @return les cases selectionnables par le joueur
     */
    public ArrayList<Case> getPotentialMoves(){
        return this.plateau.getPotentialCases();
    }

    /**
     * @return le joueur actuel
     */
    public joueurs getJoueurActuel() {
        return joueurActuel;
    }

    /**
     * @return boolean - si c'est la fin de partie ou non
     */
    public boolean isTheEnd() {
        return theEnd;
    }
    /**
     * @return boolean - si une case est déjà selectionnée
     */
    public boolean hasCaseSelected(){
        return this.plateau.getCaseSelected() == null ? false : true;
    }

    public Deplacement cancelLastMove(){
        if(deplacements.size() == 0 || this.theEnd)
            return null;

        Deplacement lastDeplacement = deplacements.get(deplacements.size()-1);
        Case caseDepart = lastDeplacement.getCaseDepart();
        Case caseFinal = lastDeplacement.getCaseFinal();
        Piece pieceJouee = lastDeplacement.getPieceDeplacee();
        Piece pieceMangee = lastDeplacement.getPieceMangee();

        if(lastDeplacement.isPromotion()){
            caseDepart.setPiece(lastDeplacement.getPieceDeplaceeNonPromu());
        }
        else{
            caseDepart.setPiece(caseFinal.getPiece());
        }
        caseDepart.setEmpty(false);
        caseFinal.setPiece(pieceMangee);
        if(pieceMangee == null)
            caseFinal.setEmpty(true);
        else
            caseFinal.setEmpty(false);

        changeJoueur();
        deplacements.remove(lastDeplacement);
        if(this.plateau.getCaseSelected() != null)
            this.plateau.setCaseSelected(null);

       return lastDeplacement;
    }

}
