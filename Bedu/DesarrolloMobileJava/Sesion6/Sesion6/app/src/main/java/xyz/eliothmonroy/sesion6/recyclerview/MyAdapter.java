package xyz.eliothmonroy.sesion6.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import xyz.eliothmonroy.sesion6.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> lista;

    MyAdapter(ArrayList<String> lista){
        this.lista=lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarDato(lista.get(position));

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewItem;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItem=itemView.findViewById(R.id.txtItem);
        }
        void asignarDato(String s){
            textViewItem.setText(s);
            textViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),"Item: "+textViewItem.getText().toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
