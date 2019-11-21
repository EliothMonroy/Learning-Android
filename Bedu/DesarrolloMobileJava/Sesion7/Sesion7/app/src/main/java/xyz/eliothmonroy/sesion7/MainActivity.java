package xyz.eliothmonroy.sesion7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPost=findViewById(R.id.txtPost);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        MyApi myApi=retrofit.create(MyApi.class);
        Call<List<Post>> call=myApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()){
                    List<Post> posts=response.body();
                    for(Post post:posts){
                        String item="userId: "+post.getUserId()+"\nid: "+post.getId();
                        Log.d("TAG",post.getTitle());
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Response code: "+response.code(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
