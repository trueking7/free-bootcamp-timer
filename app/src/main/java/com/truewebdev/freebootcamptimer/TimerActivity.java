package com.truewebdev.freebootcamptimer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {


    TextView workTextView;
    TextView restTextView;
    TextView repsTextView;
    CountDownTimer workTimer30;
    CountDownTimer workTimer;
    CountDownTimer restTimer10;
    CountDownTimer restTimer;
    int repCounter;

    private boolean isCanceled = false;


    // Timer method
    public int timer(final int workTime, final int restTime, final int reps){



        repsTextView.setText("Number of reps: " + reps);
        restTextView.setText("Rest: " + restTime);

        workTimer = new CountDownTimer(workTime * 60000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                int secondsUntilFinished = (int) millisUntilFinished / 1000;

                workTextView.setText("Exercise: " + Integer.toString(secondsUntilFinished));

                if (isCanceled) {
                    cancel();

                }

            }

            @Override
            public void onFinish() {


                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell);
                mplayer.start();
                workTextView.setText("Exercise: " + workTime * 60);
                repsTextView.setText("Number of reps: "  + reps);

                restTimer = new CountDownTimer(restTime * 1000, 1000) {


                    @Override
                    public void onTick(long millisUntilFinished) {

                        int secondsUntilFinished = (int) millisUntilFinished / 1000;

                        restTextView.setText("Rest: " + Integer.toString(secondsUntilFinished));


                        if (isCanceled) {
                            cancel();

                        }

                    }

                    @Override
                    public void onFinish() {


                        MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell);
                        mplayer.start();
                        restTextView.setText("Rest: " + restTime);
                        int repCounter = reps - 1;
                        if(repCounter > 0) {
                            timer(workTime, restTime, repCounter);
                        } else{
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


        workTextView = (TextView) findViewById(R.id.workTextView);
        restTextView = (TextView) findViewById(R.id.restTextView);
        repsTextView = (TextView) findViewById(R.id.repsTextView);

        Bundle bundle = getIntent().getExtras();

        int workTime = bundle.getInt("workTime");
        int restTime = bundle.getInt("restTime");
        int reps = bundle.getInt("reps");





           timer(workTime, restTime, reps);




        }
    public void endTask(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        isCanceled = true;

    }

    }







