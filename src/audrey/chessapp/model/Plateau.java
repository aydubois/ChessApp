package audrey.chessapp.model;

import audrey.chessapp.model.pieces.*;

import java.util.ArrayList;

import static java.lang.System.*;

public class Plateau {
    private final ArrayList<Case> cases = new ArrayList<>();
    private final ArrayList<Piece> piecesBlanches = new ArrayList<>();
    private final ArrayList<Piece> piecesNoires = new ArrayList<>();
    private Case caseSelected = null;
    private boolean theEnd = false;
    private ArrayList<Case> potentialMoves = new ArrayList<>();


    public void initBord(){
        this.createAllBoxes();
        this.createAllPieces();
        this.theEnd = false;
    }

    public void getPotentialMoves(Partie.joueurs joueurActuel){
        Piece pieceSelected = caseSelected.getPiece();
        this.potentialMoves =   pieceSelected.getMove(this, joueurActuel);
    }

    private void createAllBoxes(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case box = new Case("case"+j+""+i);
                cases.add(box);
            }
        }
    }

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

    public Case getCaseSelected() {
        return caseSelected;
    }

    public boolean isTheEnd() {
        return theEnd;
    }

    public ArrayList<Case> getPotentialCases() {
        return potentialMoves;
    }

    public Deplacement actionOnCase(int row, int column, Partie.joueurs joueurActuel){
        Case caseClicked = null;
        for(Case oneCase : cases){
            if(oneCase.getRow() == row && oneCase.getColumn() == column){
                caseClicked = oneCase;
                break;
            }
        }
        if(caseClicked == null){
            out.println("ERROR ===> La case cliquée n'existe pas.");
            return null;
        }
        //Si aucune case sélectionnée
        if(caseSelected == null){
            out.println("aucune case selectionne");
            //Vérification que la case cliquée n'est pas vide
            if(caseClicked.isEmpty()){
                //ne rien faire si la case est vide.
                return null ;
            }
            if (caseClicked.getPiece().getColor() != joueurActuel){
                //ne rien faire si la piece ne correspond pas au joueur
                return null;
            }
            caseClicked.setSelected(true);
            caseSelected = caseClicked;
            this.getPotentialMoves(joueurActuel);
            return null;
        }
        //Si une case est sélectionnée
        else {
            // la case ne fait partie des cases potentielles -> null
            if (this.potentialMoves == null || this.potentialMoves.indexOf(caseClicked) == -1) {
                if (caseClicked.getRow() == caseSelected.getRow() && caseClicked.getColumn() == caseSelected.getColumn()) {
                    //Si meme case -> deselection
                    caseSelected = null;
                }
                return null;
            } else {
                //1er cas -> la case est vide deplacement de la piece
                if (caseClicked.isEmpty()) {
                    Deplacement deplacement = new Deplacement(caseSelected.getPiece(), null, caseSelected, caseClicked);
                    caseClicked.setEmpty(false);
                    caseSelected.setEmpty(true);
                    caseClicked.setPiece(caseSelected.getPiece());
                    caseSelected.setPiece(null);
                    caseSelected.setSelected(false);
                    caseSelected = null;
                    return deplacement;
                }
                //2eme cas -> la case n'est pas vide : mangeage de piece si adversaire
                else {
                    String[] namePieceSplit = caseClicked.getPiece().getName().split(" ");
                    //Si mangeage de roi -> Fin de partie
                    if ("ROI".equals(namePieceSplit[0])) {
                        this.theEnd = true;
                    }
                    Deplacement deplacement = new Deplacement(caseSelected.getPiece(), caseClicked.getPiece(), caseSelected, caseClicked);
                    caseSelected.setEmpty(true);
                    caseClicked.setPiece(caseSelected.getPiece());
                    caseSelected.setPiece(null);
                    caseSelected.setSelected(false);
                    caseSelected = null;

                    return deplacement;
                }
            }
/*


            out.println("CaseSelectionne --> "+caseSelected.getColumn()+""+ caseSelected.getRow());
            //1er cas -> la case est vide deplacement de la piece
            if(caseClicked.isEmpty()){
                out.println("case selected url piece--> "+caseSelected.getPiece().getUrlImage());
                Deplacement deplacement = new Deplacement(caseSelected.getPiece(),null, caseSelected, caseClicked);
                caseClicked.setEmpty(false);
                caseSelected.setEmpty(true);
                caseClicked.setPiece(caseSelected.getPiece());
                caseSelected.setPiece(null);
                caseSelected.setSelected(false);
                caseSelected = null;
                return deplacement;
            }
            //2eme cas -> la case n'est pas vide : mangeage de piece si adversaire
            else{
                if(caseClicked.getPiece().getColor() == joueurActuel){
                    if(caseClicked.getRow() == caseSelected.getRow() && caseClicked.getColumn() == caseSelected.getColumn() ){
                        //Si meme case -> deselection
                        caseSelected = null;
                    }
                    //ne rien faire si les 2 pieces sont du meme joueur
                    return null;
                }
                String[] namePieceSplit = caseClicked.getPiece().getName().split(" ");
                if("ROI".equals(namePieceSplit[0])){
                    //Fin de partie
                    this.theEnd = true;

                }
                Deplacement deplacement = new Deplacement(caseSelected.getPiece(),caseClicked.getPiece(), caseSelected, caseClicked);
                caseSelected.setEmpty(true);
                caseClicked.setPiece(caseSelected.getPiece());
                caseSelected.setPiece(null);
                caseSelected.setSelected(false);
                caseSelected = null;

                return deplacement;
            }
        }
*/
        }
    }

    public ArrayList<Case> getCases() {
        return cases;
    }

    public Case getOneCase(int row, int column){
        for(Case oneCase : cases){
            if(oneCase.getRow() == row && oneCase.getColumn() == column){
                return oneCase;
            }
        }
        return null;
    }
}
