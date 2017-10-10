package com.example.mzored.timer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    long timer_to = 1000025; //досчитает до 1000
    long timer_step = 1000;
    long a;
    boolean timer_act;
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(getString(R.string.timer_act), timer_act);
        outState.putLong("a", a);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        timer_act = savedInstanceState.getBoolean(getString(R.string.timer_act));
        a = savedInstanceState.getLong("a");
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

    private void start_tim() {
        timer_act = true;
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
        timer_act = false;
        StopButton.setVisibility(View.INVISIBLE);
        StartButton.setVisibility(View.VISIBLE);
        if (timer != null)
            timer.cancel();
    }

    public void onResume() {
        super.onResume();
        if (timer_act)
            start_tim();
        else
            stop_tim();
    }

    public void onPause() {
        super.onPause();
        if (timer != null)
            timer.cancel();
    }
}