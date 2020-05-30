package edu.unama.p03_appnumint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 1. declarar variáveis dos componentes dinâmicos:
    EditText editNumero;
    TextView txtNumeros, txtImpar, txtPar, txtSoma;
    ArrayList<Integer> lista = new ArrayList<>();
    int impares = 0, pares = 0, soma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 2. integração entre XML e Java:
        editNumero = findViewById(R.id.edit_numero);
        txtNumeros = findViewById(R.id.txt_numeros);
        txtImpar   = findViewById(R.id.txt_impar);
        txtPar     = findViewById(R.id.txt_par);
        txtSoma    = findViewById(R.id.txt_soma);
    } // fim do onCreate

    // 3. criar o método que será executado
    // qdo o usuário clicar no botão OK
    public void calcular(View v) {
        // 4. pegar o valor digitado:
        String valorDig = editNumero.getText().toString();
        // 5. transformar String para int:
        int valor = Integer.parseInt(valorDig);
        // 6. adicionar o valor na lista:
        lista.add( valor );
        // 7. mostrar a lista na tela:
        txtNumeros.setText( lista.toString() );
        // 8. criar métodos para calcular ímpares, pares, soma:
        calcImpar( valor );
        calcPar( valor );
        calcSoma( valor );
        // 9. limpar campo depois de clicar no botão:
        editNumero.setText("");
    } // fim do calcular

    private void calcSoma(int valor) {
        soma = soma + valor;
        txtSoma.setText( String.valueOf(soma) );
    }

    private void calcPar(int valor) {
        int resto = valor % 2;
        if ( resto == 0 ) {
            pares++;
            txtPar.setText( pares+"" );
        }
    }

    private void calcImpar(int valor) {
        int resto = valor % 2;
        if(resto != 0) {
            impares++;
            txtImpar.setText(impares+"");
        }
    }

} // fim da classe
