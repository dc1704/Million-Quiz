package com.dhruv.millionquiz;


import android.content.Intent;

import android.content.res.ColorStateList;
import android.graphics.Color;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 20000;
    private TextView textViewQuestion, textViewQuestionCount, textViewCountDown, textViewDbHint;
    private RadioButton rb_option1, rb_option2, rb_option3, rb_option4;
    private Button buttonNext, buttonClose;
    private RadioGroup rbGroup;
    private long mLastClickTime = 0;

    private Drawable DefaultRbDrawable;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private long timeLeftInMillis;
    private int score;
    private boolean answered;
    private long backPressedTime;
    private int categoryID;
    //private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        final MediaPlayer mp = MediaPlayer.create(this, R.raw.btnlowsound);


        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);

        rbGroup = findViewById(R.id.radiogroup);
        rb_option1 = findViewById(R.id.option_1_radio);
        rb_option2 = findViewById(R.id.option_2_radio);
        rb_option3 = findViewById(R.id.option_3_radio);
        rb_option4 = findViewById(R.id.option_4_radio);

        buttonNext = findViewById(R.id.buttonNext);
        buttonClose = findViewById(R.id.buttonClose);

        DefaultRbDrawable = rb_option1.getBackground();
        textColorDefaultCd = textViewCountDown.getTextColors();

        Intent intent = getIntent();
        categoryID = intent.getIntExtra(CategoryActivity.EXTRA_CATEGORY_ID, 0);
        // categoryName = intent.getStringExtra(CategoryActivity.EXTRA_CATEGORY_NAME);

        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        questionList = dbHelper.getQuestions(categoryID);
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);
        showNextQuestion();


        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000)
                    return;
                mLastClickTime = SystemClock.elapsedRealtime();

                mp.start();
                finishQuiz();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                if (!answered) {
                    if (rb_option1.isChecked() || rb_option2.isChecked() || rb_option3.isChecked() || rb_option4.isChecked()) {


                        checkAnswer();
                    } else {
                        Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    showNextQuestion();
                }

            }
        });


    }

    private void showNextQuestion() {
        rb_option1.setBackground(DefaultRbDrawable);
        rb_option2.setBackground(DefaultRbDrawable);
        rb_option3.setBackground(DefaultRbDrawable);
        rb_option4.setBackground(DefaultRbDrawable);
        rbGroup.clearCheck();



        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb_option1.setText(currentQuestion.getOption1());
            rb_option2.setText(currentQuestion.getOption2());
            rb_option3.setText(currentQuestion.getOption3());
            rb_option4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonNext.setText("Confirm");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }

    }

    private void finishQuiz() {

        finish();
    }


    @Override
    public void onBackPressed() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.btnlowsound);
        mp.start();
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (answerNr == currentQuestion.getAnswerNr() && rbSelected != null) {
            score++;
        } else if (rbSelected != null) {
            rbSelected.setBackground(getDrawable(R.drawable.button_bg_incorrect));

        }
        showSolution();
    }

    private void showSolution() {



        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb_option1.setBackground(getDrawable(R.drawable.button_bg_correct));
                break;

            case 2:
                rb_option2.setBackground(getDrawable(R.drawable.button_bg_correct));
                break;

            case 3:
                rb_option3.setBackground(getDrawable(R.drawable.button_bg_correct));
                break;

            case 4:
                rb_option4.setBackground(getDrawable(R.drawable.button_bg_correct));
                break;
        }
        if (questionCounter < questionCountTotal) {
            if (questionCounter == 10 && score == 10)
                Toast.makeText(this, "10 in a row. keep going.", Toast.LENGTH_SHORT).show();

            buttonNext.setText("Next");

        } else {
            buttonNext.setText("Finish");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }


}

