package com.example.p07_listacores_recyclerview_bd_ads_noite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listaCoresRV;
    private CorAdapter corAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCoresRV = findViewById(R.id.lista_cores_rv);
        LinearLayoutManager m = new LinearLayoutManager(this);
        m.setOrientation(RecyclerView.VERTICAL);
        listaCoresRV.setHasFixedSize(true);
        listaCoresRV.setLayoutManager(m);

        carregarDados();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarDados();
    }

    private void carregarDados() {
        CorBD corBD = new CorBD(this);
        SQLiteDatabase bd = corBD.getReadableDatabase();
        Cursor dados = bd.query(CorBD.TabCor.TABELA,
                null,
                null,
                null,
                null,
                null,
                CorBD.TabCor.COL_NOME);
        List listaCores = new ArrayList<>();
        while (dados.moveToNext()) {
            String cor = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_NOME));
            listaCores.add(cor);
        }
        bd.close();
        corAdapter = new CorAdapter(listaCores);
        listaCoresRV.setAdapter( corAdapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemClicado = item.getItemId();
        switch (itemClicado) {
            case R.id.acao_adicionar:
                Intent i = new Intent(this, InserirCorActivity.class);
                startActivity( i );
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
