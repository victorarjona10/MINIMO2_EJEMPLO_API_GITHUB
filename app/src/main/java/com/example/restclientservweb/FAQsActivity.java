package com.example.restclientservweb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FAQsActivity extends AppCompatActivity {


    private RecyclerView recyclerViewFAQs;
    private FAQsAdapter faqsAdapter;
    private List<FAQs> faqList;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_faqs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        recyclerViewFAQs = findViewById(R.id.recyclerViewFAQs);
        recyclerViewFAQs.setLayoutManager(new LinearLayoutManager(this));
        Button button = findViewById(R.id.button3);

        faqList = new ArrayList<>();



        faqsAdapter = new FAQsAdapter(faqList);
        recyclerViewFAQs.setAdapter(faqsAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);

                Preguntas_y_respuestas();
            }
        });

    }

    private void Preguntas_y_respuestas()
    {
        Call<List<FAQs>> call = apiService.getFAQs();
        call.enqueue(new Callback<List<FAQs>>() {
            @Override
            public void onResponse(Call<List<FAQs>> call, Response<List<FAQs>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<FAQs> faqs = response.body();
                faqList.addAll(faqs);
                faqsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FAQs>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}