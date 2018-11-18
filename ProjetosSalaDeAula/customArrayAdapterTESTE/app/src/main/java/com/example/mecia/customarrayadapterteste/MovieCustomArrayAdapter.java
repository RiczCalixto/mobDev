package com.example.mecia.customarrayadapterteste;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieCustomArrayAdapter extends ArrayAdapter<Filmes> {

    private Context mContext;
    private List<Filmes> filmesList = new ArrayList<>();

    public MovieCustomArrayAdapter(@NonNull Context context, @LayoutRes ArrayList<Filmes> list) {
        super(context, 0, list);
        mContext = context;
        filmesList = list;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        Filmes filmeAtual = filmesList.get(position);

        ImageView image = (ImageView) listItem.findViewById(R.id.imageView);
        image.setImageResource(filmeAtual.getIdFilme());

        TextView name = (TextView) listItem.findViewById(R.id.textView);
        name.setText(filmeAtual.getNomeFilme());

        TextView lançamento = (TextView) listItem.findViewById(R.id.textView2);
        lançamento.setText((filmeAtual.getLançamentoFilme()));

        return listItem;



    }
}
