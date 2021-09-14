package com.example.test;

import android.content.Context;
import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class Data {

    ArrayList<ArrayList> list = new ArrayList<>();
    ArrayList<Bitmap> photoList = new ArrayList<>();
    HashMap<String, Object> map;

    public ArrayList<ArrayList> read(Context context) throws IOException{
        ArrayList<Contact> contactList = new ArrayList<>();
        InputStream instream = context.getResources().openRawResource(R.raw.technical_test);
        InputStreamReader isr = new InputStreamReader(instream);
        BufferedReader buf = new BufferedReader(isr);
        StringBuilder strb = new StringBuilder();
        String tmp;

        while((tmp = buf.readLine()) != null){
            strb.append(tmp);
        }
        String jsonStr = strb.toString();

        try{
            JSONObject jsonRoot = new JSONObject(jsonStr);

            JSONArray jsonData = jsonRoot.getJSONArray("items");
            for(int i = 0; i<jsonData.length(); i++){
                JSONObject c = jsonData.getJSONObject(i);

                int age = c.getInt("age");
                String name = c.getString("name");
                String surname = c.getString("surname");
                String fullname = c.getString("fullname");
                String mail = c.getString("email");
                String photo = c.getString("photo");

                AsyncTaskBitmap task = new AsyncTaskBitmap();
                task.execute(photo);
                Bitmap myBitmap = task.get();
                photoList.add(myBitmap);

                Contact contact = new Contact(name,surname,fullname,mail,photo,age);
                contactList.add(contact);

                list.add(contactList);
                list.add(photoList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return list;
    }
}
