package com.example.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.platzigram.R;
import com.example.platzigram.model.Picture;
import com.example.platzigram.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

    private ArrayList<Picture> pictures; //Arreglo de imagenes
    private int resource;
    private Activity activity; //Desde dónde vemos la imagen

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);

        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PictureViewHolder pictureViewHolder, int i) {

        Picture picture=pictures.get(i);//Recorremos la lista por cada elemento
        pictureViewHolder.usernamecard.setText(picture.getUserName());
        pictureViewHolder.timeCard.setText(picture.getTime());
        pictureViewHolder.likeNumberCard.setText(picture.getLikes_number());

        //Obtenemos la imagen vía Picasso
        Picasso.get().load(picture.getPicture()).into(pictureViewHolder.pictureCard);

        //Agregamos listener para cuando el usuario presione sobre la imagen
        pictureViewHolder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,PictureDetailActivity.class);

                //Las transiciones deben de ser agregadas en la parte de código
                //donde se va a llamar a la otra actividad
                //Primero verificamos que el dispositivo tenga Android 5 o mayor
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    Explode explode=new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);

                    //Para llamar a la otra actividad, indicamos que habrá una transición de entrada
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity,v,activity.getString(R.string.transitionname_picture)).toBundle());
                }else{
                    //Iniciamos actividad sin transición
                    activity.startActivity(intent);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return pictures.size(); //Este método regresa el total de elementos
    }

    //Nuestro objeto adapter para el card
    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView usernamecard;
        private TextView timeCard;
        private TextView likeNumberCard;


        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureCard=itemView.findViewById(R.id.pictureCard);
            usernamecard=itemView.findViewById(R.id.usernameCard);
            timeCard=itemView.findViewById(R.id.timeCard);
            likeNumberCard=itemView.findViewById(R.id.likeNumberCard);

        }
    }



}
