package com.basic.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions;
    TextView scoreTextView;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button answer4Button;
    TextView sumTextView;
    TextView timerTextView;

    public void chooseAnswer(View view) {

        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {

            Log.i("Correct!", "You got it");
            Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show();
            score++;
        }else{

            Log.i("Wrong!", ":/");
            Toast.makeText(this, "WRONG :(", Toast.LENGTH_SHORT).show();
        }

        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void start(View view) {

        goButton.setVisibility(View.INVISIBLE);
        answer1Button.setVisibility(View.VISIBLE);
        answer2Button.setVisibility(View.VISIBLE);
        answer3Button.setVisibility(View.VISIBLE);
        answer4Button.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);

        score = 0;
        numberOfQuestions = 0;

        newQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {

                Toast.makeText(MainActivity.this, "FINISHED!", Toast.LENGTH_LONG).show();
                goButton.setVisibility(View.VISIBLE);
                answer1Button.setVisibility(View.INVISIBLE);
                answer2Button.setVisibility(View.INVISIBLE);
                answer3Button.setVisibility(View.INVISIBLE);
                answer4Button.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    public void newQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {

                answers.add(a+b);
            }else{
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b) {

                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        answer1Button.setText(Integer.toString(answers.get(0)));
        answer2Button.setText(Integer.toString(answers.get(1)));
        answer3Button.setText(Integer.toString(answers.get(2)));
        answer4Button.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        answer1Button = findViewById(R.id.answer1Button);
        answer2Button = findViewById(R.id.answer2Button);
        answer3Button = findViewById(R.id.answer3Button);
        answer4Button = findViewById(R.id.answer4Button);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);

        goButton = findViewById(R.id.goButton);

    }
}