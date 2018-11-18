package com.example.mecia.customarrayadapterteste;


import android.widget.ImageView;

import java.lang.reflect.Constructor;
import java.security.PublicKey;

public class Filmes {

    private int idFilme;
    private String nomeFilme;
    private String lançamentoFilme;

    public Filmes(int idFilme, String nomeFilme, String lançamentoFilme){
        this.idFilme = idFilme;
        this.nomeFilme = nomeFilme;
        this.lançamentoFilme = lançamentoFilme;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme (String nomeFilme){
        this.nomeFilme = nomeFilme;
    }

    public String getLançamentoFilme() {
        return lançamentoFilme;
    }

    public void setLançamentoFilme(String lançamentoFilme){
        this.lançamentoFilme = lançamentoFilme;
    }


}
