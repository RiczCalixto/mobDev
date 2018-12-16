package io.github.mobileteacher.imagestoragesample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter
                        implements ChildEventListener{

    protected List<DataSnapshot> items;

    StorageReference storageReference = FirebaseStorage.getInstance().getReference("imagens");
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("imagens");

    public PhotoAdapter(){
        // O próprio adapter ficará monitorando alteraçoes nos dados
        items = new ArrayList<>();
        databaseReference.addChildEventListener(this);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //  Constrói uma View a partir de um XML de layout
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);
        // Associa viewholder à view
        PhotoViewHolder viewHolder = new PhotoViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        // Configura views com dados para a posição correta
        DataSnapshot dataSnapshot = items.get(i);
        // Reconstrói o objeto "photo"
        Photo photo = dataSnapshot.getValue(Photo.class);

        // Casting do viewholder para acessar atributos específicos de PhotoViewHolder
        PhotoViewHolder photoViewHolder = (PhotoViewHolder) viewHolder;
        // Configura views com seus valores
        photoViewHolder.nameTextView.setText(photo.getName());

        Log.d("DEBUG", storageReference.child(photo.getName()).getPath());

        GlideApp.with(photoViewHolder.photo)
                .load(storageReference.child(photo.getName()))
                .into(photoViewHolder.photo);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        items.add(dataSnapshot);
        notifyItemInserted(items.size()-1);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;
        public TextView nameTextView;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photoView);
            nameTextView = itemView.findViewById(R.id.image_name);

        }
    }
}
