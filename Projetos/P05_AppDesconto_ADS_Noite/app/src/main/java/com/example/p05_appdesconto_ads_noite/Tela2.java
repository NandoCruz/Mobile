package com.example.p05_appdesconto_ads_noite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Tela2 extends AppCompatActivity {
    // 10. declaração de componente dinâmicos:
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        // 11. integração entre XML e Java:
        txtResultado = findViewById(R.id.txt_resultado);
        // 12. pegar a Intent da tela 1:
        Intent j = getIntent();
        // 13. desempacotar a Intent:
        String nome = j.getStringExtra("nome");
        double valorFinal = j.getDoubleExtra("preco_final", 0.0);
        // 14. atribuir o valor no componente de resultado:
        txtResultado.setText(nome + " R$ " + valorFinal);
    } // fim do onCreate

    // 15. mostrar o menu na Tela 2:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tela2, menu);
        return true;
    }

    // 16. controle de ação do menu:
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idClicado = item.getItemId();
        switch (idClicado) {
            case R.id.menu2_compartilhar:
                // 17. criação e execução de uma Intent Implícita:
                Intent k = new Intent(Intent.ACTION_SEND);
                k.setType("text/plain");
                k.putExtra(Intent.EXTRA_SUBJECT, "App de Desconto");
                k.putExtra(Intent.EXTRA_TEXT, txtResultado.getText());
                startActivity( Intent.createChooser(k, "Compartilhar...") );
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


} // fim da classe
