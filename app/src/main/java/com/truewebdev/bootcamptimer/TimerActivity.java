package com.truewebdev.bootcamptimer;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {


    TextView workTextView;
    TextView restTextView;
    TextView repsTextView;
    TextView directionTextView;
    CountDownTimer workTimer30;
    CountDownTimer workTimer;
    CountDownTimer restTimer10;
    CountDownTimer restTimer;
    RelativeLayout relativeLayout;
    int repCounter;

    private boolean isCanceled = false;


    // Timer method
    public int timer(final int workTime, final int restTime, final int reps) {


        repsTextView.setText(getString(R.string.repsSetText) + reps);
        restTextView.setText(getString(R.string.restText) + restTime);

        workTimer = new CountDownTimer(workTime * 60000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                int secondsUntilFinished = (int) millisUntilFinished / 1000;

                workTextView.setText(getString(R.string.exercise) + Integer.toString(secondsUntilFinished));
                directionTextView.setText(R.string.exercise2);
                relativeLayout.setBackgroundColor(Color.parseColor("#008000"));


                if (isCanceled) {
                    cancel();

                }

            }

            @Override
            public void onFinish() {


                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell);
                mplayer.start();
                workTextView.setText(getString(R.string.exercise3) + workTime * 60);
                repsTextView.setText(getString(R.string.reps2) + reps);

                restTimer = new CountDownTimer(restTime * 1000, 1000) {


                    @Override
                    public void onTick(long millisUntilFinished) {

                        int secondsUntilFinished = (int) millisUntilFinished / 1000;

                        restTextView.setText(getString(R.string.rest1) + Integer.toString(secondsUntilFinished));
                        directionTextView.setText(R.string.rest2);
                        relativeLayout.setBackgroundColor(Color.parseColor("#FF0000"));


                        if (isCanceled) {
                            cancel();

                        }

                    }

                    @Override
                    public void onFinish() {


                        MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell);
                        mplayer.start();
                        restTextView.setText(getString(R.string.rest3) + restTime);
                        int repCounter = reps - 1;
                        if (repCounter > 0) {
                            timer(workTime, restTime, repCounter);
                        } else {
                            endTask();
                        }


                    }
                }.start();

                restTimer10 = new CountDownTimer((restTime * 1000) - 10000, 1000) {


                    @Override
                    public void onTick(long millisUntilFinished) {


                        if (isCanceled) {
                            cancel();

                        }


                    }

                    @Override
                    public void onFinish() {


                        MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell1);
                        mplayer.start();


                    }
                }.start();


            }
        }.start();

        workTimer30 = new CountDownTimer((workTime * (60000)) - 30000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                int secondsUntilFinished = (int) millisUntilFinished / 1000;


                if (isCanceled) {
                    cancel();

                }


            }

            @Override
            public void onFinish() {


                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell1);
                mplayer.start();


            }
        }.start();
        repCounter = reps - 1;
        return repCounter;
    }

    public void goBack(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        isCanceled = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        workTextView = (TextView) findViewById(R.id.workTextView);
        restTextView = (TextView) findViewById(R.id.restTextView);
        repsTextView = (TextView) findViewById(R.id.repsTextView);
        directionTextView = (TextView) findViewById(R.id.directionTextView);

        Bundle bundle = getIntent().getExtras();

        int workTime = bundle.getInt("workTime");
        int restTime = bundle.getInt("restTime");
        int reps = bundle.getInt("reps");


        timer(workTime, restTime, reps);


    }

    public void endTask() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        isCanceled = true;

    }

}







