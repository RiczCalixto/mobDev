package io.github.mobileteacher.carviewer;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity {


    private RecyclerView carRecyclerView;

    private final int ADD_CAR_REQUEST_CODE = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        carRecyclerView = findViewById(R.id.recyclerview);

//        Car[] carNames = {
//                new Car("Ford","Fiesta", 1996, 10000.00),
//                new Car("Toyota", "Corolla", 2009, 21000.76),
//                new Car("Volkswagen", "Gol", 2012, 18543.98),
//                new Car("Fiat", "Uno", 2018, 42000.42)};


        List<Car> carNames = readCarsFromFile();

        //Criar um Adapter para a RecyclerView
        CarAdapter adapter = new CarAdapter(carNames);
        //associar RecyclerView a um Adapter
        carRecyclerView.setAdapter(adapter);
        //Dizer a "forma" da RecyclerView
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        carRecyclerView.setLayoutManager(lm);

        carRecyclerView.addItemDecoration(
                            new DividerItemDecoration(this,
                                        DividerItemDecoration.VERTICAL));

    }

    public void addCar(View view){
        Intent intent = new Intent(this, AddNewCarActivity.class);
        startActivityForResult(intent, ADD_CAR_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CAR_REQUEST_CODE && resultCode == RESULT_OK){
            String make = data.getStringExtra(AddNewCarActivity.MAKE_KEY);
            String model = data.getStringExtra(AddNewCarActivity.MODEL_KEY);
            int year =data.getIntExtra(AddNewCarActivity.YEAR_KEY, 0);
            double price = data.getDoubleExtra(AddNewCarActivity.PRICE_KEY, 0.0);

            Car newCar = new Car(make, model, year ,price);

            CarAdapter adapter = (CarAdapter) carRecyclerView.getAdapter();
            adapter.addItem(newCar);

            saveCarToFile(newCar);
        }
    }


    public void saveCarToFile(Car car){
        File myFile = new File(getFilesDir(), "savedcars.txt");

        try {
            FileOutputStream outputStream = new FileOutputStream(myFile, true);
            outputStream.write(car.toString().getBytes());
            outputStream.close();
        } catch (FileNotFoundException exception){
            //
        } catch (IOException exception){

        }
    }

    public List<Car> readCarsFromFile(){
        File myFile = new File(getFilesDir(), "savedcars.txt");
        List<Car> cars = new ArrayList<>();

        try {
            FileReader reader = new FileReader(myFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null){
                if (line.equals("#")){
                    String make = bufferedReader.readLine();
                    String model = bufferedReader.readLine();
                    int year = Integer.parseInt(bufferedReader.readLine());
                    double price = Double.parseDouble(bufferedReader.readLine());
                    Car newCar = new Car(make, model, year, price);
                    cars.add(newCar);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException exception){
            //
        } catch (IOException exception){

        }

        return cars;
    }
}
