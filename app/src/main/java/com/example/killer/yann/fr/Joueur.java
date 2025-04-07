package com.example.killer.yann.fr;

public abstract class Joueur {

    public abstract void lancerlesD();
    public abstract void garderD(int numeroDgarde);



   
    public abstract String afficherStat();

    //public abstract void afficherDes();
    //public abstract int afficherScores();
    public abstract void resetD();
    public abstract void resetDegats();
     public abstract void resetScore();
    
    public void setNumero(int numero){this.mNumero = numero;}
    public int getNumero(){return this.mNumero ;}

    public void setVie(int vie){this.mVie = vie;}
    public int getVie(){return this.mVie ;}

    public void setDegats(int Degats){this.mDegats = Degats;}
    public int getDegats(){return this.mDegats ;}

    public void setScore(int Score){this.mScore = Score;}
    public int getScore(){return this.mScore ;}

    public void setNumeroCible(int cible){this.mNumeroDeLaCible = cible;}
    public int getNuleroCible(){return this.mNumeroDeLaCible;}

    public void setPseudo(String pseudo){this.mPseudo = pseudo;}
    public String getPseudo(){return this.mPseudo;}

    public Des[] getDesTab(){return this.mDes;}
    public Des getDes(int numero){return this.mDes[numero];}
    protected String mPseudo;
    protected int mNumero;


    protected int mScore;
    protected int mVie;
    protected int mDegats;
    protected int mNumeroDeLaCible;
   protected Des[] mDes;
   protected  final int VIE = 30;
   protected final int NBR_DES = 5;
   protected static int mNbrInstance;
   
   
    
}
