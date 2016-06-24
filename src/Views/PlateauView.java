package Views;

import Controler.CustomEventHandler;
import Models.Case;
import Models.Plateau;

import Models.PlateauNormal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.*;

import java.util.Observer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.javatuples.Triplet;
import utils.utilitaires;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Epulapp
 */
public class PlateauView implements Observer {

    private GridPane mainGrid;
    private GridPane rootGrid;
    private Label messageLabel;
    private Label timerLabel;
    private JFXButton resetBtn;
    private JFXSlider sliderNbBomb;
    private JFXSlider sliderNbCase;
    private JFXColorPicker colorPicker;
    private ScrollPane scroolPane;

    public PlateauView() {
        Font myFont = new Font("Serif", 30);
        rootGrid = new GridPane();
        mainGrid = new GridPane();
        scroolPane = new ScrollPane(mainGrid);
        resetBtn = new JFXButton();
        resetBtn.setText("Recommencer");
        String style = " -fx-border-color: white; " + "-fx-border-width: 3;" + "-fx-background-color: #61E79E;" + "-fx-spacing: 5px;" + "-fx-text-fill: white;";
        resetBtn.setStyle(style);
        resetBtn.setPrefHeight(50);
        resetBtn.setPrefWidth(250);
        resetBtn.setFont(myFont);
        messageLabel = new Label("");
        timerLabel = new Label("");
        timerLabel.setFont(myFont);
        sliderNbBomb = new JFXSlider();
        sliderNbCase = new JFXSlider();
        sliderNbCase.setMax(50);
        sliderNbCase.setMin(10);
        sliderNbBomb.setValue(10);
        sliderNbCase.setValue(10);
        sliderNbBomb.setTooltip(new Tooltip("nombre de bombes"));
        sliderNbCase.setTooltip(new Tooltip("taille du plateau"));
        sliderNbBomb.setPrefSize(200, 50);
        sliderNbCase.setPrefSize(200, 50);
        
        colorPicker = new JFXColorPicker();
        colorPicker.setValue(Color.web("#9CC4E4"));

        rootGrid.add(scroolPane, 0, 0);
        rootGrid.add(resetBtn, 0, 1);
        rootGrid.add(messageLabel, 0, 2);
        rootGrid.add(timerLabel, 1, 1);
        rootGrid.add(sliderNbBomb, 0, 3);
        rootGrid.add(sliderNbCase, 0, 4);
        rootGrid.add(colorPicker, 0, 5);
        messageLabel.setFont(myFont);

    }

    public void addButton(int i, int j, Case courantCase, PlateauNormal plt) {
        ObsButton btn = new ObsButton(courantCase, plt, utilitaires.toHex(colorPicker.getValue().getRed(), colorPicker.getValue().getGreen(), colorPicker.getValue().getBlue()));

        courantCase.addObserver(btn);

        if (plt.getLbMessage() != "") {
            btn.setDisable(true);
        }
        mainGrid.add(btn.btn, i, j);
    }

    public GridPane GetmainGrid() {
        return mainGrid;
    }

    public GridPane GetrootGrid() {
        return rootGrid;
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

        if (mainGrid.getChildren().size() == 0) {
            for (Triplet<Integer, Integer, Case> elem : ((PlateauNormal) o).getLstCase()) {

                addButton(elem.getValue0(), elem.getValue1(), elem.getValue2(), (PlateauNormal) o);
                resetBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        PlateauNormal tmp = (PlateauNormal) o;

                        mainGrid.getChildren().clear();
                        tmp.Initialisation((int) sliderNbCase.getValue(), (int) sliderNbBomb.getValue());
                        mainGrid.setDisable(false);
                        messageLabel.setText("");
                        timerLabel.setText("");
                    }
                });
            }
        }

        Plateau plt = (Plateau) o;
        if (plt.getLbMessage() != "") {
            messageLabel.setText(plt.getLbMessage());
            mainGrid.setDisable(true);
        }
        timerLabel.setText(plt.getLbTimer());
    }

    
    

}
