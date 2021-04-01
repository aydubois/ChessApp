package audrey.chessapp.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Piece {
    private final String name;
    private final Partie.joueurs color;
    private  final String urlImage;
    private Case presentCase;

    public Piece(String name, Partie.joueurs color, Case presentCase){
        this.name = name;
        this.color = color;
        this.presentCase = presentCase;
        this.urlImage = this.initUrlImage();
    }

    public String getUrlImage(){
        return this.urlImage;
    }

    public String getName() {
        return name;
    }

    public void getMove(Plateau plateau, Case cases){

    }


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

}
