package com.example.p06_avaliaserie_ads_noite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 1. componentes dinâmicos:
    EditText editTitulo;
    Spinner  spinGenero;
    Switch   swAtiva;
    RatingBar rbAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 2. integração entre XML e Java:
        editTitulo = findViewById(R.id.edit_titulo);
        spinGenero = findViewById(R.id.spin_genero);
        swAtiva    = findViewById(R.id.sw_ativa);
        rbAvaliacao = findViewById(R.id.rb_avaliacao);
    } // fim do onCreate

    // 3. inflar/associar o menu da Tela1:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    // 4. controlar o clique no item de menu:
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idClicado = item.getItemId();
        if(idClicado == R.id.menu1_avaliar) {
            // 5. pegar valores digitados:
            String titulo = editTitulo.getText().toString();
            String genero = spinGenero.getSelectedItem().toString();
            boolean ativa = swAtiva.isChecked();
            double avaliacao = rbAvaliacao.getRating();
            if( titulo.trim().equals("") ) {
                Toast.makeText(this,
                        "Erro! Informe um título válido.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            if( genero.equals("Selecione...") ) {
                Toast.makeText(this,
                        "Erro! Escolha um gênero.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            // 6. criar e empacotar a Intent:
            Intent i = new Intent(this, Tela2.class);
            i.putExtra("titulo", titulo);
            i.putExtra("genero", genero);
            i.putExtra("ativa",  ativa);
            i.putExtra("avaliacao", avaliacao);
            // 7. executar:
            startActivity( i );
        }
        return super.onOptionsItemSelected(item);
    }
} // fim da classe
