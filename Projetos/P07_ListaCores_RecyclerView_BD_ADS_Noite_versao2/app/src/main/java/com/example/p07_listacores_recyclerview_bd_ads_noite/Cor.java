package com.example.p07_listacores_recyclerview_bd_ads_noite;

public class Cor {
    private int id;
    private String nome;

    public Cor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
