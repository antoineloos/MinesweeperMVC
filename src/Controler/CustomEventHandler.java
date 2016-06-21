/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Models.Case;
import Models.Plateau;
import Models.PlateauNormal;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.javatuples.Triplet;

/**
 *
 * @author Epulapp
 */
public class CustomEventHandler implements EventHandler<MouseEvent> {

    public Case cs ;
    
    
    public CustomEventHandler(Case cas)
    {
        this.cs = cas;
    }
    @Override
    public void handle(MouseEvent event) {
        
                        if(event.getButton()==MouseButton.SECONDARY)
                        {

                            
                            JFXButton courantbtn = (JFXButton)event.getSource();
                           
                            
                            if(cs.isIsHidden()==true)
                            {
                               if(cs.isIsFlagged()!=true)
                               {
                                    cs.setIsFlagged(true);
                                    
                               }
                               else {
                                   cs.setIsHidden(true);
                                   cs.setIsFlagged(false);
                               }
                            }
                            
                            
                            
                        }
                        else if (event.getButton()==MouseButton.PRIMARY)
                        {

                            
                            JFXButton courantbtn = (JFXButton)event.getSource();
                            
                            
                            
                            if(cs.isIsHidden()==true) //Ancien que j'ai remplacé
                            {
                                cs.DevoileCase(cs);  
                            }
                            
                            
                        }
                    
    }
    
}
