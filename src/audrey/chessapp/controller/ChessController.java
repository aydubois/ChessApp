package audrey.chessapp.controller;

import audrey.chessapp.model.Partie;
import audrey.chessapp.model.Plateau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ChessController implements Initializable {

    @FXML
    private GridPane    gridPanePlateau;
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
    private ImageView   imageViewTourNoir1, imageViewTourNoir2, imageViewTourBlanc1, imageViewTourBlanc2,
                        imageViewCavalierNoir1, imageViewCavalierNoir2, imageViewCavalierBlanc1, imageViewCavalierBlanc2,
                        imageViewFouNoir1, imageViewFouNoir2,imageViewFouBlanc1,imageViewFouBlanc2,
                        imageViewReineNoir, imageViewReineBlanc, imageViewRoiNoir, imageViewRoiBlanc,
                        imageViewPionNoir1,imageViewPionNoir2,imageViewPionNoir3,imageViewPionNoir4,
                        imageViewPionNoir5,imageViewPionNoir6,imageViewPionNoir7,imageViewPionNoir8,
                        imageViewPionBlanc1,imageViewPionBlanc2,imageViewPionBlanc3,imageViewPionBlanc4,
                        imageViewPionBlanc5,imageViewPionBlanc6,imageViewPionBlanc7,imageViewPionBlanc8;


    @FXML
    private Button buttonAnnuler, buttonNouvellePartie, buttonQuitter;
    @FXML
    private Label labelEquipe;


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
        this.addEventNewGame();
    }

    private void addEventNewGame(){
        this.buttonNouvellePartie.setOnMouseClicked(mouseEvent -> {
            this.partie.newGame();
            this.plateau = this.partie.getPlateau();
            if(this.partie.getJoueurActuel() == Partie.joueurs.BLANC)
                this.labelEquipe.setText("Blancs");
            else
                this.labelEquipe.setText("Noirs");
            this.addEventClickCase();
        });
    }

    private void addEventClickCase(){
        for(Pane pane : panes){
            System.out.println(pane);
            pane.setOnMouseClicked(mouseEvent -> {
                String name = pane.getId();
                String[] nameSplit = name.split("");
                int row =Integer.parseInt(nameSplit[nameSplit.length-2]);
                int column =Integer.parseInt(nameSplit[nameSplit.length-1]);
                // Le joueur peut-il selectionner la case ?
                // oui si
                /**
                 * Aucune case déjà selectionnée
                 * Si même case déjà selectionnée -> déselection
                 * Case avec piece du même joueur
                 */

                // Si selection ok
                /**
                 * case->setSelect(true)
                 * pane.setBackground()
                 */
            });
        }
    }
}
