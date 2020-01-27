package xyz.eliothmonroy.sesion7.reto1.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.eliothmonroy.sesion7.R;
import xyz.eliothmonroy.sesion7.reto1.data.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> users;

    UsersAdapter(List<User> users){
        this.users=users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
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

        TextView itemId, itemName, itemEmail, itemAddressStreet, itemAddressCity, itemGeoLat, itemGeoLon, itemCompanyName;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            itemId=itemView.findViewById(R.id.itemId);
            itemName=itemView.findViewById(R.id.itemName);
            itemEmail=itemView.findViewById(R.id.itemEmail);
            itemAddressStreet=itemView.findViewById(R.id.itemAddressStreet);
            itemAddressCity=itemView.findViewById(R.id.itemAddressCity);
            itemGeoLat=itemView.findViewById(R.id.itemGeoLat);
            itemGeoLon=itemView.findViewById(R.id.itemGeoLon);
            itemCompanyName=itemView.findViewById(R.id.itemCompanyName);
        }

        void setData(User user){
            itemId.setText(getString(R.string.placeholder_id,user.getId()));
            itemName.setText(getString(R.string.placeholder_name,user.getName()));
            itemEmail.setText(getString(R.string.placeholder_email,user.getEmail()));
            itemAddressStreet.setText(getString(R.string.placeholder_address_street, user.getAddress().getStreet()));
            itemAddressCity.setText(getString(R.string.placeholder_address_city,user.getAddress().getCity()));
            itemGeoLat.setText(getString(R.string.placeholder_geo_lat,user.getAddress().getGeo().getLat()));
            itemGeoLon.setText(getString(R.string.placeholder_geo_lon,user.getAddress().getGeo().getLng()));
            itemCompanyName.setText(getString(R.string.placeholder_company_name,user.getCompany().getName()));
        }

        private String getString(int id, String value){
            return itemView.getResources().getString(id,value);
        }
    }
}
