package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=b23johgu";

    private Plants[] plants;
    Gson gson = new Gson();
    ArrayList<Plants> items = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @SuppressWarnings("SameParameterValue")
    private String readFile(String plants) {
        try {
            //noinspection CharsetObjectCanBeUsed
            return new Scanner(getApplicationContext().getAssets().open(plants), Charset.forName("UTF-8").name()).useDelimiter("\\A").next();
        } catch (IOException e) {

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Plants item) {
                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);



        new JsonTask(this).execute(JSON_URL);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Type type = new TypeToken<List<Plants>>() {}.getType();
        List<Plants> listOfPlants = gson.fromJson(json, type);
        items.addAll(listOfPlants);
        adapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }

}
