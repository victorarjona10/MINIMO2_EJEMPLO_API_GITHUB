package com.example.restclientservweb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_Restclientservweb);
        // Clear the splash screen background
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_main);

        Button buttonLogin = findViewById(R.id.button_login);
        Button buttonRegister = findViewById(R.id.button_register);
        Button buttonStore = findViewById(R.id.button_store);
        Button buttonDenuncia = findViewById(R.id.button_denuncia);
        Button buttonFAQs = findViewById(R.id.button2);
        Button buttonQuestion = findViewById(R.id.button4);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        sharedPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        checkLoginStatus();

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        buttonStore.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StoreActivity.class);
            startActivity(intent);
        });
        buttonDenuncia.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DenunciaActivity.class);
            startActivity(intent);
        });
        buttonDenuncia.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DenunciaActivity.class);
            startActivity(intent);
        });
        buttonFAQs.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FAQsActivity.class);
            startActivity(intent);
        });
        buttonQuestion.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
            startActivity(intent);
        });


    }

    private void checkLoginStatus() {
        String username = sharedPreferences.getString("username", null);
        String idUser = sharedPreferences.getString("idUser", null);
        if (username != null) {
            Intent intent = new Intent(MainActivity.this, StoreActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("idUser", idUser);
            startActivity(intent);
            finish();
        }
    }
}