package com.example.triviaapp.Screens;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaapp.Adapters.QuestionsAdapter;
import com.example.triviaapp.Classes.Questions;
import com.example.triviaapp.MainActivity;
import com.example.triviaapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;




public class SecondScreenTriviaApp extends AppCompatActivity {


    public static List<String> wrongAnswers;
public QuestionsAdapter questionsAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    ArrayList<Questions> questionsList = new ArrayList<>();

    static final String BASE_URL = "https://opentdb.com/api.php?";
    static final String AMOUNT_OFQ = "amount";
    static final String CATEGORY = "category";
    static final String DIFFICULTY = "difficulty";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen_trivia_app);

        String amount = getIntent().getStringExtra("QUESTION_KEY");
        String category = getIntent().getStringExtra("CATEGORY_KEY");
        String difficulty = getIntent().getStringExtra("DIFFICULTY_KEY");

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.btn_progressbar);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        TriviaAsyncTask triviaAsyncTask = new TriviaAsyncTask(amount, category, difficulty,new GetQuestionsListener() {
            @Override
            public void loadQuestions(ArrayList<Questions> questionsList) {

                QuestionsAdapter questionsAdapter = new QuestionsAdapter(questionsList, SecondScreenTriviaApp.this);
                recyclerView.setAdapter(questionsAdapter);


            }
        });triviaAsyncTask.execute();


        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

//                int counter = 0;
//                boolean[] user_input = questionsAdapter.getUserInput();
//                for (int i = 0; i < user_input.length; i++) {
//                    if (user_input[i]) {
//                        counter++;
//                    }
//                }
//                if (counter == questionsList.size()) {
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(SecondScreenTriviaApp.this);
//
//                    alertDialog.setTitle("CONGRATULATIONS! Your score is: " + counter + "/" + questionsList.size());
//                    alertDialog.setMessage("Do you want to play another game?");
//                    alertDialog.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent restartIntent = new Intent(SecondScreenTriviaApp.this, FirstScreenTriviaApp.class);
//                            startActivity(restartIntent);
//                        }
//                    });
//                    alertDialog.show();
//                } else {
//
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(SecondScreenTriviaApp.this);
//
//                    alertDialog.setTitle("Your score: " + counter + "/" + questionsList.size());
//                    alertDialog.setMessage("Do you want to play another game?");
//                    alertDialog.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent restartIntent = new Intent(SecondScreenTriviaApp.this, FirstScreenTriviaApp.class);
//                            startActivity(restartIntent);
//                        }
//                    });
//                    alertDialog.show();
//
//                }

            }
        });


    }

    static class TriviaAsyncTask extends AsyncTask<String, String, ArrayList<Questions>> {



        GetQuestionsListener listener;

        String amount;
        String category;
        String difficulty;
        String  wrongAnswer1, wrongAnswer2,wrongAnswer3 ;

        public TriviaAsyncTask(String amount, String category, String difficulty,GetQuestionsListener listener) {
            this.listener=listener;
            this.amount = amount;
            this.category = category;
            this.difficulty = difficulty;

        }


      @Override
        protected ArrayList<Questions> doInBackground(String... strings) {

          ArrayList<Questions> questionsList = new ArrayList<>();

          Uri uri = Uri.parse(BASE_URL )
                  .buildUpon()
                   .appendQueryParameter(AMOUNT_OFQ, amount)
                  .appendQueryParameter(CATEGORY, category)
                   .appendQueryParameter(DIFFICULTY, difficulty)
                   .build();

           URL url = null;
           try {
                url = new URL(uri.toString());
              HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                int responseCode = httpURLConnection.getResponseCode();


                if (responseCode == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    String results = ConvertUtil.convertIsToString(inputStream);

                    JSONObject object = new JSONObject(results);
                    JSONArray jsonArray = object.getJSONArray("results");


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        String question = jsonObject.getString("question");
                        String correctAnswer = jsonObject.getString("correct_answer");


                        wrongAnswers = new ArrayList<>();
                        JSONArray jsonArrayI = jsonObject.getJSONArray("incorrect_answers");

                        for (int j = 0; j < jsonArrayI.length(); j++) {
                            String incorrectAnswer = (String) jsonArrayI.get(j);
                            wrongAnswers.add(incorrectAnswer);
                        }
                        if (wrongAnswers.size() == 1) {
                            continue;
                        }
                        wrongAnswer1 = wrongAnswers.get(0);
                        wrongAnswer2 = wrongAnswers.get(1);
                        wrongAnswer3 = wrongAnswers.get(2);



                        questionsList.add(new Questions(question,correctAnswer,wrongAnswer1,wrongAnswer2,wrongAnswer3));
                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
           catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return questionsList;
     }

       @Override
        protected void onPostExecute(ArrayList<Questions> questionsList) {
            super.onPostExecute(questionsList);

            listener.loadQuestions(questionsList);


        }
    }

}