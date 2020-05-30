package edu.unama.p01_conta_ads_noite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    // 1. criar variáveis para os componentes dinâmicos:
    EditText campoValor;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. integração entre XML e JAVA:
        campoValor   = findViewById(R.id.edit_valor);
        txtResultado = findViewById(R.id.txt_resultado);
    } // fim do onCreate

    // 3. método que será executado quando o usuário tocar no botão
    public void calcular(View v) {
        // 4. pegar valor do campo de valor:
        String valorDigitado = campoValor.getText().toString();
        // 5. converter valor para número:
        double valor = Double.valueOf(valorDigitado);
        // 6. realizar o cálculo:
        double resultado = valor * (110.0 / 100.0);
        // 7. formatar o valor final:
        DecimalFormat df = new DecimalFormat("#.##");
        // 8. atribuir o resultado no campo de resultado:
        txtResultado.setText("Valor da conta com 10% R$ " + df.format(resultado));
    } // fim do calcular


} // fim da classe
