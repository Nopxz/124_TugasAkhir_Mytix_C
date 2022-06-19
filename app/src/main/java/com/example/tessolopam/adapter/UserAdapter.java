package com.example.tessolopam.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tessolopam.model.PesanModel;
import com.example.tessolopam.R;
import com.example.tessolopam.model.PesanModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.PesanViewHolder> {

    private Context context;
    private List<PesanModel> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<PesanModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PesanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data,parent,false);
        return new PesanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PesanViewHolder holder, int position) {
        holder.nama.setTextSize(25);
        holder.total.setTextSize(15);

        holder.nama.setTextColor(Color.BLUE);
        holder.total.setTextColor(Color.BLACK);

        holder.nama.setText(list.get(position).getNama());
        holder.total.setText("Rp "+list.get(position).getHarga());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PesanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView nama,total;

        public PesanViewHolder(@NonNull View itemView){
            super(itemView);

            cardView = itemView.findViewById(R.id.cardPelanggan);
            nama = itemView.findViewById(R.id.textPelanggan);
            total = itemView.findViewById(R.id.textHarga);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog != null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
