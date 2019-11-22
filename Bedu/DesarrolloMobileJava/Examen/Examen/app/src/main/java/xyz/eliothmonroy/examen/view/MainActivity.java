package xyz.eliothmonroy.examen.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eliothmonroy.examen.R;
import xyz.eliothmonroy.examen.api.Api;
import xyz.eliothmonroy.examen.data.Data;
import xyz.eliothmonroy.examen.data.Result;
import xyz.eliothmonroy.examen.utils.RetrofitUtils;

public class MainActivity extends AppCompatActivity {

    List<Data> users;
    Api api;
    RecyclerView recyclerUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerUsers=findViewById(R.id.recyclerUsers);
        api= RetrofitUtils.getRetrofitInstance().create(Api.class);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerUsers.setLayoutManager(linearLayoutManager);

        getUsers();
    }

    private void getUsers() {
        Call<Result> call=api.getUsers();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @androidx.annotation.NonNull Response<Result> response) {
                if (response.isSuccessful()){
                    Result result=response.body();
                    if (result!=null){
                        users=result.getData();
                    }else {
                        Toast.makeText(getApplicationContext(),"No se obtuvieron usuarios del api :(",Toast.LENGTH_LONG).show();
                    }
                    UserAdapter usersAdapter=new UserAdapter(users);
                    recyclerUsers.setAdapter(usersAdapter);
                }else{
                    Toast.makeText(getApplicationContext(),"Response code: "+response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @androidx.annotation.NonNull Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
