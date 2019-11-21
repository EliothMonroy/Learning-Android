package xyz.eliothmonroy.sesion6.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import xyz.eliothmonroy.sesion6.R;

public class HomeActivity extends AppCompatActivity {

    ArrayList<String> lista;
    RecyclerView myRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myRecycler=findViewById(R.id.recycler);
        myRecycler.setLayoutManager(new GridLayoutManager(this,2));
        lista=new ArrayList<>();
        for(int i=0;i<50;i++){
            lista.add("Item num: "+(i+1));
        }
        myRecycler.setAdapter(new MyAdapter(lista));
    }
}
