/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.javatuples.Triplet;

/**
 *
 * @author Epulapp
 */
public class PlateauNormal extends Plateau {

    private ArrayList<Triplet<Integer, Integer, Case>> lstCase;
    

    public PlateauNormal() {
        lstCase = new ArrayList<Triplet<Integer, Integer, Case>>();
        setLbMessage("");
        setLbTimer("0");
        NbDevoile = 0;
    }

    @Override
    public void Initialisation(int difficulte, int nbbomb) {
        
        if(lstCase.size()!=0)
        {
            lstCase.clear();
            NbDevoile = 0;
            setLbMessage("");
            setLbTimer("0");
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
        @Override
        public void run() {
            int tmp = Integer.parseInt(getLbTimer());
             Platform.runLater(() -> setLbTimer(String.valueOf(tmp+1)));  
            ;
        }},1000, 1000);
        if(nbbomb==0) nbbomb=10;
        if(difficulte==0) nbbomb=10;
       
        

        ArrayList<Boolean> bomblst = new ArrayList<Boolean>();
        for (int i = 0; i < difficulte * difficulte; i++) {
            if (i < nbbomb) {
                bomblst.add(Boolean.TRUE);
            } else {
                bomblst.add(Boolean.FALSE);
            }
        }
        Collections.shuffle(bomblst);
        int indice = 0;
        for (int i = 0; i < difficulte; i++) {
            for (int j = 0; j < difficulte; j++) {
                getLstCase().add(new Triplet<Integer, Integer, Case>(i, j, new Case(bomblst.get(indice),this)));
                indice++;
            }

        }
        for (Triplet<Integer, Integer, Case> elem : lstCase) {
            elem.getValue2().SetVoisin(GetVoisins(elem));
            elem.getValue2().setNbBombe(GetNnBomb(elem));

        }
        setChanged();
        notifyObservers();
        super.Initialisation(difficulte, nbbomb); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    /**
     * @return the lstCase
     */
    public ArrayList<Triplet<Integer, Integer, Case>> getLstCase() {
        return lstCase;
    }

   

    public ArrayList<Case> GetVoisins(Triplet<Integer, Integer, Case> trlCase) {
        ArrayList<Case> lstVoisin = new ArrayList<>();
        for (Triplet<Integer, Integer, Case> elem : lstCase) {

            if ((elem.getValue0() <= (trlCase.getValue0() + 1)) && (elem.getValue0() >= (trlCase.getValue0() - 1)) && (elem.getValue1() >= (trlCase.getValue1() - 1)) && (elem.getValue1() <= (trlCase.getValue1() + 1))) {

                lstVoisin.add(elem.getValue2());

            }

        }
        return lstVoisin;
    }

    public int GetNnBomb(Triplet<Integer, Integer, Case> trlCase) {
        int nbBomb = 0;
        for (Triplet<Integer, Integer, Case> elem : lstCase) {

            if ((elem.getValue0() <= (trlCase.getValue0() + 1)) && (elem.getValue0() >= (trlCase.getValue0() - 1)) && (elem.getValue1() >= (trlCase.getValue1() - 1)) && (elem.getValue1() <= (trlCase.getValue1() + 1))) {
                if (elem.getValue2().isIsBomb()) {
                    nbBomb++;
                }
            }

        }
        return nbBomb;
    }

    @Override
    public void IncremNBDevoile() {
        NbDevoile++;
        if (NbDevoile == lstCase.size() - this.getNBBomb()) {
            this.setLbMessage("Gagné");
           this.resetTimer();
        }
    }
    
     

}
