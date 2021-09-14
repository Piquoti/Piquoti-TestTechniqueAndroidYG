package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.listView);
        Button btn = findViewById(R.id.btnContacter);
        ArrayList<ArrayList> list = new ArrayList<>();


        Data task = new Data();
        try {
            list = task.read(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        myAdapter adapter = new myAdapter(list.get(0), list.get(1), this);
        lv.setAdapter(adapter);
    }
}