package com.example.killer.yann.fr;


import android.os.Parcel;
import android.os.Parcelable;

public final class Tour1 extends Partie implements Parcelable {

    public Tour1(int nbrJH, int nbrJIA, String pseudo){
        super(nbrJH, nbrJIA, pseudo);
        
        
        
        this.mValeurAttaque = 0;
    }
   
   
   
   

    /************************************************ */
    protected Tour1(Parcel in) {
        super(in);
        mValeurAttaque = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mValeurAttaque);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tour1> CREATOR = new Creator<Tour1>() {
        @Override
        public Tour1 createFromParcel(Parcel in) {
            return new Tour1(in);
        }

        @Override
        public Tour1[] newArray(int size) {
            return new Tour1[size];
        }
    };

    /***********METHODE*********************************** */
   
   
                                                                           
   
   
   
   
                           
   private boolean plusProchede11(int score)
   {
    int res11  = 0;
    int res24 = 0;

    res11 = score -11;
    res24 = 24-score;

    if(res11 < res24)
    {
        return true;
    }
    else
    {
        return false;

    }

   
   
   
   }
   //retourne valeur attaque
    public int calculeAttaque(int score){
        int res11  = 0;
        int res24  = 0;

        int retour = 0;




        if(score == 11 )
        {

            retour = 11;
        }
        else if (score == 24)
        {

            retour = 24;
        }
        else if(score > 11 && score < 24 )
        {

            if(plusProchede11(score)){
            res11 = score - 11;
            switch (humainOuIA(mNumTourJoueur)) {
                case HUMAIN:
               

                mJoueurH[mNumTourJoueur-1].setVie(mJoueurH[mNumTourJoueur-1].getVie()-res11);
                    retour =  res11;
                break;



                    
                   
                    case IA:


                      mJoueurIA[mNumTourJoueur-1-mJoueurH.length].setVie(mJoueurIA[mNumTourJoueur-1-mJoueurH.length].getVie()-res11);
                        retour = res11;
                      break;


    
                    

                    case NULL:


                    break;
            
                default:
                    break;
            }
            
        }
        else{

            res24 = 24- score;

            switch (humainOuIA(mNumTourJoueur)) {
                case HUMAIN:
               

                mJoueurH[mNumTourJoueur-1].setVie
           (mJoueurH[mNumTourJoueur-1].getVie()
           -res24);
                    retour = res24;
                break;
                    
                   
                    case IA:

                      mJoueurIA[mNumTourJoueur-1-mJoueurH.length].
           setVie(mJoueurIA[mNumTourJoueur-1-mJoueurH.length].
           getVie()-res24);
                        retour = res24;
                      break;
                    

            
                default:
                    break;
            }

        }
       }
       else if(score <11)
       {
        int res  = 11-score;

        switch(humainOuIA(mNumTourJoueur))
        {
            case HUMAIN:

            this.mValeurAttaque = res;
                retour = res;
            break;
            case IA :

                this.mValeurAttaque = res;
                retour = res;
            break;

            default:
            break;
        }


       }
       else if (score > 24){
        int res = score -24;

        switch(humainOuIA(mNumTourJoueur))
        {
            case HUMAIN:

             this.mValeurAttaque = res;
                retour = res;
            break;
            case IA :

            this.mValeurAttaque = res;
                retour = res;
            break;


        }


       }

        return retour;

    }

    
 
 
 
 
 
 
   
    public int gagneUnD6DeVie(){

        
       Des DdeVie = new Des(1);


        switch (humainOuIA(mNumTourJoueur)) {
            case HUMAIN:


             DdeVie.rouler();
            mJoueurH[mNumTourJoueur-1].setVie(mJoueurH[mNumTourJoueur-1].getVie()+DdeVie.getValeur());
                    return DdeVie.getValeur();


                case IA:

                DdeVie.rouler();
                mJoueurIA[mNumTourJoueur-1-mJoueurH.length].setVie(mJoueurIA[mNumTourJoueur-1-mJoueurH.length].getVie()+DdeVie.getValeur());
                    return DdeVie.getValeur();

     



        
            default:
                return 0;

        }
     }

     public boolean desAmoin1(){
       switch (humainOuIA(mNumTourJoueur))
       {
           case HUMAIN:
               for (Des d:mJoueurH[mNumTourJoueur-1].getDesTab())
               {
                   if(d.getValeur() == -1)
                       return true;
               }

               break;

           case IA:

               for (Des d:mJoueurIA[mNumTourJoueur-1-mNbrHumain].getDesTab())
               {
                   if(d.getValeur() == -1)
                       return true;


               }


               break;
       }

         return false;

     }


     public void setValeurAttaque(int valeurAttaque){this.mValeurAttaque = valeurAttaque;}
     public int getValeurAttaque(){return this.mValeurAttaque;}
    
private int mValeurAttaque;
   
}
