package com.example.p06_avaliaserie_ads_noite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Tela2 extends AppCompatActivity {
    // 8. declaração de componentes dinâmicos:
    TextView txtTitulo, txtGenero, txtAtiva, txtAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        // 9. integração entre XML e Java:
        txtTitulo = findViewById(R.id.txt2_titulo);
        txtGenero = findViewById(R.id.txt2_genero);
        txtAtiva  = findViewById(R.id.txt2_ativa);
        txtAvaliacao = findViewById(R.id.txt2_avaliacao);

        // 10. pegar e desempacotar a Intent repassa para cá:
        Intent j = getIntent();
        String titulo = j.getStringExtra("titulo");
        String genero = j.getStringExtra("genero");
        boolean ativa = j.getBooleanExtra("ativa", false);
        double nota   = j.getDoubleExtra("avaliacao", 0.0);

        // 11. colocar os valores em cada componente dinâmico da Tela2:
        txtTitulo.setText(titulo);
        txtGenero.setText(genero);
        txtAtiva.setText(ativa == true ? "Sim" : "Não");
        txtAvaliacao.setText(nota + "");
    } // fim do onCreate

    // 12. inflar o menu da Tela2:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    // 13. controlar o clique no menu:
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemClicado = item.getItemId();
        if(itemClicado == R.id.menu2_compartilhar) {
            // 14. criar e executar uma Intent implícita:
            Intent k = new Intent(Intent.ACTION_SEND);
            k.setType("text/plain");
            k.putExtra(Intent.EXTRA_SUBJECT, "Avalia Série");
            k.putExtra(Intent.EXTRA_TEXT, "Avaliei a série " + txtTitulo.getText()
                    + " com a nota " + txtAvaliacao.getText() + "/5.0.");
            startActivity(Intent.createChooser(k, "Compartilhar..."));
        }
        return super.onOptionsItemSelected(item);
    }


} // fim da classe
