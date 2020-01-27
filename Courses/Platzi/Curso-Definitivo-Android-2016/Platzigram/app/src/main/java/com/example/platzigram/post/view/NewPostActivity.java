package com.example.platzigram.post.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.platzigram.PlatzigramApplication;
import com.example.platzigram.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class NewPostActivity extends AppCompatActivity {
    
    private static final String TAG = "NewPostActivity";
    private ImageView imgPhoto;
    private Button buttonCreatePost;
    private String photoPath;
    private PlatzigramApplication application;
    private StorageReference storageReference;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        buttonCreatePost=findViewById(R.id.createPost);
        imgPhoto=findViewById(R.id.imgPhoto);
        if(getIntent().getExtras()!=null){
            photoPath=getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            showPhoto();
        }
        application=(PlatzigramApplication) getApplication();
        storageReference=application.getFirebaseStorageReference();
        
        buttonCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });
        
    }
    
    private void uploadPhoto() {
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();
        Bitmap bitmap=imgPhoto.getDrawingCache();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] photoByte=byteArrayOutputStream.toByteArray();
        String photoName=photoPath.substring(photoPath.lastIndexOf("/")+1);
        final StorageReference photoReference=storageReference.child("images").child(photoName);
        UploadTask uploadTask=photoReference.putBytes(photoByte);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG,"Error al subir la imagen a firebase: "+e.toString());
            }
        });
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                photoReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url=uri.toString();
                        Log.w(TAG,"Exito al subir la imagen a firebase, url: "+url);
                        finish();
                    }
                });
            }
        });
        
    }
    
    private void showPhoto(){
        Picasso.get().load(photoPath).into(imgPhoto);
    }
}
