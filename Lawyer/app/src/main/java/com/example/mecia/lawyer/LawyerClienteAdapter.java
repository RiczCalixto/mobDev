package com.example.mecia.lawyer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class LawyerClienteAdapter extends RecyclerView.Adapter<LawyerClienteHolder> {
    private final List<LawyerCliente> clientes;

    public LawyerClienteAdapter(List<LawyerCliente> clientes){
        this.clientes = clientes;
    }

    @Override
    public LawyerClienteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LawyerClienteHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista, viewGroup, false  ));
    }

    @Override
    public void onBindViewHolder(@NonNull LawyerClienteHolder lawyerClienteHolder, int i) {
        lawyerClienteHolder.numeroProcesso.setText(clientes.get(i).getNumero());
    }



    @Override
    public int getItemCount() {
        return clientes != null ? clientes.size() : 0;
    }
}
