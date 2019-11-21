package xyz.eliothmonroy.sesion7.reto1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eliothmonroy.sesion7.R;
import xyz.eliothmonroy.sesion7.reto1.api.UsersApi;
import xyz.eliothmonroy.sesion7.reto1.data.User;
import xyz.eliothmonroy.sesion7.reto1.utils.RetrofitUtils;

public class UsersActivity extends AppCompatActivity {

    List<User> users;
    UsersApi usersApi;
    RecyclerView recyclerUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerUsers=findViewById(R.id.recyclerUsers);
        usersApi= RetrofitUtils.getRetrofitInstance().create(UsersApi.class);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerUsers.setLayoutManager(linearLayoutManager);

        getUsers();

    }

    private void getUsers() {
        Call<List<User>> call=usersApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    users=response.body();
                    UsersAdapter pictureAdapterRecyclerView=new UsersAdapter(users);
                    recyclerUsers.setAdapter(pictureAdapterRecyclerView);
                }else{
                    Toast.makeText(getApplicationContext(),"Response code: "+response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
