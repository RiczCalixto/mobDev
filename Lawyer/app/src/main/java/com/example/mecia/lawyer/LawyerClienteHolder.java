package com.example.mecia.lawyer;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LawyerClienteHolder extends RecyclerView.ViewHolder {

    public TextView numeroProcesso;
    public FloatingActionButton btnEditar;
    public FloatingActionButton btnDeletar;

    public LawyerClienteHolder(View itemView){
        super(itemView);
        numeroProcesso = (TextView) itemView.findViewById(R.id.numeroTextView);
        btnEditar = (FloatingActionButton) itemView.findViewById(R.id.editarBotao);
        btnDeletar = (FloatingActionButton) itemView.findViewById(R.id.deletarBotao);
    }

}
