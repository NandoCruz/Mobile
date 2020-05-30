package br.clima;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class ClimaService extends AsyncTask<String, Void, List<Clima>> {
    private AsyncTaskDelegate delegate;
    private Context context;
    private ProgressBar pbClima;

    public ClimaService(Context context, AsyncTaskDelegate delegate) {
        this.delegate = delegate;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // mostrar uma barra de progresso
        pbClima = ((MainActivity) context).findViewById(R.id.pb_clima);
        pbClima.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<Clima> doInBackground(String... params) {
        // acessar a API e pegar os dados
        try {
            String local = params[0];
            String appid = params[1];
            String qtd   = params[2];
            String urlAPI = "https://api.openweathermap.org/data/2.5/forecast?q=" + local
                    + "&appid=" + appid + "&cnt=" + qtd + "&units=metric&lang=pt_br";
            String dadosAPI = Jsoup.connect(urlAPI).ignoreContentType(true).execute().body();
            JSONObject jsonAPI = new JSONObject(dadosAPI);
            JSONArray vetorClimaAPI = jsonAPI.getJSONArray("list");
            List<Clima> listaClima = new ArrayList<>();
            for (int i = 0; i < vetorClimaAPI.length(); i++) {
                Clima c = new Clima();
                try {
                    JSONObject objExterno = vetorClimaAPI.getJSONObject(i);
                    c.setData( objExterno.getString("dt_txt") );
                    JSONObject objMain = objExterno.getJSONObject("main");
                    c.setTemperatura( objMain.getDouble("temp") );
                    c.setSensacaoTermica( objMain.getDouble("feels_like") );

                    JSONArray vetorWeather = objExterno.getJSONArray("weather");
                    for (int j = 0; j < vetorWeather.length(); j++) {
                        JSONObject objWeather = vetorWeather.getJSONObject(j);
                        c.setDescricao( objWeather.getString("description") );
                    }
                    listaClima.add( c );
                } catch (Exception e) {
                    Toast.makeText(context, "Erro ao converter a API de dados.", Toast.LENGTH_SHORT).show();
                }
            } // fim do for
            return listaClima;
        } catch(Exception e) {
            Toast.makeText(context, "Erro ao acessar a API de dados.", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Clima> climas) {
        super.onPostExecute(climas);
        if(delegate != null) {
            delegate.processFinish(climas);
        }
        // esconder a barra de progresso
        pbClima.setVisibility(View.GONE);
    }
}
