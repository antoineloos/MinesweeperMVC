/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Epulapp
 */
public class Case extends Observable   {
    private List<Case> lstVoisin;
    private Plateau plateau;

    private boolean IsBomb;
    private boolean IsHidden;
    private boolean IsFlagged;
    private String Urlimg;
    private int nbBombe;
    
    public Case( Boolean isBb , Plateau plt)
    {
        this.IsHidden = true;
        this.IsFlagged = false;
        plateau = plt;
        this.setIsBomb(isBb);
        
    }
    public void SetVoisin(ArrayList<Case> lstc)
    {
        setLstVoisin(lstc);
    }

    /**
     * @return the lstVoisin
     */
    public List<Case> getLstVoisin() {
        return lstVoisin;
    }

    /**
     * @param lstVoisin the lstVoisin to set
     */
    public void setLstVoisin(List<Case> lstVoisin) {
        this.lstVoisin = lstVoisin;
    }

    
    public void DevoileCase(Case cs) {
        if (cs.getNbBombe() != 0) {
            cs.setIsHidden(false);
            plateau.IncremNBDevoile();
        }else if(cs.isIsBomb() == true){
            cs.setIsHidden(false);
            plateau.setLbMessage("perdu");
          plateau.resetTimer();
        }
        else {
            for (Case elem : cs.getLstVoisin()) {

                if (elem.isIsBomb() == false && elem.isIsHidden() == true) {
                    elem.setIsHidden(false);
                    elem.setIsFlagged(false);
                    if (elem.getNbBombe() == 0) {
                        DevoileCase(elem);
                    }

                    plateau.IncremNBDevoile();
                }

            }
        }

        
       
    }
    

    /**
     * @return the IsBomb
     */
    public boolean isIsBomb() {
        return IsBomb;
    }

    /**
     * @param IsBomb the IsBomb to set
     */
    public void setIsBomb(boolean IsBomb) {
        this.IsBomb = IsBomb;
    }

    /**
     * @return the Urlimg
     */
    public String getUrlimg() {
        return Urlimg;
    }

    /**
     * @param Urlimg the Urlimg to set
     */
    public void setUrlimg(String Urlimg) {
        this.Urlimg = Urlimg;
    }
    
    public void setLstVoisin(ArrayList<Case> lstvs)
    {
        this.lstVoisin = lstvs;
    }

    /**
     * @return the nbBombe
     */
    public int getNbBombe() {
        return nbBombe;
    }

    /**
     * @param nbBombe the nbBombe to set
     */
    public void setNbBombe(int nbBombe) {
        if(!this.isIsBomb()){
        this.nbBombe = nbBombe;}
        
    }

    /**
     * @return the IsHidden
     */
    public boolean isIsHidden() {
        return IsHidden;
        
    }

    /**
     * @param IsHidden the IsHidden to set
     */
    public void setIsHidden(boolean IsHidden) {
        
        this.IsHidden = IsHidden;
        if(IsBomb && IsHidden==false)
            setUrlimg("Resources/bombe.png");
        else setUrlimg("");
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the IsFlagged
     */
    public boolean isIsFlagged() {
        return IsFlagged;
    }

    /**
     * @param IsFlagged the IsFlagged to set
     */
    public void setIsFlagged(boolean IsFlagged) {
        this.IsFlagged = IsFlagged;
        if(IsHidden ==true && IsFlagged==true)
        {
            setUrlimg("Resources/flag.png");
        }
        else if(IsHidden ==true && IsFlagged==false)setUrlimg("");
        this.setChanged();
        this.notifyObservers();
    }
    
}

