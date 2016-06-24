/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controler.CustomEventHandler;
import Models.Case;
import Models.Plateau;
import Models.PlateauNormal;
import com.jfoenix.controls.JFXButton;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Epulapp
 */
public class ObsButton extends JFXButton implements Observer  {
    private String _color;
    public JFXButton btn;
    public ObsButton(Case courant, PlateauNormal plt, String color)
    {
        btn = new JFXButton();
        btn.setOnMouseClicked(new CustomEventHandler(courant));
        btn.setMinHeight(70);
        btn.setMinWidth(70);
        _color = color;
        String style=" -fx-border-color: white; " + "-fx-border-width: 3;"+"-fx-background-color: "+color+";";
        
        btn.setStyle(style);
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Case)
        {
        Case courantCase = (Case)o;
        String style=" -fx-border-color: white; " + "-fx-border-width: 3;";
         if(courantCase.isIsHidden()!= true)
         {
            if(courantCase.getNbBombe()!=0)
            {
               btn.setText(String.valueOf(courantCase.getNbBombe()));
            }
            
           
            style +="-fx-graphic: url('"+ courantCase.getUrlimg() +"');";
         }
         else
         {
             
             style += "-fx-background-color: "+_color+";";
             style +="-fx-graphic: url('"+ courantCase.getUrlimg() +"');";
         }
        
        btn.setStyle(style);
        }
    }
    
}
