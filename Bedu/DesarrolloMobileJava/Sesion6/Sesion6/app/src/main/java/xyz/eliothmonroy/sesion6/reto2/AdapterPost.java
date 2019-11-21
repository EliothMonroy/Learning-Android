package xyz.eliothmonroy.sesion6.reto2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import xyz.eliothmonroy.sesion6.R;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {
    private ArrayList<Post> lista;
    
    AdapterPost(ArrayList<Post> lista){
        this.lista=lista;
    }
    
    @NonNull
    @Override
    public AdapterPost.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        return new AdapterPost.ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull AdapterPost.ViewHolder holder, int position) {
        holder.asignarPost(lista.get(position));
        
    }
    
    @Override
    public int getItemCount() {
        return lista.size();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder{
        
        TextView textViewItem;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //textViewItem=itemView.findViewById(R.id.txtItem);
        }
        void asginarPost(Post p){
            
            
            
            textViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                
                }
            });
        }
    }
}
