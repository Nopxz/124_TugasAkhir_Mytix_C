package com.example.tessolopam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TampilanBlackpanther extends AppCompatActivity {

    private Button btpesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_blackpanther);

        btpesan = findViewById(R.id.btpesan);
        btpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TampilanOrder.class);
                startActivity(i);
            }
        });
    }
}