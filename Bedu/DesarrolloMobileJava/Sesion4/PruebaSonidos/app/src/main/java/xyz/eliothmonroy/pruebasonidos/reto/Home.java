package xyz.eliothmonroy.pruebasonidos.reto;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import xyz.eliothmonroy.pruebasonidos.R;

public class Home extends AppCompatActivity {

    ImageView caballo, cabra, cerdo, gallo, mono, perro, serpiente, tigre;

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
        switch (view.getId()){
            case R.id.caballo: MediaPlayer.create(this,R.raw.caballo).start(); break;
            case R.id.cabra: MediaPlayer.create(this,R.raw.cabra).start(); break;
            case R.id.cerdo: MediaPlayer.create(this,R.raw.cerdo).start(); break;
            case R.id.gallo: MediaPlayer.create(this,R.raw.gallo).start(); break;
            case R.id.mono: MediaPlayer.create(this,R.raw.mono).start(); break;
            case R.id.perro: MediaPlayer.create(this,R.raw.perro).start(); break;
            case R.id.serpiente: MediaPlayer.create(this,R.raw.serpiente).start(); break;
            case R.id.tigre: MediaPlayer.create(this,R.raw.tigre).start(); break;
            default: break;
        }
    }
}
