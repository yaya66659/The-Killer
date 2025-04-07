package com.example.killer.yann.fr;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public final class Tour2 extends Partie implements Parcelable {

    public Tour2(int valeurAttaque, Humain[] h, IA[] ia, int numeroTour, String pseudo) {
        super(h.length, ia.length, pseudo);
        mJoueurH = h;
        mJoueurIA = ia;
        mValeurAttaque = valeurAttaque;
        mNumTourJoueur = numeroTour;

    }


    protected Tour2(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tour2> CREATOR = new Creator<Tour2>() {
        @Override
        public Tour2 createFromParcel(Parcel in) {
            return new Tour2(in);
        }

        @Override
        public Tour2[] newArray(int size) {
            return new Tour2[size];
        }
    };

    public int nbrDValeurAttaque(Des[] des) {

        int cmpt = 0;

        for (Des d : des) {

            if (d.getValeur() == mValeurAttaque) {
                cmpt++;
            }

        }

        return cmpt;
    }

    ;


    public static boolean choixEnnemiOK(int choix) {

        boolean choixOK = false;
        switch (humainOuIA(mNumTourJoueur)) {
            case HUMAIN:

                if (choix != mJoueurH[mNumTourJoueur - 1].
                        getNumero()) {
                    for (Humain humain : mJoueurH) {
                        if (choix == humain.getNumero() && humain.getVie() > 0) {
                            choixOK = true;
                            return choixOK;
                        } else
                            choixOK = false;
                    }
                    for (IA ia : mJoueurIA) {
                        if (choix == ia.getNumero() && ia.getVie() > 0) {
                            choixOK = true;
                            return choixOK;
                        } else
                            choixOK = false;

                    }
                }

                return choixOK;
            case IA:

                if (choix != mJoueurIA[mNumTourJoueur - 1 - mJoueurH.length].
                        getNumero()) {
                    for (Humain humain : mJoueurH) {
                        if (choix == humain.getNumero() && humain.getVie() > 0) {
                            choixOK = true;
                            return choixOK;
                        } else
                            choixOK = false;
                    }
                    for (IA ia : mJoueurIA) {
                        if (choix == ia.getNumero() && ia.getVie() > 0) {
                            choixOK = true;
                            return choixOK;
                        } else
                            choixOK = false;

                    }
                }

                return choixOK;


            default:
                return false;

        }

    }

    public static boolean choixNumEnemi(int choixEnemi) {

        switch (humainOuIA(mNumTourJoueur)) {

            case HUMAIN:
                if (choixEnnemiOK(choixEnemi)) {
                    mJoueurH[mNumTourJoueur - 1].setNumeroCible(choixEnemi);
                    return true;
                } else {
                    return false;
                }


            case IA:
                int choixIAEnemi;


                do {

                    Random RchoiIaEnemie = new Random();

                    choixIAEnemi = RchoiIaEnemie.nextInt((nbrJoueuEnVie() + 1));
                    if (choixEnnemiOK(choixIAEnemi))
                        break;

                } while (true);

                mJoueurIA[mNumTourJoueur - 1 - mJoueurH.length].setNumeroCible(choixIAEnemi);
                return true;


            default:
                return false;

        }

}

public void GardeDeValeurAttaque(Des[] des){

    
    for (Des d : des) {

        if(d.getValeur() == mValeurAttaque)
        {
          

            switch (humainOuIA(mNumTourJoueur)) {
                case HUMAIN:

                    mJoueurH[mNumTourJoueur-1].setDegats(mJoueurH[mNumTourJoueur-1].getDegats()+ d.getValeur());

                    mJoueurH[mNumTourJoueur-1].mDes[d.getnumero()-1].setValeur(0);
                    
                    break;
                    case IA:

                    mJoueurIA[mNumTourJoueur-1-mJoueurH.length].setDegats(mJoueurIA[mNumTourJoueur-1-mJoueurH.length].
                    getDegats()+ d.getValeur());
                    
                    mJoueurIA[mNumTourJoueur-1-mJoueurH.length].mDes[d.getnumero()-1].setValeur(0);
                    

                    break;
            
                default:

                    break;
            }
        }
                
        
    }

   

}

private boolean resteDaLancer(Des[] D)
{
    boolean reste = false;
        for (Des des : D) {

            if(des.getValeur() == 0 )
            {
                reste = false;
                
            }
            else
            {
                reste = true; 
               break; 
            }
            
           
            
        }

        return reste;

}

private int nbrDesDeValeurAttaque(Des[] des)
{
    int cmpt = 0;
   for(int i = 0 ; i<des.length;i++)

   {
        if(des[i].getValeur() == mValeurAttaque)
            cmpt++;
        
    }

    return cmpt;
}

public boolean attaqueDejoueur(int numeroCible)
{

    switch (humainOuIA(mNumTourJoueur)) {
        case HUMAIN:
        //do{





            

           /* if(nbrDesDeValeurAttaque(mJoueurH[mNumTourJoueur-1].mDes)<1)
            {
               return false;
            }*/





       // }while(resteDaLancer(mJoueurH[mNumTourJoueur-1].mDes));




        switch(humainOuIA(numeroCible)) {
            case HUMAIN:
                mJoueurH[numeroCible-1].setVie(mJoueurH[numeroCible-1].getVie() - mJoueurH[mNumTourJoueur-1].getDegats());
                return true;


                case IA:

                
                mJoueurIA[numeroCible-1-mJoueurH.length].setVie(mJoueurIA[numeroCible-1-mJoueurH.length].getVie()-mJoueurH[mNumTourJoueur-1].getDegats());
                return true;

            default:
                    return false;

        }
  case IA:

            

            do{

                mJoueurIA[mNumTourJoueur-1-mJoueurH.
                length].lancerlesD();

               
                if(nbrDesDeValeurAttaque(mJoueurIA[mNumTourJoueur-1-mJoueurH.length].mDes) <1)
                {
               break;


                }
                else
                {

                    GardeDeValeurAttaque(mJoueurIA[mNumTourJoueur-1-mJoueurH.length].mDes);

                }

            }while(resteDaLancer(mJoueurIA[mNumTourJoueur-1-mJoueurH.length].mDes) );


            

            switch (humainOuIA(numeroCible)) {
                case HUMAIN:
                    mJoueurH[numeroCible-1].setVie(mJoueurH[numeroCible-1].getVie() - 
            mJoueurIA[mNumTourJoueur-1-mJoueurH.length].getDegats());

                      return true;
                    

                    case IA:
                    mJoueurIA[numeroCible-1-mJoueurH.length].setVie(mJoueurIA[numeroCible-1-mJoueurH.length].getVie()-mJoueurIA[mNumTourJoueur-1-mJoueurH.length].getDegats());
                         return true;

                    
                default:
                    return false;

            }
                  
    

    
        default:
                return false;

    }
}


    private static int mValeurAttaque;
    
}
