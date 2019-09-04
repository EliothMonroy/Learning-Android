package com.example.platzigram.view;

import android.os.Build;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.transition.Fade;
import android.view.WindowManager;

import com.example.platzigram.R;

public class PictureDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("",true);

        //Verificamos que sea la versión 5+
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            //Agregamos la transición de entrada
            getWindow().setEnterTransition(new Fade());
            //Para poner el status bar transparente
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar=findViewById(R.id.toolbarPictureDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsingToolbar);
    }

}
