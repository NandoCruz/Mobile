package com.example.p07_listacores_recyclerview_bd_ads_noite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CorAdapter extends RecyclerView.Adapter<CorAdapter.ViewHolder> {
    private List listaDados;
    public CorAdapter(List dados) {
        this.listaDados = dados;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_lista_cor,parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCor.setText(listaDados.get(position).toString());
    }

    @Override
    public int getItemCount() {
        if(listaDados != null) return listaDados.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCor = itemView.findViewById(R.id.txt_item_cor);
        }
    }

}
