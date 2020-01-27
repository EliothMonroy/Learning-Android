package xyz.eliothmonroy.retosesion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button enviar;
    EditText edadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviar=findViewById(R.id.enviar);
        edadText=findViewById(R.id.edad);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (!edadText.getText().toString().equals("")){
                    int edad=Integer.parseInt(edadText.getText().toString());
                    if (edad>17){
                        intent=new Intent(getApplicationContext(),MayorEdadActivity.class);
                    }else{
                        intent=new Intent(getApplicationContext(),MenorEdadActivity.class);
                    }
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Por favor ingrese su edad", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
