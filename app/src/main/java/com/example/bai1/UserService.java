package com.example.bai1;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface UserService {
    @POST("users")
    Call<User> registerUser(@Body User user);
}
