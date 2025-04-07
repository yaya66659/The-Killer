package com.example.killer;

import static com.example.killer.yann.fr.Partie.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.killer.yann.fr.Des;
import com.example.killer.yann.fr.Partie;
import com.example.killer.yann.fr.Tour1;
import com.example.killer.yann.fr.Tour2;

public class Tour2Attaque extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour2_attaque);

        Bundle bInfoPartie = getIntent().getExtras();
        this.mPartie = bInfoPartie.getParcelable("partie");
        this.mTour1 = bInfoPartie.getParcelable("tour1");
        this.mTour2 = bInfoPartie.getParcelable("tour2");

        mRotationImageD = 0;

        this.mDesGarde = new Des[5];
        for (int i = 0; i<this.mDesGarde.length; i++) {
            this.mDesGarde[i]= new Des(i+1);
        }

        d1Garde = false;
        d2Garde = false;
        d3Garde =false;
        d4Garde =false;
        d5Garde =false;

        this.mNbrDValeurAttaqueGarder = 0;
        this.mNbrDgardeValAttaqueTotal = 0;

        this.mPhaseAttaqueActivity = this;

        this.mTextDegats = (TextView) findViewById(R.id.degats);


        this.mTextNumTourJoueur = (TextView) findViewById(R.id.numeroTour);
        this.mTextNumTourJoueur.setText(String.valueOf(this.mPartie.getNumTourJoueur()));

        this.mTextValeurAttaque = (TextView) findViewById(R.id.valeurAttaque);
        this.mTextValeurAttaque.setText(String.valueOf(this.mTour1.getValeurAttaque()));

        this.mLogoBack = (ImageView) findViewById(R.id.back);
        this.mLogoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupBackMain = new AlertDialog.Builder(mPhaseAttaqueActivity);
                popupBackMain.setTitle("QUITTER?");
                popupBackMain.setMessage(R.string.back);
                popupBackMain.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                Intent backMain = new Intent(getApplicationContext(), MainActivity.class);
                popupBackMain.setNegativeButton("QUITTER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(backMain);
                        finish();
                    }
                });
                popupBackMain.show();
            }
        });

        this.mInfoAttaque = (ImageView) findViewById(R.id.infoAttaque);
        this.mInfoAttaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupInfo = new AlertDialog.Builder(mPhaseAttaqueActivity);
                popupInfo.setTitle("INFO");
                popupInfo.setMessage(R.string.infoKiller2);
                popupInfo.show();

            }
        });

        this.mRegles = (ImageView) findViewById(R.id.regles);
        this.mRegles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupStat = new AlertDialog.Builder(mPhaseAttaqueActivity);
                popupStat.setTitle("REGLES");
                popupStat.setMessage(R.string.regles);
                popupStat.show();
            }
        });

        this.mStatutJoueur = (ImageView) findViewById(R.id.statJoueur);
        this.mStatutJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupStat = new AlertDialog.Builder(mPhaseAttaqueActivity);
                popupStat.setTitle("STATUT DES JOUEURS");
                popupStat.setMessage(mPartie.afficherStats());
                popupStat.show();
            }
        });

        this.mRegles = (ImageView) findViewById(R.id.regles);
        this.mRegles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupRegles = new AlertDialog.Builder(mPhaseAttaqueActivity);
                popupRegles.setTitle("REGLES");
                popupRegles.setMessage(R.string.regles);
                popupRegles.show();
            }
        });

        this.D1 = (ImageView) findViewById(R.id.d1);
        this.D1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               switch(humainOuIA(mPartie.getNumTourJoueur()))
               {
                   case HUMAIN:
                       if( mTour1.desAmoin1())
                       {
                           AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mPhaseAttaqueActivity);
                           popupDmoin1.setTitle("D1 IMPOSSIBLE A GARDER");
                           popupDmoin1.setMessage(R.string.Dmoin1);
                           popupDmoin1.show();
                       }
                       else
                       {
                           if(!d1Garde && mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(0).getValeur() == mTour1.getValeurAttaque())
                           {
                              mPartie.getJoueurH(mPartie.getNumTourJoueur()).setDegats( mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats() + mTour1.getValeurAttaque());
                               mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(0).setValeur(0);
                              mTextDegats.setText(String.valueOf(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats()));
                              D1.setImageResource(R.drawable.d_garde);
                              d1Garde = true;
                              mNbrDValeurAttaqueGarder++;
                              mNbrDgardeValAttaqueTotal++;
                           }


                       }


                       break;

                   case IA:

                       break;
               }
            }
        });


        this.D2 = (ImageView) findViewById(R.id.d2);
        this.D2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(humainOuIA(mPartie.getNumTourJoueur()))
                {
                    case HUMAIN:
                        if( mTour1.desAmoin1())
                        {
                            AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mPhaseAttaqueActivity);
                            popupDmoin1.setTitle("D2 IMPOSSIBLE A GARDER");
                            popupDmoin1.setMessage(R.string.Dmoin1);
                            popupDmoin1.show();
                        }
                        else
                        {
                            if(!d2Garde && mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(1).getValeur() == mTour1.getValeurAttaque())
                            {
                                mPartie.getJoueurH(mPartie.getNumTourJoueur()).setDegats( mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats() + mTour1.getValeurAttaque());
                                mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(1).setValeur(0);
                                mTextDegats.setText(String.valueOf(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats()));
                                D2.setImageResource(R.drawable.d_garde);
                                d2Garde = true;
                                mNbrDValeurAttaqueGarder++;
                                mNbrDgardeValAttaqueTotal++;
                            }


                        }


                        break;

                    case IA:

                        break;
                }
            }
        });


        this.D3 = (ImageView) findViewById(R.id.d3);
        this.D3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(humainOuIA(mPartie.getNumTourJoueur()))
                {
                    case HUMAIN:
                        if( mTour1.desAmoin1())
                        {
                            AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mPhaseAttaqueActivity);
                            popupDmoin1.setTitle("D3 IMPOSSIBLE A GARDER");
                            popupDmoin1.setMessage(R.string.Dmoin1);
                            popupDmoin1.show();
                        }
                        else
                        {
                            if(!d3Garde && mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(2).getValeur() == mTour1.getValeurAttaque())
                            {
                                mPartie.getJoueurH(mPartie.getNumTourJoueur()).setDegats( mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats() + mTour1.getValeurAttaque());
                                mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(2).setValeur(0);
                                mTextDegats.setText(String.valueOf(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats()));
                                D3.setImageResource(R.drawable.d_garde);
                                d3Garde = true;
                                mNbrDValeurAttaqueGarder++;
                                mNbrDgardeValAttaqueTotal++;
                            }


                        }


                        break;

                    case IA:

                        break;
                }
            }
        });


        this.D4 = (ImageView) findViewById(R.id.d4);
        this.D4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(humainOuIA(mPartie.getNumTourJoueur()))
                {
                    case HUMAIN:
                        if( mTour1.desAmoin1())
                        {
                            AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mPhaseAttaqueActivity);
                            popupDmoin1.setTitle("D4 IMPOSSIBLE A GARDER");
                            popupDmoin1.setMessage(R.string.Dmoin1);
                            popupDmoin1.show();
                        }
                        else
                        {
                            if(!d4Garde && mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(3).getValeur() == mTour1.getValeurAttaque())
                            {
                                mPartie.getJoueurH(mPartie.getNumTourJoueur()).setDegats( mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats() + mTour1.getValeurAttaque());
                                mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(3).setValeur(0);
                                mTextDegats.setText(String.valueOf(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats()));
                                D4.setImageResource(R.drawable.d_garde);
                                d4Garde = true;
                                mNbrDValeurAttaqueGarder++;
                                mNbrDgardeValAttaqueTotal++;
                            }


                        }


                        break;

                    case IA:

                        break;
                }
            }
        });


        this.D5 = (ImageView) findViewById(R.id.d5);
        this.D5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(humainOuIA(mPartie.getNumTourJoueur()))
                {
                    case HUMAIN:
                        if( mTour1.desAmoin1())
                        {
                            AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mPhaseAttaqueActivity);
                            popupDmoin1.setTitle("D5 IMPOSSIBLE A GARDER");
                            popupDmoin1.setMessage(R.string.Dmoin1);
                            popupDmoin1.show();
                        }
                        else
                        {
                            if(!d5Garde && mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(4).getValeur() == mTour1.getValeurAttaque())
                            {
                                mPartie.getJoueurH(mPartie.getNumTourJoueur()).setDegats( mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats()
                                        + mTour1.getValeurAttaque());
                                mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(4).setValeur(0);
                                mTextDegats.setText(String.valueOf(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats()));
                                D5.setImageResource(R.drawable.d_garde);
                                d5Garde = true;
                                mNbrDValeurAttaqueGarder++;
                                mNbrDgardeValAttaqueTotal++;
                            }


                        }


                        break;

                    case IA:

                        break;
                }
            }
        });


        this.mLancerD =(ImageView) findViewById(R.id.lancerD);
        this.mLancerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(humainOuIA(mPartie.getNumTourJoueur()))
                {

                    case HUMAIN:

                        if(!mTour1.desAmoin1() && (mNbrDgardeValAttaqueTotal == 5 || mTour2.nbrDValeurAttaque(mDesGarde)== 0))
                        {
                            Intent tourSuivant = new Intent(mPhaseAttaqueActivity, killer_Tour1.class);
                            tourSuivant.putExtra("partie", (Parcelable) mPartie);
                            tourSuivant.putExtra("tour1", (Parcelable) mTour1);


                            mTour2.attaqueDejoueur(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getNuleroCible());
                            AlertDialog.Builder popupAttaqueHumain = new AlertDialog.Builder(mPhaseAttaqueActivity);
                            popupAttaqueHumain.setTitle("ATTAQUE A " + String.valueOf(mTour1.getValeurAttaque()) + " SUR JOUEUR NUMERO  "
                                    + String.valueOf(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getNuleroCible()));
                            popupAttaqueHumain.setMessage("Vous faite " + String.valueOf(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDegats()));
                            popupAttaqueHumain.setCancelable(false);
                            popupAttaqueHumain.setNegativeButton("Passer au tour suivant", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mTour1.resetAllDegats();
                                    mTour1.resetD();
                                    mPartie.tourSuivant();
                                    mTour1.setValeurAttaque(0);
                                    startActivity(tourSuivant);
                                    finish();

                                }
                            });
                            popupAttaqueHumain.show();
                        }

                        else if(mTour1.desAmoin1() ||  mNbrDValeurAttaqueGarder == mTour2.nbrDValeurAttaque(mDesGarde))
                        {

                            mNbrDValeurAttaqueGarder = 0;
                            mPartie.getJoueurH(mPartie.getNumTourJoueur()).lancerlesD();

                            mRotationImageD += 30;
                            mLancerD.setRotation(mRotationImageD);
                            for (int i = 0; i<mDesGarde.length; i++) {
                               mDesGarde[i].setValeur(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(i).getValeur());
                            }
                            if (mRotationImageD > 360) {
                                mRotationImageD = 0;
                            }
                            for(int i = 0; i<mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDesTab().length; i++)
                            {
                                switch(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(i).getValeur())
                                {
                                    case 1:
                                        switch(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(i).getnumero())
                                        {
                                            case 1:
                                                D1.setImageResource(R.drawable.d1);

                                            break;
                                            case 2:
                                                D2.setImageResource(R.drawable.d1);

                                                break;
                                            case 3:
                                                D3.setImageResource(R.drawable.d1);

                                                break;
                                            case 4:
                                                D4.setImageResource(R.drawable.d1);

                                                break;
                                            case 5:
                                                D5.setImageResource(R.drawable.d1);

                                                break;
                                        }
                                        break;
                                    case 2:
                                        switch(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(i).getnumero())
                                        {
                                            case 1:
                                                D1.setImageResource(R.drawable.d2);

                                                break;
                                            case 2:
                                                D2.setImageResource(R.drawable.d2);

                                                break;
                                            case 3:
                                                D3.setImageResource(R.drawable.d2);

                                                break;
                                            case 4:
                                                D4.setImageResource(R.drawable.d2);

                                                break;
                                            case 5:
                                                D5.setImageResource(R.drawable.d2);

                                                break;
                                        }
                                        break;

                                    case 3:
                                        switch(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(i).getnumero())
                                        {
                                            case 1:
                                                D1.setImageResource(R.drawable.d3);

                                                break;
                                            case 2:
                                                D2.setImageResource(R.drawable.d3);

                                                break;
                                            case 3:
                                                D3.setImageResource(R.drawable.d3);

                                                break;
                                            case 4:
                                                D4.setImageResource(R.drawable.d3);

                                                break;
                                            case 5:
                                                D5.setImageResource(R.drawable.d3);

                                                break;
                                        }
                                        break;
                                    case 4:
                                        switch(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(i).getnumero())
                                        {
                                            case 1:
                                                D1.setImageResource(R.drawable.d4);

                                                break;
                                            case 2:
                                                D2.setImageResource(R.drawable.d4);

                                                break;
                                            case 3:
                                                D3.setImageResource(R.drawable.d4);

                                                break;
                                            case 4:
                                                D4.setImageResource(R.drawable.d4);

                                                break;
                                            case 5:
                                                D5.setImageResource(R.drawable.d4);

                                                break;
                                        }
                                        break;
                                    case 5:
                                        switch(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(i).getnumero())
                                        {
                                            case 1:
                                                D1.setImageResource(R.drawable.d5);

                                                break;
                                            case 2:
                                                D2.setImageResource(R.drawable.d5);

                                                break;
                                            case 3:
                                                D3.setImageResource(R.drawable.d5);

                                                break;
                                            case 4:
                                                D4.setImageResource(R.drawable.d5);

                                                break;
                                            case 5:
                                                D5.setImageResource(R.drawable.d5);

                                                break;
                                        }
                                        break;
                                    case 6:
                                        switch(mPartie.getJoueurH(mPartie.getNumTourJoueur()).getDes(i).getnumero())
                                        {
                                            case 1:
                                                D1.setImageResource(R.drawable.d6);

                                                break;
                                            case 2:
                                                D2.setImageResource(R.drawable.d6);

                                                break;
                                            case 3:
                                                D3.setImageResource(R.drawable.d6);

                                                break;
                                            case 4:
                                                D4.setImageResource(R.drawable.d6);

                                                break;
                                            case 5:
                                                D5.setImageResource(R.drawable.d6);

                                                break;
                                        }
                                        break;
                                }
                            }



                        }
                        else if(!mTour1.desAmoin1() && mTour2.nbrDValeurAttaque(mDesGarde) > mNbrDValeurAttaqueGarder)
                        {
                            AlertDialog.Builder popupPasDeDAttaque = new AlertDialog.Builder(mPhaseAttaqueActivity);
                            popupPasDeDAttaque.setTitle("LANCEE DES DES IMPOSSIBLE");
                            popupPasDeDAttaque.setMessage("Vous devez gardez tout les dés de la valeur de l\'attaque : "
                                    + String.valueOf(mTour1.getValeurAttaque()));
                            popupPasDeDAttaque.show();


                        }



                        break;
                    case IA:
                        Intent tourSuivant = new Intent(mPhaseAttaqueActivity, killer_Tour1.class);
                        tourSuivant.putExtra("partie", (Parcelable) mPartie);
                        tourSuivant.putExtra("tour1", (Parcelable) mTour1);

                        mTour2.attaqueDejoueur(mPartie.getJoueurIA(mPartie.getNumTourJoueur()-mPartie.getJoueurHTab().length).getNuleroCible());

                        AlertDialog.Builder popuIAlancerD = new AlertDialog.Builder(mPhaseAttaqueActivity);
                        popuIAlancerD.setTitle("ATTAQUE JOUEUR IA" + String.valueOf(mPartie.getNumTourJoueur()));
                        popuIAlancerD.setMessage("IA" + String.valueOf(mPartie.getNumTourJoueur()) + " fait " + mPartie.getJoueurIA(mPartie.getNumTourJoueur()-mPartie.getJoueurHTab().length).getDegats() + " au joueur numero " +
                                mPartie.getJoueurIA(mPartie.getNumTourJoueur()-mPartie.getJoueurHTab().length).getNuleroCible());
                        popuIAlancerD.setCancelable(false);
                        popuIAlancerD.setNegativeButton("Passser au tour suivant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mTour1.resetD();
                                mTour1.resetAllDegats();
                                mTour1.setValeurAttaque(0);
                                    mPartie.tourSuivant();
                                    startActivity(tourSuivant);
                                    finish();
                            }
                        });
                        popuIAlancerD.show();




                        break;
                }
            }
        });




    }

    private Partie mPartie;
    private Tour1 mTour1;
    private Tour2 mTour2;

    private Des[] mDesGarde;

    private Activity mPhaseAttaqueActivity;

    private TextView mTextNumTourJoueur;
    private TextView mTextDegats;
    private TextView mTextValeurAttaque;

    private ImageView D1;
    private ImageView D2;
    private ImageView D3;
    private ImageView D4;
    private ImageView D5;

    private ImageView mLancerD;

    private boolean d1Garde;
    private boolean d2Garde;
    private boolean d3Garde;
    private boolean d4Garde;
    private boolean d5Garde;

    private int mRotationImageD;

    //par lancer de D
    private int mNbrDValeurAttaqueGarder;


    private int mNbrDgardeValAttaqueTotal;
    //au total partie  attaque
    private ImageView mLogoBack;
    private ImageView mInfoAttaque;
    private ImageView mRegles;
    private ImageView mStatutJoueur;
}
