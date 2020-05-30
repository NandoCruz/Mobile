package com.example.p04_gasapp_ads_noite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // 1. declarar componentes dinâmicos:
    EditText editModelo, editDistancia, editPotencia, editValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 2. integração entre XML e Java:
        editModelo    = findViewById(R.id.edit_modelo);
        editDistancia = findViewById(R.id.edit_distancia);
        editPotencia  = findViewById(R.id.edit_potencia);
        editValor     = findViewById(R.id.edit_valor);
    } // fim do onCreate

    // 3. criar o método para ser chamado no clique do botão:
    public void botaoCalculo(View v) {
        // 4. pegar valores digitados:
        String modelo = editModelo.getText().toString();
        String distDigitada  = editDistancia.getText().toString();
        String potDigitada   = editPotencia.getText().toString();
        String valorDigitado = editValor.getText().toString();
        // 5. conversão para double:
        double distancia = Double.parseDouble( distDigitada );
        double potencia  = Double.parseDouble( potDigitada );
        double valor     = Double.parseDouble( valorDigitado );
        // 6. cálculo do valor gasto:
        double total = valorTotal(distancia, potencia, valor);
        // 7. Intent explícita:
        Intent i = new Intent(this, Tela2.class);
        i.putExtra("modelo",    modelo);
        i.putExtra("distancia", distDigitada);
        i.putExtra("potencia",  potDigitada);
        i.putExtra("valor_gas", valorDigitado);
        i.putExtra("total",     total);
        startActivity( i );
    } // fim do botaoCalculo

    public double valorTotal(double distancia, double potencia, double valor) {
        double autonomia = 0.0;
        if( potencia <= 1.0 )  autonomia = 13.0;
        else if(potencia > 1.0 && potencia <= 1.4)  autonomia = 11.0;
        else if(potencia > 1.4 && potencia <= 1.9)  autonomia = 9.5;
        else autonomia = 7.75;

        double valorTotal = (distancia / autonomia) * valor;
        return valorTotal;
    }

} // fim da classe
