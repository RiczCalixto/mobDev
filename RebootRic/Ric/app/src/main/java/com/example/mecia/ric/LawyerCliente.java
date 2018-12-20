package com.example.mecia.ric;

import java.io.Serializable;

public class LawyerCliente implements Serializable {

    private int id;
    private String numero;
    private String fase;
    private String obs;

    public LawyerCliente(int id, String numero, String fase, String obs){
        this.id = id;
        this.numero = numero;
        this.fase = fase;
        this.obs = obs;
    }

    public int getId(){return this.id;}
    public String getNumero(){ return this.numero; }
    public String getFase(){ return this.fase; }
    public String getObs(){ return this.obs; }

    @Override
    public boolean equals(Object o){
        return this.id == ((LawyerCliente)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}
