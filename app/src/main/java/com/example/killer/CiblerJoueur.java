package com.example.killer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.killer.yann.fr.Partie;
import com.example.killer.yann.fr.Tour1;
import com.example.killer.yann.fr.Tour2;

public class CiblerJoueur extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cibler_joueur);


        Bundle bObjectPartie = getIntent().getExtras();
        this.mPlayCiblerPartie = bObjectPartie.getParcelable("partie");
        this.mTour1 = bObjectPartie.getParcelable("tour1");
        this.mTour2 =  new Tour2(mTour1.getValeurAttaque(), mPlayCiblerPartie.getJoueurHTab(), mPlayCiblerPartie.getJoueurIATab(), mPlayCiblerPartie.getNumTourJoueur(), mPlayCiblerPartie.getJoueurH(1).getPseudo());

        this.mCiblerAtcivity = this;

        this.mBack = (ImageView) findViewById(R.id.back);
        this.mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backMain = new Intent(getApplicationContext(), MainActivity.class);
                AlertDialog.Builder popupBack = new  AlertDialog.Builder(mCiblerAtcivity);
                popupBack.setTitle("QUITER?");
                popupBack.setMessage(R.string.back);
                popupBack.setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        startActivity(backMain);
                        finish();

                    }
                });
                popupBack.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                popupBack.show();

            }
        });


        this.mStatJoueur = (TextView) findViewById(R.id.statJoueur);
        this.mStatJoueur.setText(this.mPlayCiblerPartie.afficherStats(mPlayCiblerPartie.getNumTourJoueur()));

        this.mTextValeurAttaque = (TextView) findViewById(R.id.valeurAttaque);
        this.mTextValeurAttaque.setText("Valeur de l'attaque : " +String.valueOf(this.mTour1.getValeurAttaque()));

        this.mNumTour = (TextView) findViewById(R.id.numeroTour);
        this.mNumTour.setText(String.valueOf(mPlayCiblerPartie.getNumTourJoueur()));

        this.mImageStat = (ImageView) findViewById(R.id.stat);
        this.mImageStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupStat = new AlertDialog.Builder(mCiblerAtcivity);
                popupStat.setTitle("STATUT DES JOUEURS");
                popupStat.setMessage(mPlayCiblerPartie.afficherStats(mPlayCiblerPartie.getNumTourJoueur()));
                popupStat.show();
            }
        });


        this.mRegles = (ImageView)  findViewById(R.id.regles);
        this.mRegles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popuRegle = new AlertDialog.Builder(mCiblerAtcivity);
                popuRegle.setTitle("REGLES");
                popuRegle.setMessage(R.string.regles);
                popuRegle.show();
            }
        });

        this.mInfo = (ImageView) findViewById(R.id.infoCible);
        this.mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupInfo = new AlertDialog.Builder(mCiblerAtcivity);
                popupInfo.setTitle("INFO");
                popupInfo.setMessage(R.string.infoCible);
                popupInfo.show();
            }
        });

        this.mNumCible = (EditText) findViewById(R.id.numCible);

        this.mAttaque = (ImageView) findViewById(R.id.attaque);
        this.mAttaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if(mNumCible.getText().toString().trim().isEmpty()) {

                    AlertDialog.Builder popupNoNumCible = new AlertDialog.Builder(mCiblerAtcivity);
                    popupNoNumCible.setTitle("CIBLE VIDE");
                    popupNoNumCible.setMessage("Saisir un numero de joueur ennemie en VIE\n"
                            + String.valueOf(mPlayCiblerPartie.afficherStats(mPlayCiblerPartie.getNumTourJoueur())));
                    popupNoNumCible.show();

                }
                else if (Tour2.choixNumEnemi(Integer.parseInt(mNumCible.getText().toString())))
                {

                    mPlayCiblerPartie.getJoueurH(mPlayCiblerPartie.getNumTourJoueur()).setNumeroCible(Integer.parseInt(mNumCible.getText().toString()));
                    Intent goAttaque = new Intent(mCiblerAtcivity, Tour2Attaque.class);
                    goAttaque.putExtra("partie", (Parcelable) mPlayCiblerPartie);
                    goAttaque.putExtra("tour1", (Parcelable) mTour1);
                    goAttaque.putExtra("tour2", (Parcelable) mTour2);
                    AlertDialog.Builder popupConfirmAttaque = new AlertDialog.Builder(mCiblerAtcivity);
                    popupConfirmAttaque.setTitle("VOULEZ VOUS ATTAQUER");
                    popupConfirmAttaque.setMessage("Vous allez tenter une attaque a " + String.valueOf(mTour1.getValeurAttaque()) + " sur Joueur numero "+
                            String.valueOf(mPlayCiblerPartie.getJoueurH(mPlayCiblerPartie.getNumTourJoueur()).getNuleroCible()));
                    popupConfirmAttaque.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    popupConfirmAttaque.setNegativeButton("Attaquer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(goAttaque);
                                finish();


                        }
                    });
                    popupConfirmAttaque.show();

                }
                else{
                     AlertDialog.Builder popupNoNumCible = new AlertDialog.Builder(mCiblerAtcivity);
                     popupNoNumCible.setTitle("CIBLE INCORRECTE");
                     popupNoNumCible.setMessage("Saisir un numero de joueur ennemie en VIE\n"
                             + String.valueOf(mPlayCiblerPartie.afficherStats(mPlayCiblerPartie.getNumTourJoueur())));
                     popupNoNumCible.show();
                 }

            }
        });

    }

    private Activity mCiblerAtcivity;
    private static Partie mPlayCiblerPartie;
    private Tour1 mTour1;
    private Tour2 mTour2;

    private ImageView mInfo;

    private ImageView mAttaque;
    private ImageView mBack;
    private ImageView mImageStat;
    private ImageView mRegles;
    private TextView mNumTour;
    private TextView mStatJoueur;
    private TextView mTextValeurAttaque;
    private EditText mNumCible;

}