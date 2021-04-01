package audrey.chessapp.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Piece {
    private final String name;
    private final String color;
    private final String urlImage;
    private Case presentCase;

    public Piece(String name, String color, Case presentCase){
        this.name = name;
        this.color = color;
        this.presentCase = presentCase;
        this.urlImage = this.getUrlImage();
    }


    public void getMove(Plateau plateau, Case cases){

    }


    private String getUrlImage(){
        String url = "audrey/chessapp/images/";
        String[] nameSplit = this.name.split(" ");
        switch (nameSplit[0]){
            case "TOUR":
                url += nameSplit[1] == "NOIR" ? "black_rook.png": "white_rook.png" ;
                break;
            case "CAVALIER":
                url += nameSplit[1] == "NOIR" ? "black_knight.png": "white_knight.png" ;
                break;
            case"FOU":
                url += nameSplit[1] == "NOIR" ? "black_bishop.png": "white_bishop.png" ;
                break;
            case"REINE":
                url += nameSplit[1] == "NOIR" ? "black_queen.png": "white_queen.png" ;
                break;
            case"ROI":
                url += nameSplit[1] == "NOIR" ? "black_king.png": "white_king.png" ;
                break;
            case"PION":
                url += nameSplit[1] == "NOIR" ? "black_pawn.png": "white_pawn.png" ;
                break;
        }


        return url;
    }
}
