package com.example.mecia.ric;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class LawyerClienteAdapter extends RecyclerView.Adapter<LawyerClienteHolder> {
    private final List<LawyerCliente> clientes;

    public LawyerClienteAdapter(List<LawyerCliente> clientes){
        this.clientes = clientes;
    }
    //Permite a atualização fácil e rápida de clientes na RecyclerView
    public void atualizarCliente(LawyerCliente cliente){
        clientes.set(clientes.indexOf(cliente), cliente);
        notifyItemChanged(clientes.indexOf(cliente));
    }

    public void adicionarCliente(LawyerCliente cliente){
        clientes.add(cliente);
        notifyItemInserted(getItemCount());
    }

    //Pega a posição do cliente que queremos excluir e remove da lista e dps notifica o RecyclerView

    public void removerCliente(LawyerCliente cliente){
        int position = clientes.indexOf(cliente);
        clientes.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public LawyerClienteHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LawyerClienteHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista, viewGroup, false  ));
    }

    @Override
    public void onBindViewHolder(LawyerClienteHolder holder, int i) {
        holder.numeroProcesso.setText(clientes.get(i).getNumero());
        holder.faseProcesso.setText(clientes.get(i).getFase());
        holder.obsProcesso.setText(clientes.get(i).getObs());
        final LawyerCliente cliente = clientes.get(i);

        holder.btnEditar.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = activity.getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("cliente", cliente);
                activity.finish();
                activity.startActivity(intent);
            }
        });

        holder.btnDeletar.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir este processo?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LawyerClienteDAO dao = new LawyerClienteDAO(view.getContext());
                                boolean sucesso = dao.excluir(cliente.getId());
                                if(sucesso) {
                                    removerCliente(cliente);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    Snackbar.make(view, "Erro ao excluir o cliente!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientes != null ? clientes.size() : 0;
    }



    //Método que permite obter uma Activity a partir de uma View qualquer. É necessário aqui, pois, o adapter não é uma Activity

    private Activity getActivity(View view){
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
}
