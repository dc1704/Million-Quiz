package com.dhruv.millionquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstScreen extends AppCompatActivity {
    private int user_hints;
    private static final int REQUEST_CODE_QUIZ = 1;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.btnlowsound);

        Button imageButton = findViewById(R.id.Button_start_quiz);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000)
                    return;
                mLastClickTime = SystemClock.elapsedRealtime();

                mp.start();
                startQuiz();

            }
        });

    }


    private void startQuiz() {
        Intent intent = new Intent(FirstScreen.this, CategoryActivity.class);
        startActivity(intent);
    }

}