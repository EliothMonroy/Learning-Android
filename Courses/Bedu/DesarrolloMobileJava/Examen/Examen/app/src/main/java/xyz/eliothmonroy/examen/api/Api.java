package xyz.eliothmonroy.examen.api;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.eliothmonroy.examen.data.Result;

public interface Api {
    @GET("users?page=2")
    Call<Result> getUsers();
}
