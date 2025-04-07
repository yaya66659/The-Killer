package com.example.killer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.mMain = this;

        this.mLogo = (ImageView) findViewById(R.id.logo);
        this.mLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PseudoActity = new Intent(getApplicationContext(), Killer_Jouer.class);
                startActivity(PseudoActity);
                finish();
            }
        });

            this.mRegles = (ImageView)  findViewById(R.id.regles);
            this.mRegles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder popuRegles = new AlertDialog.Builder(mMain);
                    popuRegles.setTitle("REGLES");
                    popuRegles.setMessage(R.string.regles);

                    popuRegles.show();

                }
            });



    }
    private ImageView mLogo;
    private ImageView mRegles;
    private Activity mMain;

}