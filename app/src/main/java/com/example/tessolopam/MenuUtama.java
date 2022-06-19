package com.example.tessolopam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
    }
    public void tampilanbatman(View v) {
        Intent i = new Intent(this, TampilanBatman.class);
        startActivity(i);
    }
    public void tampilanmoonknight(View v) {
        Intent i = new Intent(this, TampilanMoonknight.class);
        startActivity(i);
    }
    public void tampilanbp(View v) {
        Intent i = new Intent(this, TampilanBlackpanther.class);
        startActivity(i);
    }
    public void tampilanpesanan(View v) {
        Intent i = new Intent(this, TampilanPesanan.class);
        startActivity(i);
    }
    public void tampilanorder(View v) {
        Intent i = new Intent(this, MenuUtama.class);
        startActivity(i);
    }


}