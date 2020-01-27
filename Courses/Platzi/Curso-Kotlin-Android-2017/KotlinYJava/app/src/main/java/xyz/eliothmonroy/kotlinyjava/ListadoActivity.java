package xyz.eliothmonroy.kotlinyjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
    
        TextView textView=findViewById(R.id.txtListadoUser);
        
        if(getIntent().getExtras()!=null){
            Bundle bundle=getIntent().getExtras();
            textView.setText("Bienvenido: "+bundle.getString("usuario"));
        }
        
        RecyclerView recyclerViewListado=findViewById(R.id.recyclerListado);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewListado.setLayoutManager(linearLayoutManager);
        recyclerViewListado.setAdapter(new AdapterListado(getFakeData()));
    }
    
    private List<PojoBasico> getFakeData() {
        List<PojoBasico> pojoBasicoList=new ArrayList();
        for (int i=0;i<10;i++){
            pojoBasicoList.add(new PojoBasico("Titulo "+(i+1),"Descripción "+(i+1)));
        }
        return pojoBasicoList;
    }
}
