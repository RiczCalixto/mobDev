package io.github.mobileteacher.carviewer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter {

    List<DataSnapshot> items;
    public CarAdapter(List<DataSnapshot> cars){
        items = cars;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.car_element_layout, viewGroup, false);

        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            Car car = items.get(i).getValue(Car.class);

            CarViewHolder carViewHolder = (CarViewHolder) viewHolder;

            carViewHolder.makeTextView.setText(car.getMake());
            carViewHolder.modelTextView.setText(car.getModel());
            carViewHolder.yearTextView.setText(String.valueOf(car.getYear()));
            carViewHolder.priceTextView.setText(String.format("R$ %.2f" ,car.getPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(DataSnapshot newCar){
        items.add(newCar);

        notifyItemInserted(items.size()-1);
    }


    public void changeItem(DataSnapshot changed){
        for (int i = 0; i < items.size(); i++) {
            if (changed.getKey().equals(items.get(i).getKey())){
                items.set(i, changed);
                notifyItemChanged(i);
            }
        }
    }

    public void removeItem(DataSnapshot removed){
        for (int i = 0; i < items.size(); i++) {
            if (removed.getKey().equals(items.get(i).getKey())){
                items.remove(i);
                notifyItemRemoved(i);
            }
        }
    }


    public class CarViewHolder extends RecyclerView.ViewHolder{

        public TextView modelTextView;
        public TextView makeTextView;
        public TextView yearTextView;
        public TextView priceTextView;
        public Button deleteButton;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            makeTextView = itemView.findViewById(R.id.make_name);
            modelTextView = itemView.findViewById(R.id.model_name);
            yearTextView = itemView.findViewById(R.id.year);
            priceTextView = itemView.findViewById(R.id.price);
            deleteButton = itemView.findViewById(R.id.delete_button);


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    String idToRemove = items.get(index).getKey();

                    DatabaseReference ref = FirebaseDatabase
                                                .getInstance()
                                                .getReference("Cars")
                                                .child(idToRemove);
                    ref.removeValue();
                }
            });
        }
    }


}
