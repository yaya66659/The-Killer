package com.example.killer.yann.fr;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public  class Partie implements Parcelable {

    protected Partie(Parcel in) {
        mNbrHumain = in.readInt();
        mNbrIA = in.readInt();
    }

    public static final Creator<Partie> CREATOR = new Creator<Partie>() {
        @Override
        public Partie createFromParcel(Parcel in) {
            return new Partie(in);
        }

        @Override
        public Partie[] newArray(int size) {
            return new Partie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(mNbrHumain);
        parcel.writeInt(mNbrIA);
    }

    public enum player{
        NULL,
        HUMAIN,
        IA;
    }


    
    
    


   public Partie(int nbrH, int nbrIA, String pseudo)
    {
        this.mNbrHumain = nbrH;
        this.mNbrIA = nbrIA;
        mNumTourJoueur = 1;
        this.numeroHumainDernierEnVie = 0;
        mJoueurH = new Humain[this.mNbrHumain];
        for (int i = 0 ; i< mJoueurH.length; i++) {
            if(i == 0)
                mJoueurH[i] = new Humain(i+1, pseudo);
            else
                mJoueurH[i] = new Humain(i+1, "Invités");
            
        }
        mJoueurIA = new IA[this.mNbrIA];
        for(int i  = 0, j = nbrH+1; i<mJoueurIA.length; i++)
        {

            mJoueurIA[i] = new IA(j++);
        }

        
        
    }

    public static  int nbrJoueuEnVie()
    {
        int comptJoueur = 0;

        for (Humain humain : mJoueurH) {
            if(humain.getVie()>0)
            {
                comptJoueur++;
            }

        }
        for (IA ia : mJoueurIA) {

            if(ia.getVie()>0)
            {
                comptJoueur++;
            }

            
        }
        
return comptJoueur;

    }

    private int[] tableauDeNumeroJoueurEnVie()
    {
       
        int[] tabJoueurEnVie = new int[nbrJoueuEnVie()];

        for(int i = 0, j= 0 ; i<mNbrHumain+mNbrIA; i++){

            switch (humainOuIA(i+1)) {
                case HUMAIN:
                if(mJoueurH[i].getVie() > 0)
                {
                    tabJoueurEnVie[j++] = mJoueurH[i].getNumero();
                   
                }
              

               
               
                    
                    break;

                    case IA:

                    if(mJoueurIA[i-mJoueurH.length].getVie() > 0)
                    {
                        tabJoueurEnVie[j++] = mJoueurIA[i-mJoueurH.length].getNumero();
                       
                    }

                    
                    
    
                break;
            
                default:

                    break;
            }
        }

            return tabJoueurEnVie;
        }
        private boolean dansLesJoueurEnVie(int numeroJoueur ,int[] JenVie){

            for (int i : JenVie) {
                if(numeroJoueur == i)
                    return true;
                
            }

            return false;
        }

        private int numeroJoueurEnVieSuivant(int numeroJoueurActuelle, int[] tabJoueurEnVie){

            if(numeroJoueurActuelle != tabJoueurEnVie[nbrJoueuEnVie()-1]){
                for (int i = 0 ; i <tabJoueurEnVie.length; i++) {
                    if(tabJoueurEnVie[i] == numeroJoueurActuelle )
                    {
                            return tabJoueurEnVie[i+1];
                             }
            }
        }
           else if (!dansLesJoueurEnVie(numeroJoueurActuelle, tabJoueurEnVie) && dansLesJoueurEnVie(numeroJoueurActuelle+1, tabJoueurEnVie))
           {
                
                
            return tabJoueurEnVie[numeroJoueurActuelle+1];         
                
                

           }
           
           
             return tabJoueurEnVie[0];        

            
          
        }
        public void tourSuivant(){
                                                             
               
   mNumTourJoueur = numeroJoueurEnVieSuivant(mNumTourJoueur, tableauDeNumeroJoueurEnVie());                                                                                    

            }
       

             
       

    public static  player humainOuIA(int numero)
    {
        player p  = player.NULL;
        
        for(int i = 0; i< mJoueurH.length; i++)
        {
            if(numero == mJoueurH[i].getNumero() )
              p  = player.HUMAIN;
        }
      for(int i = 0; i< mJoueurIA.length; i++)
      {
        if(mJoueurIA[i].getNumero() == numero)
         p = player.IA;
      }
         
 return p;
          }


    public  String afficherStats(){
        String statut = "";
        for (Humain humain :mJoueurH) {
            statut = statut.concat(humain.afficherStat() + " \n");
        }
        for (IA ia : mJoueurIA) {
           statut = statut.concat(ia.afficherStat() + "\n");
        }

        return statut;

    }

    public  String afficherStats(int numTour)
    {
        String statu = "";
        switch (humainOuIA(numTour)) {
            case HUMAIN:

            for (Humain humain : mJoueurH) {
                if(humain.getNumero() != mNumTourJoueur)
                 statu =  statu.concat(humain.afficherStat() +"\n");
            }
            for (IA ia : mJoueurIA) {

               statu = statu.concat(ia.afficherStat()+"\n");
                
            }

                
                break;
                case IA:

                for (Humain humain : mJoueurH) {
                   
                   statu = statu.concat(humain.afficherStat()+"\n");
                }
                for (IA ia : mJoueurIA) {

                    if(ia.getNumero() != mNumTourJoueur)
                   statu = statu.concat(ia.afficherStat()+"\n");

                }


    
    break;

        
            default:
                break;
        }

        return statu;
    }

    public boolean resteJoueurHumainEnVie()
    {
        for (Humain humain : mJoueurH) {
            if(humain.getVie() > 0)
             return true;
        }

        return false;
    }

    public void resetD(){

        switch (humainOuIA(mNumTourJoueur)) {
            case HUMAIN:

            mJoueurH[mNumTourJoueur-1].resetD();
                
                break;
        
                case IA:
                
                mJoueurIA[mNumTourJoueur-1-mJoueurH.length].resetD();
    
                 break;
            default:
                break;
        }

    }

    public void resetAllDegats(){

        for (Humain humain : this.mJoueurH) {
            humain.resetDegats();
        }
        for (IA ia : mJoueurIA) {
            ia.resetDegats();
        }

    }
    public void resetAllScore(){
        for (Humain h: mJoueurH) {
            h.resetScore();
        }

        for (IA ia: this.mJoueurIA) {
            ia.resetScore();
        }
    }



    public  int getNumTourJoueur(){return mNumTourJoueur;}
    public  void setNumTourJoueur(int numTour){mNumTourJoueur = numTour;}

   
    public Humain getJoueurH(int numero){return mJoueurH[numero-1];}
    public IA getJoueurIA(int numero){return mJoueurIA[numero-1];}
    
    
    public Humain[] getJoueurHTab(){return mJoueurH;}
    
    public IA[] getJoueurIATab(){return mJoueurIA;}

    public int getNumeroHumainDernierEnVie(){return this.numeroHumainDernierEnVie;}
    public void setNumeroHumainDernierEnVie(int numero){this.numeroHumainDernierEnVie = numero;}

  
       



   protected int mNbrHumain;
   protected int mNbrIA;
   protected  int numeroHumainDernierEnVie = 0;
   protected static int mNumTourJoueur;
   protected static Humain[] mJoueurH;
   protected static IA[] mJoueurIA;
    
}
