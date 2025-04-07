package com.example.killer.yann.fr;

import java.util.Random;

public class Des {

    public Des(int numero)
    {
        
        this.mNumero = numero;
        this.mValeur = -1;
    }
   
    public void rouler()
    {
        Random r = new Random();
        this.mValeur = r.nextInt(6);
        this.mValeur += 1;
    }





   public void reset()
   {
    this.mValeur = -1;
   }




    public void setnumero(int numero){this.mNumero = numero;}
    public int getnumero(){return this.mNumero;}

    public void setValeur(int valeur){this.mValeur = valeur;}
    public int getValeur(){return this.mValeur;}

    private int mNumero;
    private int mValeur;

    private final int MIN = 1;
    private final int MAX = 7;
    
}
