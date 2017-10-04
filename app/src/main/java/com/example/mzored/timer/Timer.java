package com.example.mzored.timer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    long timer_to = 1000025; //досчитает до 1000
    long timer_step = 1000;
    String TextString;
    TextView TimerText;
    Button StartButton;
    Button StopButton;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        TimerText = (TextView) findViewById(R.id.TimerText);
        StartButton = (Button) findViewById(R.id.StartButton);
        StopButton = (Button) findViewById(R.id.StopButton);

        StartButton.setOnClickListener(onClickListener);
        StopButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.StartButton:
                    start_tim();
                    break;
                case R.id.StopButton:
                    stop_tim();
                    break;
            }
        }
    };

     /*
     @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        TextString = TimerText.getText().toString();
        outState.putString("TextString", TextString);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TimerText.setText(savedInstanceState.getString("TextString"));
    */

    private void start_tim() {
        StartButton.setVisibility(View.INVISIBLE);
        StopButton.setVisibility(View.VISIBLE);
        timer = new CountDownTimer(timer_to, timer_step) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimerText.setText(new NumbToStr().convert
                        (1 + (timer_to - millisUntilFinished) / timer_step));
            }

            public void onFinish() {
                stop_tim();
            }
        };
        timer.start();
    }

    private void stop_tim() {
        if (timer != null)
            timer.cancel();
        timer = null;
        StopButton.setVisibility(View.INVISIBLE);
        StartButton.setVisibility(View.VISIBLE);
    }
}