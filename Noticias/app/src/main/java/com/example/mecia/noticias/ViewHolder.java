package com.example.mecia.noticias;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView itemViewH;
    public ImageButton addButton;
    public ImageButton deleteButton;

    public ViewHolder(View itemView) {
        super(itemView);

        itemViewH = (TextView) itemView.findViewById(R.id.itemViewH);
        addButton = (ImageButton) itemView.findViewById(R.id.addBtn);
        deleteButton = (ImageButton) itemView.findViewById(R.id.removeBtn);

    }
}
