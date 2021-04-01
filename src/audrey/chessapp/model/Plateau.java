package audrey.chessapp.model;

import java.util.ArrayList;

public class Plateau {
    private ArrayList<Case> cases = new ArrayList<>();
    private ArrayList<Piece> piecesBlanches = new ArrayList<>();
    private ArrayList<Piece> piecesNoires = new ArrayList<>();
    private Case caseSelected = null;

    private enum namePiece {TOUR,CAVALIER,FOU,REINE,ROI,PION};

    public void initBord(){
        this.createAllBoxes();
        this.createAllPieces();
    }

    public void getPotentialMoves(){

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
            switch (oneCase.getRow()){
                case 0 :
                    color = Partie.joueurs.NOIR;
                    if(oneCase.getColumn() == 0 || oneCase.getColumn() == 7){
                        name = "TOUR NOIR ";
                        name += oneCase.getColumn() == 0 ? "1" : "2";
                    }
                    if(oneCase.getColumn() == 1 || oneCase.getColumn() == 6){
                        name = "CAVALIER NOIR ";
                        name += oneCase.getColumn() == 1 ? "1" : "2";
                    }
                    if(oneCase.getColumn() == 2 || oneCase.getColumn() == 5){
                        name = "FOU NOIR ";
                        name += oneCase.getColumn() == 2 ? "1" : "2";
                    }
                    if(oneCase.getColumn() == 3){
                        name = "REINE NOIR ";
                    }
                    if(oneCase.getColumn() == 4){
                        name = "ROI NOIR ";
                    }
                    break;
                case 1 :
                    color = Partie.joueurs.NOIR;
                    name = "PION NOIR " + (oneCase.getColumn()+1);
                    break;
                case 6 :
                    color = Partie.joueurs.BLANC;
                    name = "PION BLANC " + (oneCase.getColumn()+1);
                    break;
                case 7 :
                    color = Partie.joueurs.BLANC;
                    if(oneCase.getColumn() == 0 || oneCase.getColumn() == 7){
                        name = "TOUR BLANC ";
                        name += oneCase.getColumn() == 0 ? "1" : "2";
                    }
                    if(oneCase.getColumn() == 1 || oneCase.getColumn() == 6){
                        name = "CAVALIER BLANC ";
                        name += oneCase.getColumn() == 1 ? "1" : "2";
                    }
                    if(oneCase.getColumn() == 2 || oneCase.getColumn() == 5){
                        name = "FOU BLANC ";
                        name += oneCase.getColumn() == 2 ? "1" : "2";
                    }
                    if(oneCase.getColumn() == 3){
                        name = "REINE BLANC ";
                    }
                    if(oneCase.getColumn() == 3){
                        name = "ROI BLANC ";
                    }
                    break;
                default:
                    break;
            }
            if(!"".equals(name) && !"".equals(color)){
                Piece piece = new Piece(name, color, oneCase);
                oneCase.setEmpty(false);
                oneCase.setPiece(piece);
                if (color == Partie.joueurs.NOIR) {
                    piecesNoires.add(piece);
                } else {
                    piecesBlanches.add(piece);
                }
            }
        }
    }

    public Case getCaseSelected() {
        return caseSelected;
    }

    public Deplacement actionOnCase(int row, int column, Partie.joueurs joueurActuel){
        Case caseClicked = null;
        for(Case oneCase : cases){
            if(oneCase.getRow() == row && oneCase.getColumn() == column){
                caseClicked = oneCase;
                break;
            }
        }
        System.out.println("Case cliquée : "+caseClicked.getColumn()+""+caseClicked.getRow());
        if(caseClicked == null){
            System.out.println("ERROR ===> La case cliquée n'existe pas.");
            return null;
        }
        //Si aucune case sélectionnée
        if(caseSelected == null){
            System.out.println("aucune case selectionne");
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
        }
        //Si une case est sélectionnée
        else{
            System.out.println("CaseSelectionne --> "+caseSelected.getColumn()+""+ caseSelected.getRow());
            //1er cas -> la case est vide deplacement de la piece
            if(caseClicked.isEmpty()){
                System.out.println("case selected url piece--> "+caseSelected.getPiece().getUrlImage());
                Deplacement deplacement = new Deplacement(caseSelected.getPiece(), caseSelected, caseClicked);
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

                Deplacement deplacement = new Deplacement(caseSelected.getPiece(), caseSelected, caseClicked);
                caseSelected.setEmpty(true);
                caseClicked.setPiece(caseSelected.getPiece());
                caseSelected.setPiece(null);
                caseSelected.setSelected(false);
                caseSelected = null;

                return deplacement;
            }
        }

        return null;

    }

    public Case getCase(int row, int column){
        for(Case oneCase : cases){
            if(oneCase.getRow() == row && oneCase.getColumn() == column){
                return oneCase;
            }
        }
        return null;
    }
}
