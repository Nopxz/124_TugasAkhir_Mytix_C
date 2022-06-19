package com.example.tessolopam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tessolopam.adapter.UserAdapter;
import com.example.tessolopam.model.PesanModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TampilanPesanan extends AppCompatActivity {

    private Button tambah;
    private RecyclerView recyclerView;

    /*Firebase Firestore*/
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<PesanModel> list  = new ArrayList<>();
    private ProgressDialog progressDialog;
    private UserAdapter pesanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_pesanan);

        tambah = findViewById(R.id.btnTambah);
        recyclerView = findViewById(R.id.daftarPesanan);

        // Penggunan Diaglong Loading
        progressDialog = new ProgressDialog(TampilanPesanan.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        pesanAdapter = new UserAdapter(getApplicationContext(), list);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(getApplicationContext(), TampilanOrder.class);
                startActivity(data);
            }
        });


        pesanAdapter.setDialog(new UserAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final  CharSequence[] dialogItem = {"Lihat Data", "Delete", "Edit Data"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(TampilanPesanan.this);

                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){

                            /*Lihat Data*/
                            case 0:
                                Intent lihat = new Intent(getApplicationContext(), LihatTiket.class);
                                lihat.putExtra("namapemesan", list.get(pos).getNama());
                                lihat.putExtra("notelepon", list.get(pos).getTelepon());
                                lihat.putExtra("pembayaran", list.get(pos).getPembayaran());
                                lihat.putExtra("tambahan", list.get(pos).getJumlahtiket());
                                lihat.putExtra("jumlahtiket", list.get(pos).getTambah());
                                lihat.putExtra("namafilm", list.get(pos).getNamafilm());
                                lihat.putExtra("harga", list.get(pos).getHarga());
                                startActivity(lihat);
                                break;
                            /*Hapus Data*/
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                            /*Edit Data*/
                            case 2:
                                Intent intent = new Intent(getApplicationContext(), TampilanOrder.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("nama", list.get(pos).getNama());
                                intent.putExtra("notelepon", list.get(pos).getTelepon());
                                intent.putExtra("pembayaran", list.get(pos).getPembayaran());
                                intent.putExtra("jumlahtiket", list.get(pos).getJumlahtiket());
                                intent.putExtra("tambah", list.get(pos).getTambah());
                                intent.putExtra("namafilm",list.get(pos).getNamafilm());
                                startActivity(intent);
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(pesanAdapter);
    }

    private void deleteData(String id) {
        progressDialog.show();

        db.collection("Tiket").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(TampilanPesanan.this, "Data Gagal Di hapus", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(TampilanPesanan.this, "Data Berhasil Di Hapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }

    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }

    private void getData() {
        progressDialog.show();

        db.collection("Tiket")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();

                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                PesanModel pesanModel = new PesanModel(document.getString("nama"), document.getString("notelepon"),
                                        document.getString("pembayaran"), document.getString("jumlahtiket"), document.getString("tambah"), document.getString("namafilm"),
                                        document.getString("harga"));
                                pesanModel.setId(document.getId());
                                list.add(pesanModel);
                            }
                            pesanAdapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(TampilanPesanan.this, "Data Gagal di ambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}