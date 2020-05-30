package com.example.p07_listacores_recyclerview_bd_ads_noite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CorAdapter extends RecyclerView.Adapter<CorAdapter.ViewHolder> {
    private static List<Cor> listaDados;
    public CorAdapter(List<Cor> dados) {
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
        holder.txtCor.setText(listaDados.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        if(listaDados != null) return listaDados.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        public TextView txtCor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCor = itemView.findViewById(R.id.txt_item_cor);
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(final View v) {
            final int itemRV = getAdapterPosition();
            final Cor corClicada = listaDados.get(itemRV);
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Opções");
            builder.setItems(R.array.opcoes_lista,
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int itemAD) {
                    switch (itemAD) {
                        case 0: // compartilhar
                            String msg = "Selecionei a cor " + corClicada.getNome();
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("text/plain");
                            i.putExtra(Intent.EXTRA_SUBJECT, "Corapp");
                            i.putExtra(Intent.EXTRA_TEXT, msg);
                            v.getContext().startActivity(
                                    Intent.createChooser(i, "Compartilhar..."));
                        break;
                        case 1: // editar:
                            Intent j = new Intent(v.getContext(), InserirCorActivity.class);
                            j.putExtra(CorBD.TabCor.COL_ID, corClicada.getId());
                            j.putExtra(CorBD.TabCor.COL_NOME, corClicada.getNome());
                            v.getContext().startActivity( j );
                        break;
                        case 2: // remover:
                            AlertDialog.Builder builderRemover = new AlertDialog.Builder(v.getContext());
                            builderRemover.setTitle("Remoção");
                            builderRemover.setMessage("Deseja remover a cor " + corClicada.getNome()+"?");
                            builderRemover.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLiteDatabase bd = null;
                                    try {
                                        CorBD corBD = new CorBD(v.getContext());
                                        bd = corBD.getWritableDatabase();
                                        bd.delete(CorBD.TabCor.TABELA,
                                                CorBD.TabCor.COL_ID + " = ? ",
                                                new String[] {corClicada.getId()+""});
                                        removerItemLista(itemRV);
                                        Toast.makeText(v.getContext(),
                                                "Dado removido.", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(v.getContext(),
                                                "Erro ao remover cor. Detalhes: " + e.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    } finally {
                                        bd.close();
                                    }
                                }
                            });
                            builderRemover.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialogRemover = builderRemover.create();
                            dialogRemover.show();
                        break;
                        case 3: // adicionar novo
                            Intent k = new Intent(v.getContext(), InserirCorActivity.class);
                            v.getContext().startActivity( k );
                        break;
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void removerItemLista(int posicao) {
        listaDados.remove(posicao);
        notifyItemRemoved(posicao);
        notifyItemRangeChanged(posicao, getItemCount()-1);
    }

}
