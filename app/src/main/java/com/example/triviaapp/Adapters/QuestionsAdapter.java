package com.example.triviaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaapp.Classes.Questions;
import com.example.triviaapp.R;

import java.util.List;
import java.util.Random;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private static boolean[] checkedAnswers;
    private int randomNumbers;
    private Random random;
    RadioGroup radioGroup;
    private List<String> answers;

    private List<Questions> questionsList;
    Context context;

    public
    QuestionsAdapter(List<Questions> questionsList, Context context) {
        this.questionsList = questionsList;
        this.context = context;
        checkedAnswers = new boolean[questionsList.size()];
    }

    @NonNull
    @Override
    public QuestionsAdapter.QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.questions_item, parent, false);;
        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(view);
        return questionsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.QuestionsViewHolder holder, int position) {

        final int positionHolder = holder.getAdapterPosition();
        final Questions questions = questionsList.get(holder.getAdapterPosition());

        random = new Random();
        holder.tv_question.setText(questions.getQuestion());

        answers = questions.getAllAnswers();


        holder.radioButton1.setText(answers.get(0));
        holder.radioButton2.setText(answers.get(1));
        holder.radioButton3.setText(answers.get(2));
        holder.radioButton4.setText(answers.get(3));


                holder.radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton r = (RadioButton) view;
                if (r.getText().toString().equals(questions.getAllAnswers())) {
                    checkedAnswers[positionHolder] = true;
                } else {
                    checkedAnswers[positionHolder] = false;
                }
            }
        });
        holder.radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton r = (RadioButton) view;
                if (r.getText().toString().equals(questions.getAllAnswers())) {
                    checkedAnswers[positionHolder] = true;
                } else {
                    checkedAnswers[positionHolder] = false;
                }
            }
        });
        holder.radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton r = (RadioButton) view;
                if (r.getText().toString().equals(questions.getAllAnswers())) {
                    checkedAnswers[positionHolder] = true;
                } else {
                    checkedAnswers[positionHolder] = false;
                }
            }
        });
        holder.radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton r = (RadioButton) view;
                if (r.getText().toString().equals(questions.getAllAnswers())) {
                    checkedAnswers[positionHolder] = true;
                } else {
                    checkedAnswers[positionHolder] = false;
                }
            }
        });
    }

    public boolean[] getUserInput() {
        return checkedAnswers;
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {

        TextView tv_question;
        RadioGroup radio_group ;
        RadioButton radioButton1, radioButton2, radioButton3, radioButton4 ;



        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_question = itemView.findViewById(R.id.tv_question);


            radio_group = itemView.findViewById(R.id.radio_group);

            radioButton1 = itemView.findViewById(R.id.radioButton1);
            radioButton2 = itemView.findViewById(R.id.radioButton2);
            radioButton3 = itemView.findViewById(R.id.radioButton3);
            radioButton4 = itemView.findViewById(R.id.radioButton4);



        }

    }
}
