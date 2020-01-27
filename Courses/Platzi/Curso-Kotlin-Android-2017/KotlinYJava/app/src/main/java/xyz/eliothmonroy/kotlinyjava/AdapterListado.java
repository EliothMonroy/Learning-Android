package xyz.eliothmonroy.kotlinyjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterListado extends RecyclerView.Adapter<AdapterListado.ViewHolder> {
    
    private List<PojoBasico> list;
    
    public AdapterListado(List<PojoBasico> list) {
        this.list = list;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,parent,false));
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PojoBasico data=list.get(position);
        holder.titulo.setText(data.getTitulo());
        holder.desc.setText(data.getDesc());
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, desc;
    
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.txtItemTitle);
            desc=itemView.findViewById(R.id.txtItemDesc);
        }
    }
    
}
