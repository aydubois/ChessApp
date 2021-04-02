package audrey.chessapp.model;

public class Deplacement {
    private final Piece pieceDeplacee;
    private final Piece pieceMangee;
    private final Case caseDepart;
    private final Case caseFinal;

    public Deplacement(Piece pieceDeplacee, Piece pieceMangee,Case caseDepart, Case caseFinal){
        this.pieceDeplacee = pieceDeplacee;
        this.pieceMangee = pieceMangee;
        this.caseDepart = caseDepart;
        this.caseFinal = caseFinal;
    }

    public Case getCaseDepart() {
        return caseDepart;
    }

    public Case getCaseFinal() {
        return caseFinal;
    }


    public Piece getPieceDeplacee() {
        return pieceDeplacee;
    }

    public Piece getPieceMangee() {
        return pieceMangee;
    }
}
