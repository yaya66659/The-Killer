package com.example.killer;




import static com.example.killer.yann.fr.Partie.humainOuIA;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import java.lang.Thread;
import java.util.Random;

import android.os.Parcelable;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.killer.yann.fr.*;


public class killer_Tour1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_killer_tour1);

        this.mTour1 = this;




        Intent infoKiller = getIntent();
        if(infoKiller != null)
        {
                if(infoKiller.hasExtra("pseudo"))
                {
                    this.mPseudo =  infoKiller.getStringExtra("pseudo");
                }

                if(infoKiller.hasExtra("nbrH"))
                {
                        try {
                            this.mNbrH = Integer.parseInt(infoKiller.getStringExtra("nbrH"));
                        }
                        catch(NumberFormatException e)
                        {}


                }
            if(infoKiller.hasExtra("nbrIA"))
            {

                try {
                    this.mNbrIA = Integer.parseInt(infoKiller.getStringExtra("nbrIA"));
                }
                catch(NumberFormatException e)
                {}

            }
        }
        this.mTextNumTourJoueur = (TextView) findViewById(R.id.numTourJoueur);
        Bundle infoPlayTour = getIntent().getExtras();

                    if(infoPlayTour.getParcelable("partie") != null && infoPlayTour.getParcelable("tour1") != null)
                    {

                       mPlayKiller = infoPlayTour.getParcelable("partie");
                       mPartieTour1 = infoPlayTour.getParcelable("tour1");
                        mNumeroTourJoueur = mPlayKiller.getNumTourJoueur();

                        if(!mPlayKiller.resteJoueurHumainEnVie())
                        {
                            Intent goEnd = new Intent(mTour1, End.class);
                            goEnd.putExtra("partie", (Parcelable) mPlayKiller);
                            goEnd.putExtra("tour1", (Parcelable) mPartieTour1);
                            startActivity(goEnd);
                            finish();


                        }
                        else if (mPlayKiller.resteJoueurHumainEnVie() && Partie.nbrJoueuEnVie() == 1)
                        {

                            for (Humain h:mPlayKiller.getJoueurHTab()) {
                                if(h.getVie() > 0)
                                    mPlayKiller.setNumeroHumainDernierEnVie(h.getNumero());

                            }

                            Intent goEnd = new Intent(mTour1, End.class);
                            goEnd.putExtra("partie", (Parcelable) mPlayKiller);
                            goEnd.putExtra("tour1", (Parcelable) mPartieTour1);
                            startActivity(goEnd);
                            finish();
                        }

                    }
                    else {
                        this.mPlayKiller = new Partie(mNbrH, mNbrIA, this.mPseudo);
                        this.mPartieTour1 = new Tour1(mNbrH, mNbrIA, this.mPseudo);
                        mNumeroTourJoueur = mPlayKiller.getNumTourJoueur();



                    }




        this.mTextNumTourJoueur.setText(String.valueOf(mNumeroTourJoueur));
                this.mD1garde = false;
                this.mD2garde = false;
                this.mD3garde = false;
                this.mD4garde = false;
                this.mD5garde = false;

                this.mNbreDgarderTotalTour1 = 0;
                this.mNbrDgardePour1Lancer = 0;
        this.mScore = (TextView) findViewById(R.id.score);




        this.mLogo = (ImageView) findViewById(R.id.back);
        this.mLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backMainAct = new Intent(getApplicationContext(),Killer_Jouer.class);

                AlertDialog.Builder popupBack = new AlertDialog.Builder(mTour1);
                popupBack.setTitle("AVERTISSEMENT");
                popupBack.setMessage(R.string.back);
                popupBack.setPositiveButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
                popupBack.setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(backMainAct);
                        finish();
                    }
                });
                popupBack.show();

            }
        });


        this.mRegles = (ImageView) findViewById(R.id.regles);
        this.mRegles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupRegles = new AlertDialog.Builder(mTour1);
                popupRegles.setTitle("REGLES");
                popupRegles.setMessage(R.string.regles);
                popupRegles.show();
            }
        });

        this.mInfoKiller = (ImageView) findViewById(R.id.infoKiller);
        this.mInfoKiller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupInfo = new AlertDialog.Builder(mTour1);
                popupInfo.setTitle("INFO");
                popupInfo.setMessage(R.string.infoKiller1);
                popupInfo.show();
            }
        });

        /******************STATUT DES JOUEURS ************/


       this.mStatutsJoueurs = (ImageView) findViewById(R.id.statJoueur);
       this.mStatutsJoueurs.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder popupStatut = new AlertDialog.Builder(mTour1);
               popupStatut.setTitle("STATUS JOUEURS");
               popupStatut.setMessage(mPlayKiller.afficherStats());
               popupStatut.show();
           }
       });



        /**********************************************************/


        /***************GESTION DES D******************************/

        //LANCER DE D.

        this.D1 =(ImageView) findViewById(R.id.d1);
        this.D1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mPartieTour1.desAmoin1()) {

                    switch (humainOuIA(mNumeroTourJoueur)) {
                        case HUMAIN:
                            if (!mD1garde) {
                                AlertDialog.Builder popupD1 = new AlertDialog.Builder(mTour1);
                                popupD1.setTitle("GARDER D1?");
                                popupD1.setMessage("Voulez vous garder le Dés numero " + "1 de valeur : " +
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(0).getValeur());
                                popupD1.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                popupD1.setNegativeButton("OUI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mD1garde = true;
                                        D1.setImageResource(R.drawable.d_garde);

                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).setScore(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore()
                                                + mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(0).getValeur());
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(0).setValeur(0);

                                        mNbrDgardePour1Lancer++;
                                        mNbreDgarderTotalTour1++;
                                        mScore.setText(String.valueOf(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore()));
                                    }
                                });
                                popupD1.show();

                            }
                            break;
                        case IA:
                            break;
                    }

                }
                else
                {
                    AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mTour1);
                    popupDmoin1.setTitle("D1");
                    popupDmoin1.setMessage(R.string.Dmoin1);
                    popupDmoin1.show();
                }
            }

        });

        this.D2 =(ImageView) findViewById(R.id.d2);
        this.D2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mPartieTour1.desAmoin1()) {
                    switch (humainOuIA(mNumeroTourJoueur)) {
                        case HUMAIN:
                            if (!mD2garde) {
                                AlertDialog.Builder popupD1 = new AlertDialog.Builder(mTour1);
                                popupD1.setTitle("GARDER D2?");
                                popupD1.setMessage("Voulez vous garder le Dés numero "+ "2 de valeur : " +
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(1).getValeur());
                                popupD1.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                popupD1.setNegativeButton("OUI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mD2garde = true;
                                        D2.setImageResource(R.drawable.d_garde);

                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).setScore(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore() +
                                                mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(1).getValeur());
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(1).setValeur(0);

                                        mNbrDgardePour1Lancer++;
                                        mNbreDgarderTotalTour1++;
                                        mScore.setText(String.valueOf(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore()));
                                    }
                                });
                                popupD1.show();

                            }
                            break;
                        case IA:
                            break;
                    }

                }
                else
                {
                    AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mTour1);
                    popupDmoin1.setTitle("D2");
                    popupDmoin1.setMessage(R.string.Dmoin1);
                    popupDmoin1.show();
                }
            }
        });

        this.D3 =(ImageView) findViewById(R.id.d3);
        this.D3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mPartieTour1.desAmoin1()) {

                    switch (humainOuIA(mNumeroTourJoueur)) {
                        case HUMAIN:
                            if (!mD3garde) {
                                AlertDialog.Builder popupD1 = new AlertDialog.Builder(mTour1);
                                popupD1.setTitle("GARDER D3?");
                                popupD1.setMessage("Voulez vous garder le Dés numero " + "3 de valeur : " +
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(2).getValeur());
                                popupD1.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                popupD1.setNegativeButton("OUI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mD3garde = true;
                                        D3.setImageResource(R.drawable.d_garde);

                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).setScore(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore() +
                                                mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(2).getValeur());
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(2).setValeur(0);

                                        mNbrDgardePour1Lancer++;
                                        mNbreDgarderTotalTour1++;
                                        mScore.setText(String.valueOf(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore()));
                                    }
                                });
                                popupD1.show();

                            }
                            break;
                        case IA:
                            break;
                    }

                }
                else
                {
                    AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mTour1);
                    popupDmoin1.setTitle("D3");
                    popupDmoin1.setMessage(R.string.Dmoin1);
                    popupDmoin1.show();
                }
            }
        });

        this.D4 =(ImageView) findViewById(R.id.d4);
        this.D4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mPartieTour1.desAmoin1()) {
                    switch (humainOuIA(mNumeroTourJoueur)) {
                        case HUMAIN:
                            if (!mD4garde) {
                                AlertDialog.Builder popupD1 = new AlertDialog.Builder(mTour1);
                                popupD1.setTitle("GARDER D4?");
                                popupD1.setMessage("Voulez vous garder le Dés numero "  + "4 de valeur : " +
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(3).getValeur());
                                popupD1.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                popupD1.setNegativeButton("OUI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mD4garde = true;
                                        D4.setImageResource(R.drawable.d_garde);

                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).setScore(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore() +
                                                mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(3).getValeur());
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(3).setValeur(0);

                                        mNbrDgardePour1Lancer++;
                                        mNbreDgarderTotalTour1++;
                                        mScore.setText(String.valueOf(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore()));
                                    }
                                });
                                popupD1.show();

                            }
                            break;
                        case IA:
                            break;
                    }

                }
                else
                {
                    AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mTour1);
                    popupDmoin1.setTitle("D4");
                    popupDmoin1.setMessage(R.string.Dmoin1);
                    popupDmoin1.show();
                }
            }
        });

        this.D5 =(ImageView) findViewById(R.id.d5);
        this.D5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mPartieTour1.desAmoin1()) {
                    switch (humainOuIA(mNumeroTourJoueur)) {
                        case HUMAIN:
                            if (!mD5garde) {
                                AlertDialog.Builder popupD1 = new AlertDialog.Builder(mTour1);
                                popupD1.setTitle("GARDER D5?");
                                popupD1.setMessage("Voulez vous garder le Dés numero "  + "5 de valeur : " +
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(4).getValeur());
                                popupD1.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                popupD1.setNegativeButton("OUI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mD5garde = true;
                                        D5.setImageResource(R.drawable.d_garde);

                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).setScore(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore() +
                                                mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(4).getValeur());
                                        mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(4).setValeur(0);

                                        mNbrDgardePour1Lancer++;
                                        mNbreDgarderTotalTour1++;
                                        mScore.setText(String.valueOf(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore()));
                                    }
                                });
                                popupD1.show();

                            }
                            break;
                        case IA:
                            break;
                    }

                }
                else
                {
                    AlertDialog.Builder popupDmoin1 = new AlertDialog.Builder(mTour1);
                    popupDmoin1.setTitle("D5");
                    popupDmoin1.setMessage(R.string.Dmoin1);
                    popupDmoin1.show();
                }
            }
        });


        this.mLancerD = (ImageView) findViewById(R.id.desLancer);
        this.mLancerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch(humainOuIA(mPlayKiller.getNumTourJoueur())) {
                    case NULL:
                        break;
                    case HUMAIN:

                        if(mNbreDgarderTotalTour1 == 5)
                        {
                            boolean peuxAttaquer = false;
                            int attaque = mPartieTour1.calculeAttaque(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore());
                            switch (mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore())
                            {
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                    mMessageFinTour1 = "Vous Avez droit de cibler un Joueur ennemie en VIE avec une attaque de " + attaque;
                                    peuxAttaquer = true;
                                    break;
                                case 11:
                                case 24 :
                                        mMessageFinTour1 = "Vous vous regenerez de " + String.valueOf(mPartieTour1.gagneUnD6DeVie()) +
                                                " points de VIE";

                                    break;
                                case 25:
                                case 26:
                                case 27:
                                case 28:
                                case 29:
                                case 30:
                                    mMessageFinTour1 = "Vous Avez droit de cibler un Joueur ennemie en VIE avec une attaque de " + attaque;
                                    peuxAttaquer = true;
                                    break;

                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:

                                case 18:
                                case 19:
                                case 20:
                                case 21:
                                case 22:
                                case 23:
                                    mMessageFinTour1 = "Vous perdez " + attaque + " point de VIE.";
                                    break;

                            }

                            AlertDialog.Builder popupfinTour1 = new AlertDialog.Builder(mTour1);
                            popupfinTour1.setTitle("SCORE " + String.valueOf(mPlayKiller.getJoueurH(mNumeroTourJoueur).getScore()));
                            popupfinTour1.setMessage(mMessageFinTour1);
                            popupfinTour1.setCancelable(false);
                            if(peuxAttaquer)
                            {
                                Intent ciblerAct = new Intent(mTour1, CiblerJoueur.class);
                                ciblerAct.putExtra("partie", (Parcelable)mPlayKiller );
                                ciblerAct.putExtra("tour1", (Parcelable) mPartieTour1);
                                popupfinTour1.setPositiveButton("Choisir une cible et Tentez une Attaque.", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mPartieTour1.resetD();
                                        mPartieTour1.resetAllScore();
                                        startActivity(ciblerAct);
                                        finish();

                                    }
                                });
                            }
                            else {
                                Intent tourSuivantRetourLancerD = new Intent(mTour1, killer_Tour1.class);
                                tourSuivantRetourLancerD.putExtra("partie", (Parcelable) mPlayKiller);
                                tourSuivantRetourLancerD.putExtra("tour1", (Parcelable) mPartieTour1);
                                popupfinTour1.setPositiveButton("Passer au tour suivant.", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                            mPartieTour1.resetD();
                                            mPartieTour1.tourSuivant();
                                            mPartieTour1.resetAllScore();
                                            startActivity(tourSuivantRetourLancerD);
                                            finish();


                                    }
                                });

                            }


                            popupfinTour1.show();
                        }


                        else if (mNbrDgardePour1Lancer > 0 || mPartieTour1.desAmoin1()) {
                            mNbrDgardePour1Lancer = 0;
                            mRotationImageD += 30;
                            mLancerD.setRotation(mRotationImageD);
                            if (mRotationImageD > 360) {
                                mRotationImageD = 0;
                            }


                            mPlayKiller.getJoueurH(mNumeroTourJoueur).lancerlesD();
                            for (int i = 0; i < mPlayKiller.getJoueurH(mNumeroTourJoueur).getDesTab().length; i++) {

                                if (mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(i).getValeur() != 0) {
                                    switch (mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(i).getValeur()) {
                                        case 1:
                                            switch (mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(i).getnumero()) {
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
                                            switch (mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(i).getnumero()) {
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
                                            switch (mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(i).getnumero()) {
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
                                            switch (mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(i).getnumero()) {
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
                                            switch (mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(i).getnumero()) {
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
                                            switch (mPlayKiller.getJoueurH(mNumeroTourJoueur).getDes(i).getnumero()) {
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

                        } else {
                            AlertDialog.Builder popupNonLancerD = new AlertDialog.Builder(mTour1);
                            popupNonLancerD.setTitle("!IMPOSSIBLE DE LANCER LES DES!");
                            popupNonLancerD.setMessage(R.string.DgardeMoinDe1);
                            popupNonLancerD.show();

                        }



                            break;

                            case IA:


                                mRotationImageD += 30;
                                mLancerD.setRotation(mRotationImageD);
                                if (mRotationImageD > 360) {
                                    mRotationImageD = 0;
                                }





                                    boolean peuxAttaquer = false;
                                mPlayKiller.getJoueurIA(mPlayKiller.getNumTourJoueur()-mPlayKiller.getJoueurHTab().length).garderD(1);

                                int attaque = mPartieTour1.calculeAttaque(mPlayKiller.getJoueurIA(mPlayKiller.getNumTourJoueur()-mPlayKiller.getJoueurHTab().length).getScore());

                                   switch (mPlayKiller.getJoueurIA(mPlayKiller.getNumTourJoueur()-mPlayKiller.getJoueurHTab().length).getScore())
                                    {
                                        case 5:
                                        case 6:
                                        case 7:
                                        case 8:
                                        case 9:
                                        case 10:
                                        case 25:
                                        case 26:
                                        case 27:
                                        case 28:
                                        case 29:
                                        case 30:
                                            mMessageIATour1 = "le joueur IA" + String.valueOf(mNumeroTourJoueur) +
                                                    " va cibler un Joueur ennemie en VIE avec une attaque de " +
                                                    String.valueOf(attaque);
                                            peuxAttaquer = true;
                                            break;
                                        case 11:
                                        case 24 :
                                            mMessageIATour1 = "le joueur IA" + String.valueOf(mNumeroTourJoueur) +
                                                    " regenerez de " + String.valueOf(mPartieTour1.gagneUnD6DeVie()) +
                                                    " points de VIE";

                                            break;

                                        case 12:
                                        case 13:
                                        case 14:
                                        case 15:
                                        case 16:
                                        case 17:

                                        case 18:
                                        case 19:
                                        case 20:
                                        case 21:
                                        case 22:
                                        case 23:
                                            mMessageIATour1 = "Le joueur IA" +
                                                    String.valueOf(mNumeroTourJoueur) +" perd " +
                                                    String.valueOf(attaque) + " point de VIE";
                                            break;

                                        default:
                                            mMessageIATour1 =" erreure switch";
                                            break;

                                    }


                                    AlertDialog.Builder popupIAScore = new AlertDialog.Builder(mTour1);
                                    popupIAScore.setTitle("SCORE DE JOUEUR IA " + String.valueOf(mNumeroTourJoueur));
                                    popupIAScore.setMessage(mMessageIATour1);
                                    popupIAScore.setCancelable(false);
                                    if(peuxAttaquer)
                                    {
                                        mPartieTour2 = new Tour2(mPartieTour1.getValeurAttaque(), mPlayKiller.getJoueurHTab(),
                                                mPlayKiller.getJoueurIATab(), mNumeroTourJoueur, mPseudo);

                                        Tour2.choixNumEnemi(0);

                                        Intent versTour2 = new Intent(mTour1, Tour2Attaque.class);
                                        versTour2.putExtra("partie", (Parcelable) mPlayKiller);
                                        versTour2.putExtra("tour1", (Parcelable) mPartieTour1);
                                        versTour2.putExtra("tour2", (Parcelable) mPartieTour2);
                                    popupIAScore.setNegativeButton("Voir Cible du Joueur IA "+ String.valueOf(mNumeroTourJoueur), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            AlertDialog.Builder popupCibleIA = new AlertDialog.Builder(mTour1);
                                            popupCibleIA.setTitle("CIBLE DU JOUEUR IA " + String.valueOf(mPlayKiller.getNumTourJoueur()));
                                            popupCibleIA.setMessage("Le joueur IA" + String.valueOf(mPlayKiller.getNumTourJoueur()) + "a pris pour cible le joueur numero "
                                                    + mPlayKiller.getJoueurIA(mNumeroTourJoueur-mPlayKiller.getJoueurHTab().length).getNuleroCible() );
                                            popupCibleIA.setCancelable(false);
                                            popupCibleIA.setNegativeButton("Voir Attaque du joueur IA" + String.valueOf(mNumeroTourJoueur), new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    mPartieTour1.resetD();
                                                    mPartieTour1.resetAllScore();

                                                    startActivity(versTour2);
                                                    finish();
                                                }
                                            });
                                            popupCibleIA.show();



                                        }
                                    });
                                    popupIAScore.show();
                                    }
                                    else {

                                        Intent tourSuivant = new Intent(mTour1, killer_Tour1.class);
                                        tourSuivant.putExtra("partie", (Parcelable) mPlayKiller);
                                        tourSuivant.putExtra("tour1", (Parcelable) mPartieTour1);
                                        popupIAScore.setNegativeButton("Passer au tour suivant", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialogInterface, int i) {

                                              mPartieTour1.resetD();
                                              mPartieTour1.resetAllScore();
                                              mPartieTour1.tourSuivant();
                                              startActivity(tourSuivant);
                                              finish();



                                          }
                                      });
                                    }
                                    popupIAScore.show();


                                break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + humainOuIA(mPlayKiller.getNumTourJoueur()));
                }


           }
        });




        /*********************************************************/


    }
    
    private float mRotationImageD;
    private final long TIME_ROTATION8_IMG_D = 10;
    private String mMessageIATour1;

    private ImageView mLogo;
    private TextView mTextNumTourJoueur;
    private ImageView mInfoKiller;
    private TextView mScore;
    private ImageView mRegles;
    private ImageView mLancerD;
    private ImageView mStatutsJoueurs;

    private ImageView D1;
    private ImageView D2;
    private ImageView D3;
    private ImageView D4;
    private ImageView D5;

    private boolean mD1garde;
    private boolean mD2garde;
    private boolean mD3garde;
    private boolean mD4garde;
    private boolean mD5garde;



    private String mPseudo;
    private int mNbrDgardePour1Lancer;

    private int mNbreDgarderTotalTour1;
    private int mNbrH;
    private int mNbrIA;


    private static Partie mPlayKiller;
    private Tour1 mPartieTour1;
    private Tour2 mPartieTour2;
    private String mMessageFinTour1;

    private static int mNumeroTourJoueur;


    private Activity mTour1;

}