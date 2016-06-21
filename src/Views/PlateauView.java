package Views;

import Controler.CustomEventHandler;
import Models.Case;
import Models.Plateau;

import Models.PlateauNormal;
import com.jfoenix.controls.JFXButton;

import java.util.Observer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.javatuples.Triplet;

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
    
    public PlateauView() {
        Font myFont = new Font("Serif", 30);
        rootGrid = new GridPane();
        mainGrid = new GridPane();
        resetBtn = new JFXButton();
        resetBtn.setText("Recommencer");
        String style=" -fx-border-color: white; " + "-fx-border-width: 3;"+"-fx-background-color: #61E79E;"+"-fx-spacing: 5px;"+"-fx-text-fill: white;";
        resetBtn.setStyle(style);
        resetBtn.setPrefHeight(50);
        resetBtn.setPrefWidth(250);
        resetBtn.setFont(myFont);
        messageLabel = new Label("");
        timerLabel = new Label("");
        timerLabel.setFont(myFont);
        rootGrid.add(mainGrid, 0, 0);
        rootGrid.add(resetBtn, 0, 1);
        rootGrid.add(messageLabel, 0, 2);
        rootGrid.add(timerLabel, 1,1);
        
        messageLabel.setFont(myFont);
        
    }
    
    
    
    public void addButton(int i , int j , Case courantCase , PlateauNormal plt )
    {
                ObsButton btn = new ObsButton(courantCase, plt);
         
                
                courantCase.addObserver(btn);
                
                System.out.println(plt.getLbMessage());
                if(plt.getLbMessage()!="")
                {
                    btn.setDisable(true);
                }
                mainGrid.add(btn.btn,i,j);
    }
    
    

    public GridPane GetmainGrid(){return mainGrid;}
    public GridPane GetrootGrid(){return rootGrid;}
    
    
    @Override
    public void update(java.util.Observable o, Object arg) {
       
           if(mainGrid.getChildren().size()==0)
           {
                for(Triplet<Integer,Integer,Case> elem : ((PlateauNormal)o).getLstCase())
                {

                        addButton(elem.getValue0(), elem.getValue1(), elem.getValue2(),(PlateauNormal)o);
                        resetBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
                        {
                            @Override
                                    public void handle(MouseEvent event) 
                                    {
                                       PlateauNormal tmp =  (PlateauNormal)o;
                                       
                                       mainGrid.getChildren().clear();
                                       tmp.Initialisation(10,10);
                                       mainGrid.setDisable(false);
                                       messageLabel.setText("");
                                       timerLabel.setText("");
                                    }
                        });
                }
           }
           
           
            Plateau plt = (Plateau)o;
            if(plt.getLbMessage()!="")
            {
                messageLabel.setText(plt.getLbMessage());
                mainGrid.setDisable(true);
                
            }
            
            timerLabel.setText(plt.getLbTimer());
            
            
        }
      
    }

 
    

