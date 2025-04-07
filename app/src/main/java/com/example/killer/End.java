package com.example.killer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.killer.yann.fr.Partie;
import com.example.killer.yann.fr.Tour1;

public class End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        this.endActivity = this;

        Bundle bInfoPartie = getIntent().getExtras();
        this.mPartie = bInfoPartie.getParcelable("partie");
        this.mTour1 = bInfoPartie.getParcelable("tour1");

        this.gagner = (TextView) findViewById(R.id.gagner);

        this.numJoueur = (TextView) findViewById(R.id.numeroJoueurGagner);

        this.stat = (TextView) findViewById(R.id.stat);
        this.stat.setText(mPartie.afficherStats());

        this.info = (ImageView) findViewById(R.id.infoEnd);
        this.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popupInfo = new AlertDialog.Builder(endActivity);
                popupInfo.setTitle("INFO");
                popupInfo.setMessage("Touchez le logo Killer pour recommencer.");
                popupInfo.show();
            }
        });

        this.back = (ImageView) findViewById(R.id.back);
        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restart = new Intent(getApplicationContext(), Killer_Jouer.class);
                startActivity(restart);
                finish();
            }
        });

        if(!mPartie.resteJoueurHumainEnVie())
        {
            this.gagner.setText("PERDU");
            this.numJoueur.setText("");
        }
        else
        {
            this.gagner.setText("GAGNER");
            this.numJoueur.setText("Vainqueur Joueur numero " + String.valueOf(this.mPartie.getNumeroHumainDernierEnVie()));
        }

    }
    private Partie mPartie;
    private Tour1 mTour1;

    private Activity endActivity;

    private ImageView info;
    private ImageView back;
    private TextView gagner;
    private TextView numJoueur;
    private TextView stat;
}