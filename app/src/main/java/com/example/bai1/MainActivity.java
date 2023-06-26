package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://flow-fbmj.onrender.com/";
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Lấy thông tin từ người dùng
        String email = "user@gmail.com";
        String username = "John";
        String password = "123";

        // Tạo một đối tượng User với thông tin từ người dùng
        User user = new User(email, username, password);

        // Tạo call để đăng ký người dùng
        UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.registerUser(user);
        // Thực hiện call và xử lý response từ server
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User registeredUser = response.body();
                    // Xử lý registeredUser
                } else {
                    Toast.makeText(MainActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}