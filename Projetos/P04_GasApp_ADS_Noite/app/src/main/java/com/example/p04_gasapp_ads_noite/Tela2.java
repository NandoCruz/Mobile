package com.example.p04_gasapp_ads_noite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Tela2 extends AppCompatActivity {
    // 8. declaração de componente dinâmico:
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        // 9. integração dentre XML e Java:
        txtResultado = findViewById(R.id.txt_resultado);
        // 10. pegar a Intent repassada para cá
        Intent i = getIntent();
        String modelo    = i.getStringExtra("modelo");
        String distancia = i.getStringExtra("distancia");
        String potencia  = i.getStringExtra("potencia");
        String valorGas  = i.getStringExtra("valor_gas");
        double total     = i.getDoubleExtra("total", 0.0);
        // 11. atribuir o resultado no campo respectivo:
        String resultado = modelo + " " + potencia + " gasta R$ " + total +
                " para percorrer " + distancia + " km com gasolina a R$ " +
                valorGas + " por litro.";
        txtResultado.setText( resultado );
    } // fim do onCreate

    // 12. criação do método de compartilhar:
    public void compartilhar(View v) {
        // 13. criar uma Intent Implícita:
        Intent j = new Intent(Intent.ACTION_SEND);
        j.setType("text/plain");
        j.putExtra(Intent.EXTRA_SUBJECT, "GasApp");
        j.putExtra(Intent.EXTRA_TEXT, txtResultado.getText().toString());
        startActivity( Intent.createChooser(j, "Compartilhar...") );
    }

} // fim da classe
