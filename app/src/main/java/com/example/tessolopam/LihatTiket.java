package com.example.tessolopam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class LihatTiket extends AppCompatActivity {

    private TextView namapemesan, notelepon, pembayaran, jumlahtiket, tambahan, namafilm, harga;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_tiket);

        namapemesan = findViewById(R.id.vtnamapemesan);
        notelepon = findViewById(R.id.vtnotelepon);
        pembayaran = findViewById(R.id.vtpembayaran);
        jumlahtiket = findViewById(R.id.vtjumlah);
        tambahan = findViewById(R.id.vttambahan);
        namafilm = findViewById(R.id.vtnamafilm);
        harga = findViewById(R.id.vtharga);

        progressDialog = new ProgressDialog(LihatTiket.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil Data...");

        Intent intent = getIntent();

        if(intent != null){
            namapemesan.setText(intent.getStringExtra("namapemesan"));
            notelepon.setText(intent.getStringExtra("notelepon"));
            pembayaran.setText(intent.getStringExtra("pembayaran"));
            jumlahtiket.setText(intent.getStringExtra("jumlahtiket"));
            tambahan.setText(intent.getStringExtra("tambahan"));
            namafilm.setText(intent.getStringExtra("namafilm"));
            harga.setText(intent.getStringExtra("harga"));
        }
    }
}