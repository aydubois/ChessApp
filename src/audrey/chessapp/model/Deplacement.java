package audrey.chessapp.model;

public class Deplacement {
    private final Piece pieceDeplacee;
    private final Case caseDepart;
    private final Case caseFinal;

    public Case getCaseDepart() {
        return caseDepart;
    }

    public Case getCaseFinal() {
        return caseFinal;
    }

    public Deplacement(Piece pieceDeplacee, Case caseDepart, Case caseFinal){
        this.pieceDeplacee = pieceDeplacee;
        this.caseDepart = caseDepart;
        this.caseFinal = caseFinal;
    }

    public Piece getPieceDeplacee() {
        return pieceDeplacee;
    }
}
