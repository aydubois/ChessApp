package audrey.chessapp.model;

public class Deplacement {
    private Piece pieceDeplacee;
    private final Piece pieceMangee;
    private  Piece pieceDeplaceeNonPromu;
    private final Case caseDepart;
    private final Case caseFinal;
    private boolean promotion;


    public Deplacement(Piece pieceDeplacee, Piece pieceMangee, Case caseDepart, Case caseFinal, boolean promotion){
        this.pieceDeplacee = pieceDeplacee;
        this.pieceMangee = pieceMangee;
        this.caseDepart = caseDepart;
        this.caseFinal = caseFinal;
        this.promotion = promotion;
    }

    public Case getCaseDepart() {
        return caseDepart;
    }

    public Case getCaseFinal() {
        return caseFinal;
    }

    public boolean isPromotion() {
        return promotion;
    }
    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
    public Piece getPieceDeplacee() {
        return pieceDeplacee;
    }

    public Piece getPieceDeplaceeNonPromu() {
        return pieceDeplaceeNonPromu;
    }

    public void setPieceDeplaceeNonPromu(Piece pieceDeplaceeNonPromu) {
        this.pieceDeplaceeNonPromu = pieceDeplaceeNonPromu;
    }

    public Piece getPieceMangee() {
        return pieceMangee;
    }

    public void setPieceDeplacee(Piece piece){
        this.pieceDeplacee = piece;
    }
}

