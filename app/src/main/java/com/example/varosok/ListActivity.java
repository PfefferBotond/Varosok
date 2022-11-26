package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ListActivity extends AppCompatActivity {
    private Button backBtn;
    private TextView datainJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void init() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        backBtn = findViewById(R.id.backBtn);
        datainJson = findViewById(R.id.datainJson);
        Response response = null;
        try {
            response = RequestHandler.get(MainActivity.BASE_URL);
            String content = response.getContent();
            datainJson.setText(content);
        } catch (IOException e) {
            Toast.makeText(this, "Hiba történt a szerverrel való kommunikáció során", Toast.LENGTH_SHORT).show();
        }

    }
}