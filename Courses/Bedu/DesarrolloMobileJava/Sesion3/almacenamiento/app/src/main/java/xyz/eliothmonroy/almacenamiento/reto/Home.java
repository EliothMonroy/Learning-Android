package xyz.eliothmonroy.almacenamiento.reto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import xyz.eliothmonroy.almacenamiento.R;

public class Home extends AppCompatActivity {
    
    LinearLayout paso1, paso2, paso3, registro, home, navegacion;
    Button anterior, siguiente, registrar, continuar, borrarShared;
    EditText correo;
    TextView usuarioEmail;
    FrameLayout frameLayout;
    CheckBox checkboxTutorial;
    SharedPreferences sharedPreferences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Binding de views
        frameLayout = findViewById(R.id.relative);
        paso1 = findViewById(R.id.paso1);
        paso2 = findViewById(R.id.paso2);
        paso3 = findViewById(R.id.paso3);
        registro = findViewById(R.id.registro);
        home = findViewById(R.id.home);
        navegacion = findViewById(R.id.navegacion);
        anterior = findViewById(R.id.anterior);
        siguiente = findViewById(R.id.siguiente);
        registrar = findViewById(R.id.registrar);
        correo = findViewById(R.id.correo);
        usuarioEmail = findViewById(R.id.usuarioEmail);
        continuar = findViewById(R.id.continuar);
        checkboxTutorial = findViewById(R.id.checkboxTutorial);
        borrarShared=findViewById(R.id.borrarShared);
        
        //Obtenemos sharedpreferences
        sharedPreferences = getSharedPreferences(getString(R.string.nombre_shared_preferences), Context.MODE_PRIVATE);
        
        //Validamos si el usuario seleccionó saltar tutorial
        if (saltarTutorial()) {
            mostrarHome(paso1);
        } else {
            anterior.setEnabled(false);
            siguiente.setEnabled(true);
            
            anterior.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    regresarPaso();
                }
            });
            
            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    avanzarPaso();
                }
            });
            
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sharedPreferences.getString(getString(R.string.llaveShared), "").equals("")) {
                        paso3.setVisibility(View.INVISIBLE);
                        navegacion.setVisibility(View.INVISIBLE);
                        registro.setVisibility(View.VISIBLE);
                    } else {
                        mostrarHome(paso3);
                    }
                }
            });
            
            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences.edit().putString(getString(R.string.llaveShared), correo.getText().toString()).apply();
                    mostrarHome(registro);
                }
            });
        }
    
        checkboxTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkboxTutorial.isChecked()) {
                    sharedPreferences.edit().remove(getString(R.string.llaveTutorial)).apply();
                } else {
                    sharedPreferences.edit().putBoolean(getString(R.string.llaveTutorial), true).apply();
                }
            }
        });
        
        borrarShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().clear().apply();
            }
        });
        
    }
    
    private void mostrarHome(LinearLayout paso) {
        paso.setVisibility(View.INVISIBLE);
        navegacion.setVisibility(View.GONE);
        home.setVisibility(View.VISIBLE);
        usuarioEmail.setText(getString(R.string.texto_home_correo_usuario, sharedPreferences.getString(getString(R.string.llaveShared), "default")));
        checkboxTutorial.setChecked(sharedPreferences.getBoolean(getString(R.string.llaveTutorial),false));
    }
    
    private boolean saltarTutorial() {
        return sharedPreferences.getBoolean(getString(R.string.llaveTutorial), false);
    }
    
    private void regresarPaso() {
        siguiente.setEnabled(true);
        for (int i = 0; i < 3; i++) {
            if (frameLayout.getChildAt(i).getVisibility() == View.VISIBLE) {
                frameLayout.getChildAt(i).setVisibility(View.INVISIBLE);
                frameLayout.getChildAt(i - 1).setVisibility(View.VISIBLE);
                if (i == 1) {
                    anterior.setEnabled(false);
                }
                break;
            }
        }
    }
    
    private void avanzarPaso() {
        anterior.setEnabled(true);
        for (int i = 0; i < 3; i++) {
            if (frameLayout.getChildAt(i).getVisibility() == View.VISIBLE) {
                frameLayout.getChildAt(i).setVisibility(View.INVISIBLE);
                frameLayout.getChildAt(i + 1).setVisibility(View.VISIBLE);
                if (i == 1) {
                    siguiente.setEnabled(false);
                }
                break;
            }
        }
    }
    
}
