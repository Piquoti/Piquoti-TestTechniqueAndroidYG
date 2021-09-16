package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class myAdapter extends BaseAdapter {

    ArrayList<Contact> contactList;
    ArrayList<Bitmap> photoList;
    Context context;

    /**
     *
     * Il ne faut pas instancier une nouvelle activity. Sinon ça cause un crash quand on clique sur le bouton.
     * Les activity ne s'instancient pas "manuellement", c'est au système de le faire en passant par un Intent.
     * Ici le crash était du au fait que l'activity créée manuellement n'était pas rattachée au Thread de l'app.
     *
     */
//    MainActivity ma = new MainActivity();

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
            viewHolder.ivPhoto = (ImageView) layoutItem.findViewById(R.id.ivPhoto);

            layoutItem.setTag(viewHolder);
        }

        viewHolder.tvName.setText(contactList.get(position).getName());
        viewHolder.tvSurname.setText(contactList.get(position).getSurname());
        viewHolder.tvFullname.setText(contactList.get(position).getFullname());
        viewHolder.tvAge.setText(valueOf(contactList.get(position).getAge()));
        viewHolder.tvMail.setText(contactList.get(position).getMail());
        //viewHolder.ivPhoto.setImageBitmap(photoList.get(position)); l'affichage de l'image ne fonctionne pas
        /**
         * Pour le chargement d'image, le mieux est de passer par une lib (Picasso, Glide, Fresco etc...)
         */
        Picasso.get()
                .load(contactList.get(position).getPhoto())
                .resize(50, 50)
                .centerCrop()
                .into(viewHolder.ivPhoto);

        Button button = layoutItem.findViewById(R.id.btnContacter);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                System.out.println("adapter mail : " + contactList.get(position).getMail());
                if(context instanceof MainActivity){
                    ((MainActivity) context).mailSend(contactList.get(position).getMail());
                }
            }
        });


        return layoutItem;
    }

    private static class MyViewHolder {
        public TextView tvName,tvSurname, tvFullname, tvAge, tvMail;
        public ImageView ivPhoto;
    }

}
