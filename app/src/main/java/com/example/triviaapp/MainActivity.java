package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.triviaapp.Screens.FirstScreenTriviaApp;

public class MainActivity extends AppCompatActivity {

    Button get_started;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        get_started = (Button) findViewById(R.id.get_started);

        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFirstScreenTriviaApp();
            }
        });
    }

    private void openFirstScreenTriviaApp() {

        Intent intent = new Intent(this, FirstScreenTriviaApp.class);
        startActivity(intent);
    }
}
