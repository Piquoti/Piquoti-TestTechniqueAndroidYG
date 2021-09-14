package com.example.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import static java.lang.String.valueOf;

public class myAdapter extends BaseAdapter {

    ArrayList<Contact> contactList;
    ArrayList<Bitmap> photoList;
    Context context;

    public myAdapter(ArrayList<Contact> contactList, ArrayList<Bitmap> photoList, Context context) {
        this.contactList = contactList;
        this.photoList = photoList;
        this.context = context;
    }

    public int getCount(){return contactList.size();}
    public Object getItem(int position){return position;}
    public long getItemId(int position){return position;}

    public View getView(int position, View convertView, ViewGroup parent){
        ConstraintLayout layoutItem;
        LayoutInflater mInflater = LayoutInflater.from(context);

        if(convertView == null){
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.contact,parent,false);
        }else{
            layoutItem = (ConstraintLayout) convertView;
        }
        MyViewHolder viewHolder = (MyViewHolder) layoutItem.getTag();
        if(viewHolder == null){
            viewHolder = new MyViewHolder();

            viewHolder.tvName = (TextView) layoutItem.findViewById(R.id.tvName);
            viewHolder.tvSurname = (TextView) layoutItem.findViewById(R.id.tvSurname);
            viewHolder.tvFullname = (TextView) layoutItem.findViewById(R.id.tvFullname);
            viewHolder.tvAge = (TextView) layoutItem.findViewById(R.id.tvAge);
            viewHolder.tvMail = (TextView) layoutItem.findViewById(R.id.tvMail);

            layoutItem.setTag(viewHolder);
        }

        viewHolder.tvName.setText(contactList.get(position).getName());
        viewHolder.tvSurname.setText(contactList.get(position).getSurname());
        viewHolder.tvFullname.setText(contactList.get(position).getFullname());
        viewHolder.tvAge.setText(valueOf(contactList.get(position).getAge()));
        viewHolder.tvMail.setText(contactList.get(position).getMail());
        System.out.println(photoList.get(position));
        

        return layoutItem;
    }

    private static class MyViewHolder {
        public TextView tvName,tvSurname, tvFullname, tvAge, tvMail;
        public ImageView ivPhoto;
    }
}
