package com.example.killer.yann.fr;


import java.util.Random;

public class IA extends Joueur {

        public IA(int numero){
        
        this.mDegats =0 ;
        this.mVie =VIE;
        this.mNumero = numero;
        this.mDes = new Des[NBR_DES];
        for(int i = 0; i<this.mDes.length; i++)
        {
            this.mDes[i] = new Des(i+1);
        }


    }



    @Override
    public void  lancerlesD() {
        for (Des des : mDes) {
            if(des.getValeur() != 0)
            {
                des.rouler();
            }

        }
       
        
    }
@Override
public void resetDegats() {
this.setDegats(0);
}

    @Override
public void resetD() {

        for (Des mDe : this.mDes) {
            mDe.reset();
        }
        }

    @Override
    public void resetScore() {
        this.mScore = 0;
    }

    @Override
    public String afficherStat() {
            String stat ="";

        if(this.mVie >0)
        {
            stat = "" + this.mNumero + " - IA" + this.mNumero + " VIE : " + this.mVie;
        }

        else
        {
            stat = "" + this.mNumero + " - IA "+ this.mNumero + " KILLER";
        }
        return stat;
    }

    
    @Override
    public void garderD(int numeroDgarde) {


        
        

        Random R = new Random();

            this.mScore = R.nextInt( 26);
            this.mScore += 5;






    }



   
}
