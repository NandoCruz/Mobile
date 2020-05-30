package br.clima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listaRV;
    private ClimaAdapter climaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaRV = findViewById(R.id.lista_climas);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.VERTICAL);
        listaRV.setLayoutManager( lm );
        listaRV.setHasFixedSize( true );

        carregarClimas();
    }

    private void carregarClimas() {
        if( Utils.isOnline(this) ) {
            String filtro[] = {"Curitiba,BR", "2bfe8d19224a72a9714c623429299b31", "10"};
            new ClimaService(this, new AsyncTaskDelegate() {
                @Override
                public void processFinish(Object output) {
                    List<Clima> listaClimas = (List<Clima>) output;
                    if(listaClimas != null) {
                        climaAdapter = new ClimaAdapter(listaClimas);
                        listaRV.setAdapter(climaAdapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Não foi possível acessar os dados.", Toast.LENGTH_SHORT).show();
                    }
                }
            }).execute(filtro);
        } else {
            Toast.makeText(this, "Você está offline.", Toast.LENGTH_SHORT).show();
        }
    }
}
