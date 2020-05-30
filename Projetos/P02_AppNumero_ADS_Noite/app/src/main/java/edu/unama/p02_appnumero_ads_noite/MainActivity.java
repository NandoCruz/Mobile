package edu.unama.p02_appnumero_ads_noite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 1. declarar os componentes dinâmicos:
    EditText campoNumero;
    TextView txtNumeros, txtImpares, txtPares, txtSoma;
    // 2. declarar as variáveis globais:
    int qtdPares = 0, qtdImpares = 0, soma = 0;
    ArrayList<Integer> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 3. integração entre XML e Java:
        campoNumero = findViewById(R.id.edit_numero);
        txtNumeros  = findViewById(R.id.txt_numeros);
        txtImpares  = findViewById(R.id.txt_impares);
        txtPares    = findViewById(R.id.txt_pares);
        txtSoma     = findViewById(R.id.txt_soma);
    } // fim onCreate

    // 4. criação do método que será chamado quando o botão for tocado:
    public void processar(View v) {
        // 5. pegar o número digitado:
        String numeroDigitado = campoNumero.getText().toString();
        // 6. converter String para int:
        int numero = Integer.parseInt( numeroDigitado );
        // 7. armazenar na lista:
        lista.add( numero );
        // 8. verificar se é par ou ímpar:
        if( numero % 2 == 0 )
            qtdPares++;
        else
            qtdImpares++;
        // 9. realizar a soma dos números:
        soma = soma + numero;
        // 10. atribuir os resultados nos campos respectivos:
        txtNumeros.setText( lista.toString() );
        txtImpares.setText( qtdImpares + "" );
        txtPares.setText( qtdPares + "");
        txtSoma.setText( soma + "");
    } // fim do processar

} // fim da classe
