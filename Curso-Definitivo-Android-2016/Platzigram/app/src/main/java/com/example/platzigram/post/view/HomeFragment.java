package com.example.platzigram.post.view;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import com.crashlytics.android.Crashlytics;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.platzigram.R;
import com.example.platzigram.adapter.PictureAdapterRecyclerView;
import com.example.platzigram.model.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    
    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton floatingActionButtonCamera;
    private String photoPathTemp="";
    
    public HomeFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        floatingActionButtonCamera=view.findViewById(R.id.fabCamera);
        floatingActionButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
        
        showToolbar(view.getResources().getString(R.string.title_fragment_home),false,view);

        RecyclerView picturesRecycler = view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);//Asignamos nuestro layout
        PictureAdapterRecyclerView pictureAdapterRecyclerView=new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture,getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }
    
    private void takePicture() {
        //Verificamos si el dispositivo tiene alguna camara
        if(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            Intent intentTakePicture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //Verificamos si el intent puede acceder a una camara
            if(intentTakePicture.resolveActivity(getActivity().getPackageManager())!=null){
    
                File photoFile=null;
                try{
                    photoFile=createImageFile();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    Crashlytics.logException(e);
                }
    
                if(photoFile!=null){
                    Uri photoUri= FileProvider.getUriForFile(getActivity(),getActivity().getPackageName(),photoFile);
                    intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                    startActivityForResult(intentTakePicture, REQUEST_CAMERA);
                }else{
                    Crashlytics.log(Log.DEBUG,"HomeFragment","No se puede crear la imagen temporal");
                    Toast.makeText(getContext(), "No se puede crear imagen temporal", Toast.LENGTH_LONG).show();
                }
                
            }else{
                Toast.makeText(getContext(), "No se pudo iniciar la camara", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(), "El dispositivo no cuenta con una camara", Toast.LENGTH_LONG).show();
        }
    }
    
    private File createImageFile() throws IOException {
        //Usamos la fecha y hora exacta para identificar cada imagen
        String timeStamp=new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName="JPG_"+timeStamp+"_";
        File storageDir=getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photo=File.createTempFile(imageFileName,".jpeg",storageDir);
        photoPathTemp="file:"+photo.getAbsolutePath();
        return photo;
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CAMERA && resultCode== Activity.RESULT_OK){
            Log.d("HomeFragment","Camera OK");
            Intent intent=new Intent(getActivity(),NewPostActivity.class);
            intent.putExtra("PHOTO_PATH_TEMP",photoPathTemp);
            startActivity(intent);
        }
    }
    
    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures=new ArrayList<>();
        pictures.add(new Picture("https://i.ibb.co/rkSywDf/test1.jpg","Elioth Monroy","4 días","1 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/6NMDz53/test2.jpg","Elioth Monroy","4 días","2 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/F8CZkDc/test3.jpg","Elioth Monroy","4 días","3 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/ThZYPFc/test4.jpg","Elioth Monroy","4 días","4 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/r2SyY7H/test5.jpg","Elioth Monroy","4 días","5 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/YBg2kzf/test6.jpg","Elioth Monroy","4 días","6 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/HN9p4xS/test7.jpg","Elioth Monroy","4 días","7 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/cYYR6Bt/test8.jpg","Elioth Monroy","4 días","8 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/Fb4kDGV/test9.jpg","Elioth Monroy","4 días","9 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/YQ0KDqJ/test10.jpg","Elioth Monroy","4 días","10 Me gusta"));

       /*
        https://i.ibb.co/rkSywDf/test1.jpg
        https://i.ibb.co/6NMDz53/test2.jpg
        https://i.ibb.co/F8CZkDc/test3.jpg
        https://i.ibb.co/ThZYPFc/test4.jpg
        https://i.ibb.co/r2SyY7H/test5.jpg
        https://i.ibb.co/YBg2kzf/test6.jpg
        https://i.ibb.co/HN9p4xS/test7.jpg
        https://i.ibb.co/cYYR6Bt/test8.jpg
        https://i.ibb.co/Fb4kDGV/test9.jpg
        https://i.ibb.co/YQ0KDqJ/test10.jpg
        */

       return pictures;

    }

    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
