package com.example.triviaapp.Screens;

import com.example.triviaapp.Classes.Questions;

import java.util.ArrayList;

interface GetQuestionsListener {

    void loadQuestions(ArrayList<Questions> questions);
}
