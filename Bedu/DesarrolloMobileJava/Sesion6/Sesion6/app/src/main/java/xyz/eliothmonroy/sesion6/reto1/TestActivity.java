package xyz.eliothmonroy.sesion6.reto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import xyz.eliothmonroy.sesion6.R;

public class TestActivity extends AppCompatActivity {

    RadioGroup pregunta2_r, pregunta3_r, pregunta4_r, pregunta6_r;
    LinearLayout pregunta1_r, pregunta5_r;
    TextView resultado;
    Button terminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        terminar=findViewById(R.id.terminar);
        resultado=findViewById(R.id.resultado);
        pregunta1_r=findViewById(R.id.pregunta1_r);
        pregunta2_r=findViewById(R.id.pregunta2_r);
        pregunta3_r=findViewById(R.id.pregunta3_r);
        pregunta4_r=findViewById(R.id.pregunta4_r);
        pregunta5_r=findViewById(R.id.pregunta5_r);
        pregunta6_r=findViewById(R.id.pregunta6_r);
    }

    public void evaluarTest(View view) {
        Double total=evaluarPregunta(pregunta1_r,1)
                +evaluarPregunta(pregunta2_r,2)
                +evaluarPregunta(pregunta3_r,2)
                +evaluarPregunta(pregunta4_r,2)
                +evaluarPregunta(pregunta5_r,1)
                +evaluarPregunta(pregunta6_r,2);
        resultado.setText(getString(R.string.resultado,total));
        compartir(total);
    }

    public void compartir(Double total ) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"Resultado de la evaluación: "+total.toString());
        intent.setType("text/plain");
        Intent shareIntent=Intent.createChooser(intent,null);
        startActivity(shareIntent);
    }

    Double evaluarPregunta(ViewGroup respuestas, int tipo){
        Double valor=0.0;
        if(tipo==1){
            for (int i=0;i<respuestas.getChildCount();i++){
                CheckBox respuesta=((CheckBox)respuestas.getChildAt(i));
                if(respuesta.isChecked()){
                    valor+=Double.valueOf(respuesta.getTag().toString());
                    //Toast.makeText(this,valor.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }else if(tipo==2){
            RadioButton respuesta=findViewById(((RadioGroup)respuestas).getCheckedRadioButtonId());
            if(respuesta!=null){
                valor+=Double.valueOf(respuesta.getTag().toString());
            }
        }
        return valor;
    }
}
