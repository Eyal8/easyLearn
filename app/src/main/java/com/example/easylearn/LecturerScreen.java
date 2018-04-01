package com.example.easylearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.Locale;

public class LecturerScreen extends AppCompatActivity {
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private EditText timeAmount;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private double millis = 0;
    private boolean resume = false;
    private Button presentationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_lecturer_screen);
      presentationButton = findViewById(R.id.presentation);
      presentationButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(LecturerScreen.this, presentationActivity.class));
        }
      });
      timeAmount = findViewById(R.id.time_count);
      mTextViewCountDown = findViewById(R.id.text_view_countdown);
      mButtonStartPause = findViewById(R.id.button_start_pause);
      mButtonReset = findViewById(R.id.button_reset);

      mButtonStartPause.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          String text = timeAmount.getText().toString();
          if(!text.equalsIgnoreCase("")) {
            if (mTimerRunning) {
              pauseTimer();
            } else {
              if(resume) {
                resumeTimer();
              }
              else
                startTimer();
            }
          }
        }
      });

      mButtonReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          resetTimer();
        }
      });

      updateCountDownText();
    }

  private void startTimer() {
    millis = Double.valueOf(timeAmount.getText().toString())*1000*60;
    mCountDownTimer = new CountDownTimer((long)millis, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        millis = millisUntilFinished;
        updateCountDownText();
      }

      @Override
      public void onFinish() {
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonStartPause.setVisibility(View.INVISIBLE);
        mButtonReset.setVisibility(View.VISIBLE);

       // GoogleSignInClient mGoogleSignInClient = new GoogleSignInClient();
      }
    }.start();

    mTimerRunning = true;
    mButtonStartPause.setText("pause");
    mButtonReset.setVisibility(View.INVISIBLE);
  }
  private void resumeTimer(){
    updateCountDownText();
    mButtonStartPause.setText("pause");
    mButtonReset.setVisibility(View.INVISIBLE);
    mCountDownTimer = new CountDownTimer((long)millis, 1000) {
      public void onTick(long millisUntilFinished) {
        millis = millisUntilFinished;
        updateCountDownText();
      }

      @Override
      public void onFinish() {
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonStartPause.setVisibility(View.INVISIBLE);
        mButtonReset.setVisibility(View.VISIBLE);
      }
    }.start();
    mTimerRunning = true;
  }
  private void pauseTimer() {
    mCountDownTimer.cancel();
    mTimerRunning = false;
    resume = true;
    mButtonStartPause.setText("Resume");
    mButtonReset.setVisibility(View.VISIBLE);
  }

  private void resetTimer() {
    millis = 0;
    updateCountDownText();
    mButtonStartPause.setText("Start");
    mButtonReset.setVisibility(View.INVISIBLE);
    mButtonStartPause.setVisibility(View.VISIBLE);
    resume = false;
  }

  private void updateCountDownText() {
    int minutes = (int) (millis / 1000) / 60;
    int seconds = (int) (millis / 1000) % 60;
    int hours = 0;
    String timeLeftFormatted = "";
    if(minutes >= 60)
    {
      hours = minutes / 60;
      minutes = minutes % 60;
      timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
    }
    else
      timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

    mTextViewCountDown.setText(timeLeftFormatted);
  }


}
