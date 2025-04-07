package com.example.killer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Killer_Jouer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_killer_jouer);

        this.mPseudoActivty = this;
        this.mNbrJoueurH = 1;
        this.mNbrJoueurIA = 1;
        this.MINjoueurIA = 1;

        this.mLogo = (ImageView) findViewById(R.id.logo);
        this.mLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backMain);
                finish();
            }
        });


        this.mRegles = (ImageView) findViewById(R.id.regles);
        this.mRegles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupRgeles = new AlertDialog.Builder(mPseudoActivty);
                popupRgeles.setTitle("REGLES");
                popupRgeles.setMessage(R.string.regles);
                popupRgeles.show();
            }
        });


        this.mInfo = (ImageView) findViewById(R.id.infoPseudo);
        this.mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupInfoPseudo = new AlertDialog.Builder(mPseudoActivty);
                popupInfoPseudo.setTitle("INFO");
                popupInfoPseudo.setMessage(R.string.InfoPseudo);
                popupInfoPseudo.show();
            }
        });                       

        this.mPseudoJoueur = (EditText) findViewById(R.id.pseudo);
            this.mPlay = (ImageView) findViewById(R.id.play);
        this.mPlay.setOnClickListener(new View.OnClickListener() {

            
            @Override
            public void onClick(View view) {


                ;
                AlertDialog.Builder popupPlay = new AlertDialog.Builder(mPseudoActivty);
                popupPlay.setTitle("PRET A JOUER ?!!");
                popupPlay.setMessage("Pseudo : " + mPseudoJoueur.getText() + "\n Humain : " + mNbrJoueurH + "\nIA : " + mNbrJoueurIA + "\n\n"
                + "Cliquez sur !Je suis pret! pour démarrer une partie avec la configuration si dessus.\n\n" +
                        "Cliquez sur annuler pour revenir modifier votre pseudo.\n" +
                        "Ou le nombre des joueurs.\n");


                popupPlay.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                popupPlay.setNegativeButton("Je suis PRET!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder popupPret = new AlertDialog.Builder(mPseudoActivty);
                        popupPret.setTitle("VOUS ETE PRET");
                        popupPret.setMessage("Bonne chance.\n Bienvenue dans THE KILLER!!");
                        popupPret.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent KillertAct = new Intent(getApplicationContext(), killer_Tour1.class);
                                KillertAct.putExtra("pseudo", mPseudoJoueur.getText().toString());
                                KillertAct.putExtra("nbrH", String.valueOf(mNbrJoueurH));
                                KillertAct.putExtra("nbrIA", String.valueOf(mNbrJoueurIA));
                                startActivity(KillertAct);
                                finish();

                            }
                        });
                        popupPret.show();
                    }
                });
                popupPlay.show();

            }
        });

        this.mNumJoueurIA = (TextView) findViewById(R.id.numNbrJoueurIA);

        this.mTextNbrJoueurH = (TextView) findViewById(R.id.textNbrJoueurH);
        this.mTextNbrJoueurH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(mNbrJoueurH<MAXjoueurH)
                {
                    mNbrJoueurH++;


                }
                else
                {
                    mNbrJoueurH = MINjoueurH;
                    if(mNbrJoueurIA == 0) {
                        mNbrJoueurIA = 1;
                        mNumJoueurIA.setText(String.valueOf(mNbrJoueurIA));
                    }

                }


               mNumNbrJoueurH.setText(String.valueOf(mNbrJoueurH));



            }
        });

        this.mTextNbrJoueurIA = (TextView) findViewById(R.id.textNbrJoueurIA);
        this.mTextNbrJoueurIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mNbrJoueurH>MINjoueurH)
                    MINjoueurIA = 0;
                else
                    MINjoueurIA = 1;

                if(mNbrJoueurIA<MAXjoueurIA)
                {
                    mNbrJoueurIA++;

                }
                else
                {

                    mNbrJoueurIA =MINjoueurIA;

                }

                mNumJoueurIA.setText(String.valueOf(mNbrJoueurIA));

            }
        });

        this.mNumNbrJoueurH = (TextView) findViewById(R.id.numNbrJoueurH);
        this.mNumNbrJoueurH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mNbrJoueurH<MAXjoueurH)
                {
                    mNbrJoueurH++;


                }

                else
                {
                    mNbrJoueurH = MINjoueurH;
                    if(mNbrJoueurIA == 0)
                    {
                        mNbrJoueurIA = 1;
                        mNumJoueurIA.setText(String.valueOf(mNbrJoueurIA));
                    }

                }



                mNumNbrJoueurH.setText(String.valueOf(mNbrJoueurH));


            }
        });


        this.mNumJoueurIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mNbrJoueurH >MINjoueurH)
                    MINjoueurIA = 0;
                else
                    MINjoueurIA =  1;
                if(mNbrJoueurIA<MAXjoueurIA)
                {
                    mNbrJoueurIA++;


                }

                else
                {
                    mNbrJoueurIA = MINjoueurIA;

                }


                mNumJoueurIA.setText(String.valueOf(mNbrJoueurIA));


            }
        });





    }
      final int  MINjoueurH = 1;
    final int MAXjoueurH = 5;
      int MINjoueurIA = 0;
    final int MAXjoueurIA = 5;


    private int mNbrJoueurH;
   private int mNbrJoueurIA;

    private TextView mTextNbrJoueurH;
    private TextView mNumNbrJoueurH;
    private TextView mTextNbrJoueurIA;
    private TextView mNumJoueurIA;

    private EditText mPseudoJoueur;


    private ImageView mLogo;
    private ImageView mRegles;
    private ImageView mInfo;
    private ImageView mPlay;

    private Activity mPseudoActivty;

}