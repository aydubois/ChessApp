package audrey.chessapp.model.pieces;

import audrey.chessapp.model.Case;
import audrey.chessapp.model.Partie;
import audrey.chessapp.model.Piece;
import audrey.chessapp.model.Plateau;

import java.util.ArrayList;

public class Pion extends Piece {
    private boolean firstMove = true;
    private final int rowInitial;
    private final int columnInitial;
    public Pion(String name, Partie.joueurs color, Case presentCase) {
        super(name, color, presentCase);
        this.rowInitial = presentCase.getRow();
        this.columnInitial = presentCase.getColumn();
    }
    @Override
    public void setPresentCase(Case presentCase) {
        if(presentCase.getRow() == rowInitial && presentCase.getColumn() == columnInitial) // Si retour en arriere (Annuler)
            this.firstMove = true;
        else
            this.firstMove = false;
        this.presentCase = presentCase;
        if(this.presentCase.getRow() == 0 || this.presentCase.getRow() == 7)
            this.promotion();
    }

    @Override
    public ArrayList<Case> getMove(Plateau plateau, Partie.joueurs joueurActuel) {
        //remise a zéro des cases potentielles
        this.potentialCases = new ArrayList<>();
        int rowActual = this.getPresentCase().getRow();
        int columnActual = this.getPresentCase().getColumn();

        if(this.color == Partie.joueurs.BLANC){
            this.checkGoUp(plateau, rowActual, columnActual);
            this.checkGoDiagLeftUp(plateau, rowActual, columnActual);
            this.checkGoDiagRightUp(plateau, rowActual, columnActual);
        }else{
            this.checkGoDown(plateau, rowActual, columnActual);
            this.checkGoDiagLeftDown(plateau, rowActual, columnActual);
            this.checkGoDiagRightDown(plateau, rowActual, columnActual);
        }


        return this.potentialCases;
    }

    private void checkGoUp(Plateau plateau, int row , int column){
        int nbMax = firstMove ? 2 : 1;
        boolean itisOk = true;
        for (int i = 0; i < nbMax; i++) {
            System.out.println("nb boucle : "+ firstMove);
            if(itisOk)
                itisOk = this.checkCaseSpecialOk(plateau, --row, column);
        }
    }
    private void checkGoDown(Plateau plateau, int row , int column){
        int nbMax = firstMove ? 2 : 1;
        boolean itisOk = true;
        for (int i = 0; i < nbMax; i++) {
            if(itisOk)
                itisOk = this.checkCaseSpecialOk(plateau, ++row, column);
        }
    }
    private void checkGoDiagLeftUp(Plateau plateau, int row , int column){
        this.checkCaseSpecial(plateau, --row, --column);
    }
    private void checkGoDiagRightUp(Plateau plateau, int row , int column){
        this.checkCaseSpecial(plateau, --row, ++column);
    }
    private void checkGoDiagLeftDown(Plateau plateau, int row , int column){
        this.checkCaseSpecial(plateau, ++row, --column);
    }
    private void checkGoDiagRightDown(Plateau plateau, int row , int column){
        this.checkCaseSpecial(plateau, ++row, ++column);
    }

    //Special Diagonal
    private void checkCaseSpecial(Plateau plateau, int row, int column){
        //Si sortie du plateau -> pas ok
        if(row < 0 || column < 0 || row > 7 || column > 7)
            return;

        Case potentialCase  = plateau.getOneCase(row, column);
        if(potentialCase.getPiece() != null && potentialCase.getPiece().getColor() != this.color){
            this.potentialCases.add(potentialCase);
        }
    }

    //Special tout droit
    private boolean checkCaseSpecialOk(Plateau plateau, int row, int column){
        //Si sortie du plateau -> pas ok
        if(row < 0 || column < 0 || row > 7 || column > 7)
            return false;

        Case potentialCase  = plateau.getOneCase(row, column);
        if(potentialCase.isEmpty()){
            this.potentialCases.add(potentialCase);
            return true;
        }
        return false;
    }

    private void promotion(){
        String newName = "";
        if(this.color == Partie.joueurs.BLANC)
            newName = "REINE BLANC";
        else
            newName = "REINE NOIR";
        Piece reine = new Reine(newName, this.color, this.presentCase);
        this.presentCase.setPiece(reine);
    }
}
