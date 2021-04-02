package audrey.chessapp.model.pieces;

import audrey.chessapp.model.Case;
import audrey.chessapp.model.Partie;
import audrey.chessapp.model.Piece;
import audrey.chessapp.model.Plateau;

import java.util.ArrayList;

public class Cavalier extends Piece {
    public Cavalier(String name, Partie.joueurs color, Case presentCase) {
        super(name, color, presentCase);
    }

    @Override
    public ArrayList<Case> getMove(Plateau plateau, Partie.joueurs joueurActuel) {

        //remise a zéro des cases potentielles
        this.potentialCases = new ArrayList<>();
        int rowActual = this.getPresentCase().getRow();
        int columnActual = this.getPresentCase().getColumn();

        this.goUpLeft(plateau,rowActual, columnActual);
        this.goUpRight(plateau,rowActual, columnActual);
        this.goDownLeft(plateau,rowActual, columnActual);
        this.goDownRight(plateau,rowActual, columnActual);

        return this.potentialCases;
    }

    private void goUpLeft(Plateau plateau, int row, int column){
        row = row +2;
        this.checkMoveSpecial(plateau, row, --column);
    }
    private void goUpRight(Plateau plateau, int row, int column){
        row = row +2;
        this.checkMoveSpecial(plateau, row, ++column);
    }
    private void goDownLeft(Plateau plateau, int row, int column){
        row = row -2;
        this.checkMoveSpecial(plateau, row, --column);
    }
    private void goDownRight(Plateau plateau, int row, int column){
        row = row -2;
        this.checkMoveSpecial(plateau, row, ++column);
    }

    private void checkMoveSpecial(Plateau plateau, int row, int column){
        if(row < 0 || column < 0 || row > 7 || column > 7)
            return;
        Case potentialCase  = plateau.getOneCase(row, column);
        if(potentialCase.isEmpty() || (potentialCase.getPiece() != null && potentialCase.getPiece().getColor() != this.color)){
            this.potentialCases.add(potentialCase);
        }
    }
}
