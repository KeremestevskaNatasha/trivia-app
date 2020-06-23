package com.example.triviaapp.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.triviaapp.R;

public class FirstScreenTriviaApp extends AppCompatActivity {

    String numberOfQuestion,categoryChosen, difficultyChosen ;
    Spinner spinnerCategory, spinnerDifficulty;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_trivia_app);


        Button btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                EditText editTextNumbOfQuestions = findViewById(R.id.number_of_questions);
                String numbOfQuestion = editTextNumbOfQuestions.getText().toString();
                int numQuestions = Integer.parseInt(numbOfQuestion);
                if ( numQuestions > 20 || numQuestions < 5 ){
                    editTextNumbOfQuestions.setError("Number of question must be set, max till 20 question");
                    return;
                }


                final Spinner spinnerCategory = findViewById(R.id.select_category);
                String category =spinnerCategory.getSelectedItem().toString();
                getRealCategory(category);

                final Spinner spinnerDifficulty = findViewById(R.id.select_difficulty);
                String difficulty = spinnerDifficulty.getSelectedItem().toString();
                getRealDifficulty(difficulty);

                Intent intent = new Intent(FirstScreenTriviaApp.this,SecondScreenTriviaApp.class);
                intent.putExtra("CATEGORY_KEY",categoryChosen);
                intent.putExtra("DIFFICULTY_KEY",difficultyChosen);
                intent.putExtra("QUESTION_KEY",numbOfQuestion);

                startActivity(intent);
            }
        });
    }
    public void getRealCategory(String category){
        if(category.equalsIgnoreCase("Art")){
            categoryChosen = "25";
        }else if (category.equalsIgnoreCase("Film")){
            categoryChosen = "11";
        } else if (category.equalsIgnoreCase("Any Category")) {
            categoryChosen = null;
        }
    }

    public void getRealDifficulty(String difficulty){
        if (difficulty.equalsIgnoreCase("Easy")) {
            difficultyChosen = "easy";
        } else if (difficulty.equalsIgnoreCase("Medium")) {
            difficultyChosen = "medium";
        } else if (difficulty.equalsIgnoreCase("Any Difficulty")) {
            difficultyChosen = null;
        } else if (difficulty.equalsIgnoreCase("Hard")) {
            difficultyChosen = "hard";
        }

    }

}