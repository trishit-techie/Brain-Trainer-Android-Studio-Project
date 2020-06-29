package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout gameLayout;
    Button startButton;
    TextView questionTextView;
    TextView timerTextView;
    TextView scoreTextView;
    TextView introTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int positionOfCorrectAnswer;
    TextView resultTextView;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();;
    int score=0,noOfQuestions=0;
    public  void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        startCountDown();
        introTextView.setVisibility(View.INVISIBLE);
    }
    public void chooseAnswer(View view)
    {
        resultTextView.setVisibility(View.VISIBLE);
        String buttonTag = view.getTag().toString();
        if(buttonTag.equals(Integer.toString(positionOfCorrectAnswer)))
        {
            resultTextView.setText("Correct!");
            score++;
        }
        else
        {
            resultTextView.setText("Wrong :(");

        }
        noOfQuestions++;
        scoreTextView.setText(score+"/"+noOfQuestions);
        nextQuestion();
    }
    public  void nextQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(40);
        int b = rand.nextInt(40);
        questionTextView.setText(Integer.toString(a)+"+"+Integer.toString(b)+"=?");
        positionOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for (int i=0;i<4;i++)
        {
            if(i==positionOfCorrectAnswer)
                answers.add(a+b);
            else
            {
                int wrongAnswer = rand.nextInt(80);
                while(wrongAnswer==(a+b))
                {
                    wrongAnswer=rand.nextInt(80);
                }
                answers.add(wrongAnswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void startCountDown()
    {
        new CountDownTimer(31000,1000)
        {
            public void onTick(long l)
            {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }
            public void onFinish()
            {
                resultTextView.setText("Your score is "+score+"!");
                Toast.makeText(getApplicationContext(),"Time's up!",Toast.LENGTH_LONG).show();
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }
    public void playAgain(View view)
    {
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        score=0;
        noOfQuestions=0;
        nextQuestion();
        startCountDown();
        resultTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.startButton);
        questionTextView = (TextView)findViewById(R.id.questionTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        gameLayout=(ConstraintLayout) findViewById(R.id.gameLayout);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainbutton);
        introTextView = (TextView)findViewById(R.id.introTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
        nextQuestion();
        gameLayout.setVisibility(View.INVISIBLE);


    }
}
