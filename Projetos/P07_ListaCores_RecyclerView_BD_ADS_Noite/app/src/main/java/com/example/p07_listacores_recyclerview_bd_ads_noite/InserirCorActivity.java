package com.example.p07_listacores_recyclerview_bd_ads_noite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InserirCorActivity extends AppCompatActivity {
    private EditText editCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_cor);

        editCor = findViewById(R.id.edit_cor);
    }

    public void inserirCor(View v) {
        String corDigitada = editCor.getText().toString();
        CorBD corBD = new CorBD(this);
        SQLiteDatabase bd = corBD.getWritableDatabase();
        try {
            ContentValues valor = new ContentValues();
            valor.put(CorBD.TabCor.COL_NOME, corDigitada);
            long idCor = bd.insert(CorBD.TabCor.TABELA, null, valor);
            if(idCor > 0) {
                Toast.makeText(this, "Cor inserida.",
                        Toast.LENGTH_SHORT).show();
                editCor.setText("");
            } else {
                Toast.makeText(this, "Erro! Cor não inserida.",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this,
                    "Erro! Detalhe: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        } finally {
            bd.close();
        }
    }


}
