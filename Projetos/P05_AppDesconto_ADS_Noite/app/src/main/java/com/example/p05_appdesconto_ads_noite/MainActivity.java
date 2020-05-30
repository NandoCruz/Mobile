package com.example.p05_appdesconto_ads_noite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // 1. declaração dos componentes dinâmicos:
    EditText campoNome, campoPreco, campoDesconto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 2. integração entre XML e Java:
        campoNome = findViewById(R.id.edit_nome);
        campoPreco = findViewById(R.id.edit_preco);
        campoDesconto = findViewById(R.id.edit_desconto);
    } // fim do onCreate

    // 3. carregar o menu da Tela1:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tela1, menu);
        return true;
    }

    // 4. controlar o clique nos itens de menu:
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idClicado = item.getItemId();
        switch (idClicado) {
            case R.id.menu1_calcular:
                // 5. pegar valores digitados:
                String nome = campoNome.getText().toString();
                String precoDigitado = campoPreco.getText().toString();
                String descontoDigitado = campoDesconto.getText().toString();
                // 6. convertendo valores String para double:
                double preco = Double.parseDouble( precoDigitado );
                double desconto = Double.parseDouble( descontoDigitado );
                // 7. cálculo do valor com desconto:
                double precoFinal = preco * (100.0 - desconto) / 100.0;
                // 8. criar e empacotar a Intent Explícita:
                Intent i = new Intent(this, Tela2.class);
                i.putExtra("nome", nome);
                i.putExtra("preco_final", precoFinal);
                startActivity( i );
            return true;
            case R.id.menu1_limpar:
                // 9. limpar os campos de entrada:
                campoNome.setText("");
                campoPreco.setText("");
                campoDesconto.setText("");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

} // fim da classe
