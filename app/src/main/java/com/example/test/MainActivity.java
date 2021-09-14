package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
        Button button = findViewById(R.id.btnContacter);
        ArrayList<ArrayList> list = new ArrayList<>();


        Data task = new Data();
        try {
            list = task.read(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Contact> contactList = list.get(0);
        ArrayList<Bitmap> photoList = list.get(1);

        myAdapter adapter = new myAdapter(contactList, photoList, this);
        lv.setAdapter(adapter);
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void mailSend(String mail){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL,mail);
        intent.setData(Uri.parse("mailto:"));
        startActivity(intent);
    }
}