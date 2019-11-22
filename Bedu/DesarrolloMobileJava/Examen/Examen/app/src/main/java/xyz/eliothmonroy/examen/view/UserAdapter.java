package xyz.eliothmonroy.examen.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import xyz.eliothmonroy.examen.R;
import xyz.eliothmonroy.examen.data.Data;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Data> users;

    UserAdapter(List<Data> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        ImageView itemUserImage;
        TextView itemUserFirstName;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            itemUserImage=itemView.findViewById(R.id.itemUserImage);
            itemUserFirstName=itemView.findViewById(R.id.itemUserFirstName);
        }

        void setData(final Data user){
            Picasso.get().load(user.getAvatar()).into(itemUserImage);
            itemUserFirstName.setText(user.getFirst_name());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(),UserDetailActivity.class);
                    intent.putExtra("User",user);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //((Activity)(itemView.getContext())).getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
                        Explode explode=new Explode();
                        explode.setDuration(1000);
                        ((Activity)(itemView.getContext())).getWindow().setExitTransition(explode);
                        itemView.getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) itemView.getContext()).toBundle());

                    } else {
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }

    }
}
