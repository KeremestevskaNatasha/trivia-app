package com.example.triviaapp.Classes;

import java.util.ArrayList;
import java.util.Random;

public class Questions {
    private String question;
    private String correctAnswer;


    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;

    private ArrayList<String> allAnswers;


    public Questions(String question, String correctAnswer,String wrongAnswer1,String wrongAnswer2,String wrongAnswer3 ) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;


        allAnswers = new ArrayList<>();

        allAnswers.add(wrongAnswer1);
        allAnswers.add(wrongAnswer2);
        allAnswers.add(wrongAnswer2);

        int randomNumber;
       randomNumber = 0;
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            randomNumber = random.nextInt(4);
          allAnswers.add(randomNumber, correctAnswer);
        }

    }

    public ArrayList<String> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(ArrayList<String> allAnswers) {
        this.allAnswers = allAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }






}
