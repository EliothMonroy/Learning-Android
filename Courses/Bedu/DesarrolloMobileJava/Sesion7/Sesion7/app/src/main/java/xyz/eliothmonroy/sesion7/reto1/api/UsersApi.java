package xyz.eliothmonroy.sesion7.reto1.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.eliothmonroy.sesion7.reto1.data.User;

public interface UsersApi {
    @GET("/users")
    Call<List<User>> getUsers();
}
