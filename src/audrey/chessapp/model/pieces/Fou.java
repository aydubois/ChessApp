package audrey.chessapp.model.pieces;

import audrey.chessapp.model.Case;
import audrey.chessapp.model.Partie;
import audrey.chessapp.model.Piece;
import audrey.chessapp.model.Plateau;

import java.util.ArrayList;

public class Fou extends Piece {
    public Fou(String name, Partie.joueurs color, Case presentCase) {
        super(name, color, presentCase);
    }

    @Override
    public ArrayList<Case> getMove(Plateau plateau, Partie.joueurs joueurActuel) {
        //remise a zéro des cases potentielles
        this.potentialCases = new ArrayList<>();
        int rowActual = this.getPresentCase().getRow();
        int columnActual = this.getPresentCase().getColumn();

        this.checkGoDiagLeftUp(plateau, rowActual, columnActual);
        this.checkGoDiagRightUp(plateau, rowActual, columnActual);
        this.checkGoDiagLeftDown(plateau, rowActual, columnActual);
        this.checkGoDiagRightDown(plateau, rowActual, columnActual);

        return this.potentialCases;
    }
    private void checkGoDiagLeftUp(Plateau plateau, int row , int column){
        while(this.checkCaseOk(plateau, --row, --column));
    }
    private void checkGoDiagRightUp(Plateau plateau, int row , int column){
        while(this.checkCaseOk(plateau, --row, ++column));
    }
    private void checkGoDiagLeftDown(Plateau plateau, int row , int column){
        while(this.checkCaseOk(plateau, ++row, --column));
    }
    private void checkGoDiagRightDown(Plateau plateau, int row , int column){
        while(this.checkCaseOk(plateau, ++row, ++column));
    }

}
