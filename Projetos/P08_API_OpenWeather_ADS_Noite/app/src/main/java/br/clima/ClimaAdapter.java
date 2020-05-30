package br.clima;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ViewHolder> {
    private List<Clima> listaClimas;

    public ClimaAdapter(List<Clima> listaClimas) {
        this.listaClimas = listaClimas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_lista_climas, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Clima c = listaClimas.get(position);
        holder.txtData.setText( c.getData() );
        holder.txtTemp.setText( c.getTemperatura() + " °C" );
        holder.txtSensacao.setText( c.getSensacaoTermica() + " °C" );
        holder.txtDescricao.setText( c.getDescricao() );
    }

    @Override
    public int getItemCount() {
        if(listaClimas != null)
            return listaClimas.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtData, txtTemp, txtSensacao, txtDescricao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtData = itemView.findViewById(R.id.txt_data);
            txtTemp = itemView.findViewById(R.id.txt_temp);
            txtSensacao = itemView.findViewById(R.id.txt_sensacao);
            txtDescricao = itemView.findViewById(R.id.txt_descricao);
        }
    }
}
