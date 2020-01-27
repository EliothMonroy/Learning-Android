package xyz.eliothmonroy.appsesion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num1,num2;
    Button sumar, restar, multi, dividir;
    final int[] OPERACIONES = {1, 2, 3, 4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        sumar=findViewById(R.id.sumar);
        restar=findViewById(R.id.restar);
        multi=findViewById(R.id.multi);
        dividir=findViewById(R.id.dividir);

        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion(OPERACIONES[0]);
            }
        });
        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion(OPERACIONES[1]);
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion(OPERACIONES[2]);
            }
        });
        dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion(OPERACIONES[3]);
            }
        });
    }

    public void operacion(int tipo){
        float uno=Float.parseFloat(num1.getText().toString());
        float dos=Float.parseFloat(num2.getText().toString());
        float res=0f;
        if (tipo==OPERACIONES[0]){
            res=uno+dos;
        }else if(tipo==OPERACIONES[1]){
            res=uno-dos;
        }else if(tipo==OPERACIONES[2]){
            res=uno*dos;
        }else if(tipo==OPERACIONES[3]){
            res=uno/dos;
        }
        Toast.makeText(getApplicationContext(),"Resultado operación: "+res,Toast.LENGTH_LONG).show();
    }

}
