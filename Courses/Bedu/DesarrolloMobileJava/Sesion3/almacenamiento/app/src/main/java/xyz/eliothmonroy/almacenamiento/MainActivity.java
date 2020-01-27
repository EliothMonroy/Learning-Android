package xyz.eliothmonroy.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        borrar=findViewById(R.id.btnBorrar);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar();
            }
        });

        sharedPreferences=getSharedPreferences(getString(R.string.llaveSharedPreferences), Context.MODE_PRIVATE);
        if(leerPreferencias()){
            Toast.makeText(this,"Ya iniciaste sesión", Toast.LENGTH_LONG).show();
        }else{
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(getString(R.string.llaveCorreo),"elioth@gmail.com");
            editor.apply();
            Toast.makeText(this,"No has iniciado sesión", Toast.LENGTH_LONG).show();
        }
    }

    private void borrar() {
        sharedPreferences.edit().clear().apply();
    }

    Boolean leerPreferencias(){
        String correo=sharedPreferences.getString(getString(R.string.llaveCorreo),"");
        return !correo.equals("");
    }

}
