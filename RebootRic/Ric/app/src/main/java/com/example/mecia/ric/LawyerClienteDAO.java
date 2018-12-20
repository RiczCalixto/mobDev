package com.example.mecia.ric;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class LawyerClienteDAO {

    private final String TABLE_CLIENTS = "Clientes";
    private LawyerDbGateway gw;

    public LawyerClienteDAO(Context ctx){
        gw = LawyerDbGateway.getInstance(ctx);
    }

    public boolean salvar (String numero, String fase, String obs){
        return salvar(0, numero, fase, obs);
    }

    public boolean salvar(int id, String numero, String fase, String obs){
        ContentValues cv = new ContentValues();

        cv.put("Numero", numero);
        cv.put("Fase", fase);
        cv.put("Obs", obs);
        if(id>0) {
            return gw.getDatabase().update(TABLE_CLIENTS, cv, "ID=?", new String[]{id + ""}) > 0;
        }else {
            return gw.getDatabase().insert(TABLE_CLIENTS, null, cv) > 0;
        }

    }

    public boolean excluir(int id){
        return gw.getDatabase().delete(TABLE_CLIENTS, "ID=?", new String[]{ id+ ""}) > 0;
    }

    //Retorna todos os clientes armazenados no banco de dados

    public List<LawyerCliente> retornarTodos(){
        List<LawyerCliente> clientes = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String numero = cursor.getString(cursor.getColumnIndex("Numero"));
            String fase = cursor.getString(cursor.getColumnIndex("Fase"));
            String obs = cursor.getString(cursor.getColumnIndex("Obs"));
            clientes.add(new LawyerCliente(id, numero, fase, obs));
        }
        cursor.close();
        return clientes;
    }

    //Retornar último Cliente inserido no banco de dados
    public LawyerCliente retornarUltimo() {
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes ORDER BY ID DESC", null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String numero = cursor.getString(cursor.getColumnIndex("Numero"));
            String fase = cursor.getString(cursor.getColumnIndex("Fase"));
            String obs = cursor.getString(cursor.getColumnIndex("Obs"));
            cursor.close();
            return new LawyerCliente(id, numero, fase, obs);
        }

        return null;
    }
}
