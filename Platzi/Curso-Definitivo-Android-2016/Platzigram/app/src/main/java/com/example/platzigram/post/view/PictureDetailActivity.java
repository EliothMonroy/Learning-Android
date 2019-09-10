package com.example.platzigram.post.view;

import android.net.Uri;
import android.os.Build;

import com.example.platzigram.PlatzigramApplication;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.platzigram.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {
    
    private static final String PHOTO_NAME = "JPG_20190907_17-22-33_6094503746344208883.jpeg";
    private static final String TAG = "PictureDetailActivity";
    private ImageView imageViewPost;
    private PlatzigramApplication application;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("",true);
        application=(PlatzigramApplication)getApplication();
        storageReference=application.getFirebaseStorageReference();
        imageViewPost=findViewById(R.id.imageHeader);
        
        //Verificamos que sea la versión 5+
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            //Agregamos la transición de entrada
            getWindow().setEnterTransition(new Fade());
            //Para poner el status bar transparente
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        
        showData();

    }
    
    private void showData() {
        storageReference.child("images").child(PHOTO_NAME).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri.toString()).into(imageViewPost);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG,"Error al descargar la imagen: "+e.toString());
            }
        });
    }
    
    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar=findViewById(R.id.toolbarPictureDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsingToolbar);
    }

}
