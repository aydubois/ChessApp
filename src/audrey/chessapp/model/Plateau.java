package audrey.chessapp.model;

import java.util.ArrayList;

public class Plateau {
    private ArrayList<Case> cases = new ArrayList<>();
    private ArrayList<Piece> piecesBlanches = new ArrayList<>();
    private ArrayList<Piece> piecesNoires = new ArrayList<>();

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
            String color = "";
            switch (oneCase.getRow()){
                case 0 :
                    color = "NOIR";
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
                    if(oneCase.getColumn() == 3){
                        name = "ROI NOIR ";
                    }
                    break;
                case 1 :
                    color = "NOIR";
                    name = "PION NOIR" + (oneCase.getColumn()+1);
                    break;
                case 6 :
                    color = "BLANC";
                    name = "PION BLANC" + (oneCase.getColumn()+1);
                    break;
                case 7 :
                    color = "BLANC";
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
                if (color == "NOIR") {
                    piecesNoires.add(piece);
                } else {
                    piecesBlanches.add(piece);
                }
            }
        }
    }
    public boolean getCaseSelectedByCoordinate(int row, int column){
        for(Case oneCase : cases){
            if(oneCase.getRow() == row && oneCase.getColumn() == column){
                return oneCase.isSelected();
            }
        }
        return false;
    }
    public void changeSelectCase(int row, int column, boolean selected){
        for(Case oneCase : cases){
            if(oneCase.getRow() == row && oneCase.getColumn() == column){
                oneCase.setSelected(selected);
                break;
            }
        }
    }
}
