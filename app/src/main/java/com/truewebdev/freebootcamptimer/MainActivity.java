package com.truewebdev.freebootcamptimer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


// version 1.0
public class MainActivity extends AppCompatActivity {

    EditText workTimeEditText;
    EditText restTimeEditText;
    EditText repsEditText;



public void startTimer(View view) throws IllegalStateException{



    if (TextUtils.isEmpty(workTimeEditText.getText().toString()) || TextUtils.isEmpty(restTimeEditText.getText().toString()) || TextUtils.isEmpty(repsEditText.getText().toString()) ) {

        Toast.makeText(getApplicationContext(), "Please enter values in all three fields!", Toast.LENGTH_LONG).show();
        return;

    } else if (Integer.parseInt(restTimeEditText.getText().toString()) < 30) {

        Toast.makeText(getApplicationContext(), "Please enter a rest time of at least 30 seconds!", Toast.LENGTH_LONG).show();


    } else{

        int workTime = Integer.parseInt(workTimeEditText.getText().toString());
        int restTime = Integer.parseInt(restTimeEditText.getText().toString());
        int reps = Integer.parseInt(repsEditText.getText().toString());

        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra("workTime", workTime);
        intent.putExtra("restTime", restTime);
        intent.putExtra("reps", reps);

        MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell);
        mplayer.start();
        startActivity(intent);
    }




}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workTimeEditText = (EditText)findViewById(R.id.workTimeEditText);
        restTimeEditText = (EditText)findViewById(R.id.restTimeEditText);
        repsEditText = (EditText)findViewById(R.id.repsEditText);







    }
}
