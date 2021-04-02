package audrey.chessapp.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public abstract class Piece {
    protected final String name;
    protected final Partie.joueurs color;
    protected  final String urlImage;
    protected Case presentCase;
    protected ArrayList<Case> potentialCases = new ArrayList<>();

    public Piece(String name, Partie.joueurs color, Case presentCase){
        this.name = name;
        this.color = color;
        this.presentCase = presentCase;
        this.urlImage = this.initUrlImage();
    }

    public String getUrlImage(){
        return this.urlImage;
    }

    public Case getPresentCase() {
        return presentCase;
    }

    public void setPresentCase(Case presentCase) {
        this.presentCase = presentCase;
    }

    public String getName() {
        return name;
    }

    public abstract ArrayList<Case> getMove(Plateau plateau, Partie.joueurs joueurActuel);


    public Partie.joueurs getColor() {
        return color;
    }


    private String initUrlImage(){
        String url = "../images/";
        String[] nameSplit = this.name.split(" ");
        switch (nameSplit[0]){
            case "TOUR":
                url += "NOIR".equals(nameSplit[1]) ? "black_rook.png": "white_rook.png" ;
                break;
            case "CAVALIER":
                url += "NOIR".equals(nameSplit[1])? "black_knight.png": "white_knight.png" ;
                break;
            case"FOU":
                url += "NOIR".equals(nameSplit[1]) ? "black_bishop.png": "white_bishop.png" ;
                break;
            case"REINE":
                url += "NOIR".equals(nameSplit[1]) ? "black_queen.png": "white_queen.png" ;
                break;
            case"ROI":
                url += "NOIR".equals(nameSplit[1]) ? "black_king.png": "white_king.png" ;
                break;
            case"PION":
                url += "NOIR".equals(nameSplit[1])? "black_pawn.png": "white_pawn.png" ;
                break;
        }


        return url;
    }
    protected boolean checkCaseOk(Plateau plateau, int row, int column){
        //Si sortie du plateau -> pas ok
        if(row < 0 || column < 0 || row > 7 || column > 7)
            return false;
        Case potentialCase  = plateau.getOneCase(row, column);
        //Si case vide -> ok et on continue
        if(potentialCase.isEmpty()){
            this.potentialCases.add(potentialCase);
            return true;
        }
        //Si case joueur adversaire -> ok mais on continue pas
        if(potentialCase.getPiece() != null && potentialCase.getPiece().getColor() != this.color){
            this.potentialCases.add(potentialCase);
            return false;
        }
        return false;
    }
}
