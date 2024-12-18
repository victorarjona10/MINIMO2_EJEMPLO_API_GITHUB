package com.example.restclientservweb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DenunciaActivity extends AppCompatActivity {
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_denuncia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextDenuncia = findViewById(R.id.editTextDenuncia);
        Button button = findViewById(R.id.button);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);



        button.setOnClickListener(v -> {
            String nombre = editTextUsername.getText().toString();
            String denuncia = editTextDenuncia.getText().toString();


            String fecha = "26-1-2004";
           EnviarDenuncia(nombre, denuncia, fecha);
           //Enviar_parametros(nombre, denuncia, fecha);
        });




    }
    private void EnviarDenuncia (String nombre, String comentario, String fecha)
    {
        Denuncias denuncia = new Denuncias(nombre, comentario, fecha);
        Call<Denuncias> call = apiService.sendDenuncia(denuncia);

        call.enqueue(new Callback<Denuncias>() {
            @Override
            public void onResponse(Call<Denuncias> call, Response<Denuncias> response) {
                Toast.makeText(DenunciaActivity.this, "todo ha ido bien", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Denuncias> call, Throwable t) {

                Toast.makeText(DenunciaActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Enviar_parametros(String nombre, String comentario, String fecha)
    {
        Denuncias denuncias = new Denuncias(nombre, comentario, fecha);
        Call<Denuncias> call = apiService.sendDenuncia_parametros(nombre, comentario, fecha);

        call.enqueue(new Callback<Denuncias>() {
            @Override
            public void onResponse(Call<Denuncias> call, Response<Denuncias> response) {

                response.body();
                if (response.isSuccessful()) {

                    Toast.makeText(DenunciaActivity.this, "todo ha ido bien", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DenunciaActivity.this, "ha saltado el else del onResponse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Denuncias> call, Throwable t) {

                Toast.makeText(DenunciaActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

}