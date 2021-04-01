package audrey.chessapp.controller;

import audrey.chessapp.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ChessController implements Initializable {
    private final String backgroundColorSelected = "-fx-background-color:#b092b0;";
    private final String backgroundColorWhite = "-fx-background-color:white;";
    private final String backgroundColorGrey = "-fx-background-color:grey;";


    @FXML
    private Pane        paneCase00,paneCase10,paneCase20,paneCase30,paneCase40,paneCase50,paneCase60,paneCase70,
                        paneCase01,paneCase11,paneCase21,paneCase31,paneCase41,paneCase51,paneCase61,paneCase71,
                        paneCase02,paneCase12,paneCase22,paneCase32,paneCase42,paneCase52,paneCase62,paneCase72,
                        paneCase03,paneCase13,paneCase23,paneCase33,paneCase43,paneCase53,paneCase63,paneCase73,
                        paneCase04,paneCase14,paneCase24,paneCase34,paneCase44,paneCase54,paneCase64,paneCase74,
                        paneCase05,paneCase15,paneCase25,paneCase35,paneCase45,paneCase55,paneCase65,paneCase75,
                        paneCase06,paneCase16,paneCase26,paneCase36,paneCase46,paneCase56,paneCase66,paneCase76,
                        paneCase07,paneCase17,paneCase27,paneCase37,paneCase47,paneCase57,paneCase67,paneCase77;
    private ArrayList<Pane> panes;
    @FXML
    private ImageView   imageView00,imageView10,imageView20,imageView30,imageView40,imageView50,imageView60,imageView70,
                        imageView01,imageView11,imageView21,imageView31,imageView41,imageView51,imageView61,imageView71,
                        imageView02,imageView12,imageView22,imageView32,imageView42,imageView52,imageView62,imageView72,
                        imageView03,imageView13,imageView23,imageView33,imageView43,imageView53,imageView63,imageView73,
                        imageView04,imageView14,imageView24,imageView34,imageView44,imageView54,imageView64,imageView74,
                        imageView05,imageView15,imageView25,imageView35,imageView45,imageView55,imageView65,imageView75,
                        imageView06,imageView16,imageView26,imageView36,imageView46,imageView56,imageView66,imageView76,
                        imageView07,imageView17,imageView27,imageView37,imageView47,imageView57,imageView67,imageView77;

    private ArrayList<ImageView> imageViews;

    @FXML
    private Button buttonAnnuler, buttonNouvellePartie, buttonQuitter;
    @FXML
    private Label labelEquipe;
    @FXML
    private Label labelTitle;


    private Partie partie = new Partie();
    private Plateau plateau;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panes = new ArrayList<>(Arrays.asList(paneCase00,paneCase10,paneCase20,paneCase30,paneCase40,paneCase50,paneCase60,paneCase70,
                paneCase01,paneCase11,paneCase21,paneCase31,paneCase41,paneCase51,paneCase61,paneCase71,
                paneCase02,paneCase12,paneCase22,paneCase32,paneCase42,paneCase52,paneCase62,paneCase72,
                paneCase03,paneCase13,paneCase23,paneCase33,paneCase43,paneCase53,paneCase63,paneCase73,
                paneCase04,paneCase14,paneCase24,paneCase34,paneCase44,paneCase54,paneCase64,paneCase74,
                paneCase05,paneCase15,paneCase25,paneCase35,paneCase45,paneCase55,paneCase65,paneCase75,
                paneCase06,paneCase16,paneCase26,paneCase36,paneCase46,paneCase56,paneCase66,paneCase76,
                paneCase07,paneCase17,paneCase27,paneCase37,paneCase47,paneCase57,paneCase67,paneCase77));
        imageViews = new ArrayList<>(Arrays.asList(imageView00,imageView10,imageView20,imageView30,imageView40,imageView50,imageView60,imageView70,
                imageView01,imageView11,imageView21,imageView31,imageView41,imageView51,imageView61,imageView71,
                imageView02,imageView12,imageView22,imageView32,imageView42,imageView52,imageView62,imageView72,
                imageView03,imageView13,imageView23,imageView33,imageView43,imageView53,imageView63,imageView73,
                imageView04,imageView14,imageView24,imageView34,imageView44,imageView54,imageView64,imageView74,
                imageView05,imageView15,imageView25,imageView35,imageView45,imageView55,imageView65,imageView75,
                imageView06,imageView16,imageView26,imageView36,imageView46,imageView56,imageView66,imageView76,
                imageView07,imageView17,imageView27,imageView37,imageView47,imageView57,imageView67,imageView77));
        this.addEventNewGame();

        this.addEventQuitGame();
    }

    private void addEventNewGame(){

        this.buttonNouvellePartie.setOnMouseClicked(mouseEvent -> {
            this.partie.newGame();
            this.changeLabel();
            //Placement des images
            //Ajout des evenement seulement s'il n'existent pas déjà
            if(paneCase00.getOnMouseClicked() == null)
                this.addEventClickCase();
            else // replacement des pieces uniquement si une partie a deja ete lancée
                this.initPiecesOnBoard();
        });
    }

    private void addEventClickCase(){
        for(Pane pane : panes){

            pane.setOnMouseClicked(mouseEvent -> {
                if(!this.partie.isTheEnd()){

                    boolean selectedOk = this.partie.trySelected(pane.getId());

                    //envoi de l'evenement à la partie -> c'est elle qui gère ce qu'elle doit faire.
                    Deplacement deplacement = this.partie.clickOnCase(pane.getId());
                    System.out.println(deplacement);
                    if(deplacement != null ){
                        //REcherche de l'image
                        String urlName = deplacement.getPieceDeplacee().getUrlImage();
                        System.out.println("url image "+ urlName);
                        //recherche de la case depart
                        Case caseDepart = deplacement.getCaseDepart();
                        for(ImageView imageV : imageViews){
                            if(imageV.getId().equals("imageView"+caseDepart.getColumn()+""+caseDepart.getRow())){
                                imageV.setImage(null);
                                break;
                            }
                        }
                        //recherche de la case final
                        Case caseFinal = deplacement.getCaseFinal();
                        for(ImageView imageV : imageViews){
                            if(imageV.getId().equals("imageView"+caseFinal.getColumn()+""+caseFinal.getRow())){
                                imageV.setImage(new Image(getClass().getResource(urlName).toExternalForm()));
                                break;
                            }
                        }
                        // recherche pane depart pour supprimer le background_selected
                        for(Pane paneD : panes){
                            if(paneD.getId().equals("paneCase"+caseDepart.getColumn()+""+caseDepart.getRow())){
                                this.changeBackground(paneD, false);
                                break;
                            }
                        }
                        this.changeLabel();
                        if(this.partie.isTheEnd()){
                            this.endOfGame();
                        }
                    }else{
                        System.out.println("blah");
                        this.changeBackground(pane, selectedOk);
                    }
                }
            });
        }
    }

    private void changeBackground(Pane pane, boolean selectedOk){
        if(selectedOk){
            pane.setStyle(backgroundColorSelected);
        }else{
            //Verifier que la couleur est bien celle de base (si jamais une case était selectionnée puis déselectionnée)
            int row = this.getRow(pane.getId());
            int column = this.getColumn(pane.getId());
            if(row%2 == 0 && column%2 == 0 && pane.getStyle() != backgroundColorWhite){
                pane.setStyle(backgroundColorWhite);
            }else
            if(row%2 == 0 && column%2 != 0 && pane.getStyle() != backgroundColorGrey){
                pane.setStyle(backgroundColorGrey);
            }else
            if(row%2 != 0 && column%2 == 0 && pane.getStyle() != backgroundColorGrey){
                pane.setStyle(backgroundColorGrey);
            }else
            if(row%2 != 0 && column%2 != 0 && pane.getStyle() != backgroundColorWhite){
                pane.setStyle(backgroundColorWhite);
            }
        }
    }
    private int getRow(String name){
        String[] nameSplit = name.split("");
        return Integer.parseInt(nameSplit[nameSplit.length-1]);
    }
    private int getColumn(String name){
        String[] nameSplit = name.split("");
        return Integer.parseInt(nameSplit[nameSplit.length-2]);

    }

    private void changeLabel(){
        if(this.partie.getJoueurActuel() == Partie.joueurs.BLANC)
            this.labelEquipe.setText("Blancs");
        else
            this.labelEquipe.setText("Noirs");
    }

    private void endOfGame(){
        labelTitle.setText("GAGNANT : ");
        changeLabel();

    }

    private void initPiecesOnBoard(){
        for(ImageView imageV : imageViews){
            String name = "";
            int row = getRow(imageV.getId());
            int column = getColumn(imageV.getId());
            switch (row){
                case 0 :
                    if(column == 0 || column == 7){
                        name =  "black_rook.png";
                    }
                    if(column == 1 || column == 6){
                        name = "black_knight.png";
                    }
                    if(column== 2 || column == 5){
                        name = "black_bishop.png";
                    }
                    if(column == 3){
                        name = "black_queen.png";
                    }
                    if(column == 4){
                        name ="black_king.png";
                    }
                    break;
                case 1 :
                    name = "black_pawn.png";
                    break;
                case 6 :
                    name = "white_pawn.png";
                    break;
                case 7 :
                    if(column== 0 || column == 7){
                        name =  "white_rook.png";
                    }
                    if(column == 1 ||column == 6){
                        name = "white_knight.png";
                    }
                    if(column == 2 || column == 5){
                        name = "white_bishop.png";
                    }
                    if(column == 3){
                        name = "white_queen.png";
                    }
                    if(column == 4){
                        name ="white_king.png";
                    }
                    break;
                default:
                    break;
            }
            if("".equals(name)){
                //Si vide -> aucune piece
                imageV.setImage(null);
            }else{
                imageV.setImage(new Image(getClass().getResource("../images/"+name).toExternalForm()));
            }

        }
    }

    private void addEventQuitGame(){
        buttonQuitter.setOnMouseClicked(event->{System.exit(0);});
    }
}
