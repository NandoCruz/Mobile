package br.clima;

public class Clima {
    private String data;
    private double temperatura;
    private double sensacaoTermica;
    private String descricao;

    public Clima() {
    }

    public Clima(String data, double temperatura, double sensacaoTermica, String descricao) {
        this.data = data;
        this.temperatura = temperatura;
        this.sensacaoTermica = sensacaoTermica;
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = Utils.converterData( data );
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getSensacaoTermica() {
        return sensacaoTermica;
    }

    public void setSensacaoTermica(double sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
