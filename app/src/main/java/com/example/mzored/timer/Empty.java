package com.example.mzored.timer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Empty extends AppCompatActivity {

    long millisUntilFinished = 2000;
    static CountDownTimer timer;
    static final String TIMERKEY = "TIMER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }

    @Override
    public void onResume() {
        super.onResume();
         timer = new CountDownTimer(millisUntilFinished, 10) {
            @Override
            public void onTick(long milliSec) {
                millisUntilFinished = milliSec;
            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), Timer.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(TIMERKEY, millisUntilFinished);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        millisUntilFinished = savedInstanceState.getLong(TIMERKEY);
    }
}
