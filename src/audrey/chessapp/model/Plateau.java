package audrey.chessapp.model;

import audrey.chessapp.model.pieces.*;

import java.util.ArrayList;

import static java.lang.System.*;

public class Plateau {
    private ArrayList<Case> cases = new ArrayList<>();
    private ArrayList<Piece> piecesBlanches = new ArrayList<>();
    private ArrayList<Piece> piecesNoires = new ArrayList<>();
    private Case caseSelected = null;
    private boolean theEnd = false;
    private ArrayList<Case> potentialMoves = new ArrayList<>();


    /**
     *  Creation des 64 cases et des pièces de chaque joueur
     */
    public void initBoard(){
        this.cases = new ArrayList<>();
        this.createAllBoxes();
        this.createAllPieces();
        this.theEnd = false;
    }

    /**
     * Recherche suivant la pièce sélectionnée des cases potentielles pour le futur déplacement
     * Modification de la variable this.potentialMoves avec les nouvelles valeurs
     * @param joueurActuel (BLANC || NOIR)
     */
    public void getPotentialMoves(Partie.joueurs joueurActuel){
        Piece pieceSelected = caseSelected.getPiece();
        this.potentialMoves =   pieceSelected.getMove(this, joueurActuel);
    }

    /**
     * Création des 64 cases du jeu.
     * Stockage de celles-ci dans la variable this.cases
     */
    private void createAllBoxes(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case box = new Case("case"+j+""+i);
                cases.add(box);
            }
        }
    }

    /**
     * Creation des pieces de départ de chacun des joueurs suivant les coordonnées des cases
     * Mise en place de la liaison entre les pièces et les cases.
     * Ligne 0 et 1 : Pieces Noires / Ligne 6 et 7 : Pieces Blanches
     */
    private void createAllPieces(){
        for(Case oneCase : cases){
            String name = "";
            Partie.joueurs color = null;
            Piece piece = null;
            switch (oneCase.getRow()){
                case 0 :
                    color = Partie.joueurs.NOIR;
                    if(oneCase.getColumn() == 0 || oneCase.getColumn() == 7){
                        name = "TOUR NOIR ";
                        name += oneCase.getColumn() == 0 ? "1" : "2";
                        piece = new Tour(name, color, oneCase);

                    }
                    if(oneCase.getColumn() == 1 || oneCase.getColumn() == 6){
                        name = "CAVALIER NOIR ";
                        name += oneCase.getColumn() == 1 ? "1" : "2";
                        piece = new Cavalier(name, color, oneCase);

                    }
                    if(oneCase.getColumn() == 2 || oneCase.getColumn() == 5){
                        name = "FOU NOIR ";
                        name += oneCase.getColumn() == 2 ? "1" : "2";
                        piece = new Fou(name, color, oneCase);

                    }
                    if(oneCase.getColumn() == 3){
                        name = "REINE NOIR ";
                        piece = new Reine(name, color, oneCase);

                    }
                    if(oneCase.getColumn() == 4){
                        name = "ROI NOIR ";
                        piece = new Roi(name, color, oneCase);

                    }
                    oneCase.setEmpty(false);
                    oneCase.setPiece(piece);
                    piecesNoires.add(piece);
                    break;
                case 1 :
                    color = Partie.joueurs.NOIR;
                    name = "PION NOIR " + (oneCase.getColumn()+1);
                    piece = new Pion(name, color, oneCase);
                    oneCase.setEmpty(false);
                    oneCase.setPiece(piece);
                    piecesNoires.add(piece);

                    break;
                case 6 :
                    color = Partie.joueurs.BLANC;
                    name = "PION BLANC " + (oneCase.getColumn()+1);
                    piece = new Pion(name, color, oneCase);
                    oneCase.setEmpty(false);
                    oneCase.setPiece(piece);
                    piecesBlanches.add(piece);

                    break;
                case 7 :
                    color = Partie.joueurs.BLANC;
                    if(oneCase.getColumn() == 0 || oneCase.getColumn() == 7){
                        name = "TOUR BLANC ";
                        name += oneCase.getColumn() == 0 ? "1" : "2";
                        piece = new Tour(name, color, oneCase);
                    }
                    if(oneCase.getColumn() == 1 || oneCase.getColumn() == 6){
                        name = "CAVALIER BLANC ";
                        name += oneCase.getColumn() == 1 ? "1" : "2";
                        piece = new Cavalier(name, color, oneCase);

                    }
                    if(oneCase.getColumn() == 2 || oneCase.getColumn() == 5){
                        name = "FOU BLANC ";
                        name += oneCase.getColumn() == 2 ? "1" : "2";
                        piece = new Fou(name, color, oneCase);
                    }
                    if(oneCase.getColumn() == 3){
                        name = "REINE BLANC ";
                        piece = new Reine(name, color, oneCase);

                    }
                    if(oneCase.getColumn() == 4){
                        name = "ROI BLANC ";
                        piece = new Roi(name, color, oneCase);

                    }
                    oneCase.setEmpty(false);
                    oneCase.setPiece(piece);
                    piecesBlanches.add(piece);

                    break;
                default:
                    break;
            }

        }
    }

    /**
     *
     * @param row Coordonnée y de la case choisie
     * @param column Coordonnée x de la case choisie
     * @param joueurActuel enum (BLANC, NOIR)
     * @return Déplacement
     * @throws NullPointerException : Renvoie l'exception si pas de déplacement possible. Ne signifie pas forcément une erreur -> Nouvelle sélection/déselection de case
     * Si une case est déjà sélectionnée -> verification que la dernière case cliquée fait partie des mouvements potentiels de la pièce
     * Si aucune case selectionnée -> vérification de la possibilité de sélection de la dernière case
     */
    public Deplacement actionOnCase(int row, int column, Partie.joueurs joueurActuel) throws NullPointerException{
        Case caseClicked = null;
        for(Case oneCase : cases){
            if(oneCase.getRow() == row && oneCase.getColumn() == column){
                caseClicked = oneCase;
                break;
            }
        }
        if(caseClicked == null){
            throw  new NullPointerException("ERROR ===> La case cliquée n'existe pas.");
        }
        //Si aucune case sélectionnée
        if(caseSelected == null){
            out.println("aucune case selectionne");
            //Vérification que la case cliquée n'est pas vide
            if(caseClicked.isEmpty()){
                //ne rien faire si la case est vide.
                throw  new NullPointerException("ERROR ===> La case est vide.");
            }
            if (caseClicked.getPiece().getColor() != joueurActuel){
                //ne rien faire si la piece ne correspond pas au joueur
                throw  new NullPointerException("ERROR ===> La case cliquée possède une pièce du joueur adversaire.");

            }
            caseClicked.setSelected(true);
            caseSelected = caseClicked;
            this.getPotentialMoves(joueurActuel);
            throw  new NullPointerException("Pas de déplacement. Seulement une sélection.");
        }
        //Si une case est sélectionnée
        else {
            // la case ne fait partie des cases potentielles -> null
            if (this.potentialMoves == null || this.potentialMoves.indexOf(caseClicked) == -1) {
                if (caseClicked.getRow() == caseSelected.getRow() && caseClicked.getColumn() == caseSelected.getColumn()) {
                    //Si meme case -> deselection
                    caseSelected = null;
                }
                throw  new NullPointerException("Déselection de la case.");
            } else {
                //1er cas -> la case est vide deplacement de la piece
                if (caseClicked.isEmpty()) {
                    String[] namePieceDeplaceeSplit = caseSelected.getPiece().getName().split(" ");

                    Deplacement deplacement = new Deplacement(caseSelected.getPiece(), null, caseSelected, caseClicked, false);
                    caseClicked.setEmpty(false);
                    caseSelected.setEmpty(true);
                    caseClicked.setPiece(caseSelected.getPiece());
                    caseSelected.setPiece(null);
                    caseSelected.setSelected(false);
                    caseSelected = null;

                    //Si promotion pion
                    if("PION".equals(namePieceDeplaceeSplit[0]) && deplacement.getPieceDeplacee() != caseClicked.getPiece()){
                        deplacement.setPieceDeplaceeNonPromu(deplacement.getPieceDeplacee());
                        deplacement.setPieceDeplacee(caseClicked.getPiece());
                        deplacement.setPromotion(true);
                    }
                    return deplacement;
                }
                //2eme cas -> la case n'est pas vide : mangeage de piece si adversaire
                else {
                    String[] namePieceSplit = caseClicked.getPiece().getName().split(" ");
                    String[] namePieceDeplaceeSplit = caseSelected.getPiece().getName().split(" ");
                    //Si mangeage de roi -> Fin de partie
                    if ("ROI".equals(namePieceSplit[0])) {
                        this.theEnd = true;
                    }
                    Deplacement deplacement = new Deplacement(caseSelected.getPiece(), caseClicked.getPiece(), caseSelected, caseClicked, false);
                    caseSelected.setEmpty(true);
                    caseClicked.setPiece(caseSelected.getPiece());
                    caseSelected.setPiece(null);
                    caseSelected.setSelected(false);
                    caseSelected = null;

                    //Si promotion pion
                    if("PION".equals(namePieceDeplaceeSplit[0]) && deplacement.getPieceDeplacee() != caseClicked.getPiece()){
                        deplacement.setPieceDeplaceeNonPromu(deplacement.getPieceDeplacee());
                        deplacement.setPieceDeplacee(caseClicked.getPiece());
                        deplacement.setPromotion(true);
                    }
                    return deplacement;
                }
            }
        }
    }

    /**
     * Renvoie la case correspondante aux coordonnées voulues.
     * @param row Coordonnée y de la case
     * @param column Coordonnée x de la case
     * @return Case avec ces coordonnées.
     */
    public Case getOneCase(int row, int column){
        for(Case oneCase : cases){
            if(oneCase.getRow() == row && oneCase.getColumn() == column){
                return oneCase;
            }
        }
        return null;
    }

    /**
     * @return Case  - la case déjà sélectionné par le joueur
     */
    public Case getCaseSelected() {
        return caseSelected;
    }

    public void setCaseSelected(Case caseSelected) {
        this.caseSelected = caseSelected;
    }

    /**
     * @return boolean - si c'est la fin de partie ou non
     */
    public boolean isTheEnd() {
        return theEnd;
    }

    /**
     *
     * @return ArrayList<Case> - Liste de toutes les cases selectionnables par le joueur.
     */
    public ArrayList<Case> getPotentialCases() {
        return potentialMoves;
    }
}
