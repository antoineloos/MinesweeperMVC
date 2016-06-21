/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import org.javatuples.Triplet;




/**
 *
 * @author Epulapp
 */
public abstract class Plateau extends Observable {
   public Integer NbDevoile;
   private String LbMessage;
   private Integer NBBomb;
   Timer timer;
   public  void Initialisation(int difficulte,int nbbomb)
   {
       this.setNBBomb((Integer) nbbomb);
       
   }
    
    /**
     * @return the LbMessage
     */
    public String getLbMessage() {
        return LbMessage;
    }
    
    protected String LbTimer;
    
    abstract public void IncremNBDevoile();
    
    
    /**
     * @param LbMessage the LbMessage to set
     */
    public void setLbMessage(String LbMessage) {
        this.LbMessage = LbMessage;
        setChanged();
        notifyObservers();
    }

    /**
     * @return the NBBomb
     */
    public Integer getNBBomb() {
        return NBBomb;
    }

    /**
     * @param NBBomb the NBBomb to set
     */
    public void setNBBomb(Integer NBBomb) {
        this.NBBomb = NBBomb;
    }
    
     public void resetTimer()
    
    {
            this.timer.cancel();
            this.LbTimer =  "0";
    }  
    
    /**
     * @return the LbTimer
     */
    public String getLbTimer() {
        return LbTimer;
    }

    /**
     * @param LbTimer the LbTimer to set
     */
    public void setLbTimer(String LbTimer) {
        this.LbTimer = LbTimer;
        setChanged();
        notifyObservers();
    }
   
   
}
