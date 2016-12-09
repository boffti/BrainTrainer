package com.aneeshmelkot.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    RelativeLayout gameScreen;
    CountDownTimer timer;
    ArrayList<Integer> answers = new ArrayList<>();
    TextView resView;
    TextView scoreView;
    TextView numberAdd;
    TextView timerTextView;
    Button startButton;
    Button tile1;
    Button tile2;
    Button tile3;
    Button tile4;
    Button playAgainButton;
    int a;
    int b;
    int totalQuestions =0;
    int answeredCorrect=0;
    int locationOfCorrectAnswer;

    public void generateQuestion() {

        Random rand = new Random();

         a = rand.nextInt(21);
         b = rand.nextInt(21);

        numberAdd.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(3);

        answers.clear();

        int incorrectAnswer;

        for(int i = 0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {
                answers.add(a+b);
            }

            else {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a+b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        tile1.setText(Integer.toString(answers.get(0)));
        tile2.setText(Integer.toString(answers.get(1)));
        tile3.setText(Integer.toString(answers.get(2)));
        tile4.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            resView.setText(R.string.correctText);
            answeredCorrect++;
        } else {
            resView.setText(R.string.wrongText);
        }

        totalQuestions++;
        scoreView.setText(Integer.toString(answeredCorrect)+"/"+ Integer.toString(totalQuestions));
        generateQuestion();

    }

    public void gameStart(View view) {

        playAgainButton.setVisibility(View.INVISIBLE);
        gameScreen.setVisibility(View.VISIBLE);
        totalQuestions=0;
        answeredCorrect=0;
        scoreView.setText(Integer.toString(answeredCorrect)+"/"+ Integer.toString(totalQuestions));
        resView.setText("");
        startButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        timer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameScreen = (RelativeLayout)findViewById(R.id.gameScreen);

        tile1= (Button)findViewById(R.id.tile1);
        tile2= (Button)findViewById(R.id.tile2);
        tile3= (Button)findViewById(R.id.tile3);
        tile4= (Button)findViewById(R.id.tile4);

        startButton = (Button)findViewById(R.id.startButton);
        numberAdd = (TextView)findViewById(R.id.numberAdd);
        timerTextView =(TextView)findViewById(R.id.timerTextView);
        scoreView = (TextView)findViewById(R.id.scoreView);
        resView = (TextView)findViewById(R.id.resultTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        timer = new CountDownTimer(30200, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                //gameOver = true;
                timerTextView.setText("0s");
                resView.setText(answeredCorrect+" correct out of "+totalQuestions);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

        generateQuestion();

    }
}
