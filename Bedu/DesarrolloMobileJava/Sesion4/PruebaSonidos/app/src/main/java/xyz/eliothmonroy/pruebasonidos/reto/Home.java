package xyz.eliothmonroy.pruebasonidos.reto;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import xyz.eliothmonroy.pruebasonidos.R;

public class Home extends AppCompatActivity {

    ImageView caballo, cabra, cerdo, gallo, mono, perro, serpiente, tigre;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        caballo=findViewById(R.id.caballo);
        cabra=findViewById(R.id.cabra);
        cerdo=findViewById(R.id.cerdo);
        gallo=findViewById(R.id.gallo);
        mono=findViewById(R.id.mono);
        perro=findViewById(R.id.perro);
        serpiente=findViewById(R.id.serpiente);
        tigre=findViewById(R.id.tigre);
    }

    public void hacerSonido(View view) {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
        switch (view.getId()){
            case R.id.caballo: mediaPlayer=MediaPlayer.create(this,R.raw.caballo);break;
            case R.id.cabra: mediaPlayer=MediaPlayer.create(this,R.raw.cabra);break;
            case R.id.cerdo: mediaPlayer=MediaPlayer.create(this,R.raw.cerdo); break;
            case R.id.gallo: mediaPlayer=MediaPlayer.create(this,R.raw.gallo); break;
            case R.id.mono: mediaPlayer=MediaPlayer.create(this,R.raw.mono); break;
            case R.id.perro: mediaPlayer=MediaPlayer.create(this,R.raw.perro); break;
            case R.id.serpiente: mediaPlayer=MediaPlayer.create(this,R.raw.serpiente); break;
            case R.id.tigre: mediaPlayer=MediaPlayer.create(this,R.raw.tigre); break;
            default: break;
        }
        mediaPlayer.start();
    }
}
