package com.example.tessolopam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.MemoryFile;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TampilanOrder extends AppCompatActivity {

    private EditText namapemesan,notelepon,pembayaran,jumlahtiket,tambahanmenu,namafilm;
    private Button pesan;

    private String sNamafilm;
    private int iHarga;

    /*Convert*/
    private String harga;

    /*Firebas Firestore*/
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_order);

        namapemesan = findViewById(R.id.txNamapemesan);
        notelepon = findViewById(R.id.txNotelepon);
        pembayaran = findViewById(R.id.Pembayaran);
        jumlahtiket = findViewById(R.id.txjumlahtiket);
        namafilm = findViewById(R.id.txnamafilm);
        tambahanmenu = findViewById(R.id.txtambahan);
        pesan = findViewById(R.id.btnPesan);

        /* Penggunaan Popup Loading */
        progressDialog = new ProgressDialog(TampilanOrder.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Transaksi Berhasil...");

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sNamafilm = namafilm.getText().toString();

                if(namapemesan.getText().length() > 0 || notelepon.getText().length() > 0 || pembayaran.getText().length() > 0 ||
                        tambahanmenu.getText().length() > 0 || tambahanmenu.getText().length() > 0 || namafilm.getText().length() > 0){

                    if(sNamafilm.equals("Spiderman") || sNamafilm.equals("spiderman")){
                        iHarga = 50000;
                    }
                    else if(sNamafilm.equals("Moonknight") || sNamafilm.equals("moonknight")){
                        iHarga =  30000;
                    }
                    else if (sNamafilm.equals("The Batman") || sNamafilm.equals("the batman")){
                        iHarga = 40000;
                    }

                    /*Convert int to String*/
                    harga = String.valueOf(iHarga);

                    createData(namapemesan.getText().toString(), notelepon.getText().toString(), pembayaran.getText().toString(), jumlahtiket.getText().toString(),
                            tambahanmenu.getText().toString(), sNamafilm, harga);

                }
                else {
                    Toast.makeText(TampilanOrder.this, "Data Harus di isi Semua", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*Edit atau Update*/
        Intent intent = getIntent();
        if (intent != null){
            id = intent.getStringExtra("id");
            namapemesan.setText(intent.getStringExtra("nama"));
            notelepon.setText(intent.getStringExtra("notelepon"));
            pembayaran.setText(intent.getStringExtra("pembayaran"));
            jumlahtiket.setText(intent.getStringExtra("jumlahtiket"));
            tambahanmenu.setText(intent.getStringExtra("tambah"));
            namafilm.setText(intent.getStringExtra("namafilm"));
        }

    }

    private void createData(String nama, String notelepon, String pembayaran, String jumlahtiket, String tambah, String namafilm, String harga) {
        Map<String,Object> tiket = new HashMap<>();

        tiket.put("nama", nama);
        tiket.put("notelepon", notelepon);
        tiket.put("pembayaran", pembayaran);
        tiket.put("jumlahtiket", jumlahtiket);
        tiket.put("tambah", tambah);
        tiket.put("namafilm", namafilm);
        tiket.put("harga", harga);

        progressDialog.show();

        if (id != null) {
            /**
             *  kode untuk edit data firestore dengan mengambil id
             */
            db.collection("Tiket").document(id)
                    .set(tiket)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(TampilanOrder.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(TampilanOrder.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            /**
             * Code untuk menambahkan data dengan add
             */
            db.collection("Tiket")
                    .add(tiket)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(TampilanOrder.this, "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                            Intent pindah = new Intent(getApplicationContext(), MenuUtama.class);
                            startActivity(pindah);
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TampilanOrder.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}