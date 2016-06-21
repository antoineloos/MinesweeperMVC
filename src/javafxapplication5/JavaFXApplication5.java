/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import Models.Plateau;
import Models.PlateauNormal;
import Views.PlateauView;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Epulapp
 */
public class JavaFXApplication5 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        StackPane root = new StackPane();
        PlateauView PltView = new PlateauView();
        Plateau Plt = new PlateauNormal();
        Plt.addObserver(PltView);
        root.getChildren().add(PltView.GetrootGrid());
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Demineur");
        primaryStage.setScene(scene);
        primaryStage.show();
        Plt.Initialisation(10,10);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
