package com.example.restclientservweb;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionActivity extends AppCompatActivity {



    private EditText editTextTitle;
    private EditText editTextSender;
    private EditText editTextMessage;
    private Button buttonSend;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSender = findViewById(R.id.editTextSender);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/") // Cambiado a 10.0.2.2 para el emulador
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        buttonSend.setOnClickListener(v -> {

            // Get the current date in the format yyyy-MM-dd
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


            String title = editTextTitle.getText().toString();
            String sender = editTextSender.getText().toString();
            String message = editTextMessage.getText().toString();

            // Send the question to the server

            EnviarQuestion(title, sender, message, currentDate);
        });
    }
    private void EnviarQuestion(String title, String sender, String message, String currentDate) {


            Question question = new Question(currentDate, title, message, sender);
            Call<Question> call = apiService.sendQuestion(question);

            call.enqueue(new Callback<Question>() {
                @Override
                public void onResponse(Call<Question> call, Response<Question> response) {
                    if (response.isSuccessful()) {


                        Toast.makeText(QuestionActivity.this, "Question successful", Toast.LENGTH_SHORT).show();
                        response.body();
                    } else {
                        Toast.makeText(QuestionActivity.this, "question failed!!!! else en el on response ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Question> call, Throwable t) {


                    Toast.makeText(QuestionActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            });

    }
}