package com.example.killer.yann.fr;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Humain extends Joueur{

    @Override
    public void resetScore() {
        this.mScore = 0;
    }

    public Humain(int numero, String pseudo){

        this.mPseudo = pseudo;
        this.mVie = VIE;
        this.mDegats=0;
        this.mNumero = numero;
        this.mNumeroDeLaCible = 0;
        this.mDes = new Des[NBR_DES];
        
        for(int i = 0; i<this.mDes.length; i++)
        {
            this.mDes[i] = new Des(i+1);
        }

    }

@Override
public String afficherStat() {

    if(this.mVie >0)
        return this.mNumero + " - " + this.mPseudo + " " + this.mNumero + " VIE : " + this.mVie;
    else
    {
        return this.mNumero + " - " + this.mPseudo + " " + this.mNumero + " KILLER";
    }

}


        
    


    public boolean DgarderOK(int numDes)
    {
        boolean Dok = false;
        if(numDes != 0)
        {
            for(int i = 0; i<this.mDes.length; i++)
            {
                if(this.mDes[i].getValeur() != 0)
                {
                    if(this.mDes[i].getnumero() == numDes)
                    {
                        Dok = true;
                        break;
                    }               
                   else 
                     Dok = false;
               
                }
                else 
                    Dok = false;
            }

        }
        else
            Dok = true;
      
       return Dok;
    }



    
    @Override
    public void resetDegats() {
        this.setDegats(0);
    }
    
    
    
    

      @Override
    public void garderD(int numeroDgarde) {

        if(DgarderOK(numeroDgarde))
        {
            setScore(this.mDes[numeroDgarde-1].getValeur());
            this.mDes[numeroDgarde-1].setValeur(0);

        }
        else {

        }



       
        





    }


    @Override
    public void lancerlesD() {

        for (Des des : mDes) {
            if(des.getValeur() != 0)
            {
                des.rouler();
            }

        }
       
    }
    @Override
    public void resetD() {
        for(int i = 0; i<this.mDes.length; i++)
        {
            this.mDes[i].reset();
        }
    }
    
   
}
