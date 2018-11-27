package io.github.mobileteacher.carviewer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter {

    List<Car> carItems;


    public CarAdapter(Car[] cars){
        carItems = new ArrayList<>();
        for (Car car: cars) {
            carItems.add(car);
        }
    }

    public CarAdapter(List<Car> cars){
        carItems = cars;
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
            Car car = carItems.get(i);

            CarViewHolder carViewHolder = (CarViewHolder) viewHolder;

            carViewHolder.makeTextView.setText(car.getMake());
            carViewHolder.modelTextView.setText(car.getModel());
            carViewHolder.yearTextView.setText(String.valueOf(car.getYear()));
            carViewHolder.priceTextView.setText(String.format("R$ %.2f" ,car.getPrice()));
    }

    @Override
    public int getItemCount() {
        return carItems.size();
    }

    public void addItem(Car newCar){
        carItems.add(newCar);

        notifyItemInserted(carItems.size()-1);
    }


    public class CarViewHolder extends RecyclerView.ViewHolder{

        public TextView modelTextView;
        public TextView makeTextView;
        public TextView yearTextView;
        public TextView priceTextView;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            makeTextView = itemView.findViewById(R.id.make_name);
            modelTextView = itemView.findViewById(R.id.model_name);
            yearTextView = itemView.findViewById(R.id.year);
            priceTextView = itemView.findViewById(R.id.price);
        }
    }


}
