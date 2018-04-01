package com.example.easylearn;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class audioActivity extends AppCompatActivity {

  Button stopRecord, startRecord, playButton, stopButton;
  String pathSave= "";
  MediaRecorder mediaRecorder;
  MediaPlayer mediaPlayer;

  final int REQUEST_PERMISSION_CODE=1000;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audio);
    playButton=(Button)findViewById(R.id.playButton);
    stopButton=(Button)findViewById(R.id.stopButton);
    stopRecord=(Button)findViewById(R.id.stopRecord);
    startRecord=(Button)findViewById(R.id.startRecord);
//    if(checkPermissionFromDevice())
      if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
      (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED )){
        ActivityCompat.requestPermissions(this,new String[]{
          Manifest.permission.WRITE_EXTERNAL_STORAGE,
          Manifest.permission.RECORD_AUDIO
        },REQUEST_PERMISSION_CODE);
      }
    //{
      startRecord.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          pathSave= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ UUID.randomUUID().toString()+"_audio_record.3gp";
          setupMediaRecorder();
          try {
            mediaRecorder.prepare();
            mediaRecorder.start();
          } catch (IOException e) {
            e.printStackTrace();
          }
          playButton.setEnabled(false);
          stopButton.setEnabled(false);
          Toast.makeText(audioActivity.this, "Recording...", Toast.LENGTH_SHORT).show();
        }
      });
      stopRecord.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
          mediaRecorder.stop();
          stopRecord.setEnabled(false);
          playButton.setEnabled(true);
          startRecord.setEnabled(true);
          stopButton.setEnabled(false);

        }
      });
      playButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          stopButton.setEnabled(true);
          stopRecord.setEnabled(false);
          startRecord.setEnabled(false);
          mediaPlayer=new MediaPlayer();
          try{
            mediaPlayer.setDataSource(pathSave);
            mediaPlayer.prepare();

          } catch (IOException e) {
            e.printStackTrace();
          }
          mediaPlayer.start();
          Toast.makeText(audioActivity.this,"pLAYING...", Toast.LENGTH_SHORT).show();
        }
      });
      stopButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          stopRecord.setEnabled(false);
          startRecord.setEnabled(true);
          stopButton.setEnabled(false);
          playButton.setEnabled(true);
          if(mediaPlayer!=null)
          {
            mediaPlayer.stop();
            mediaPlayer.release();
            setupMediaRecorder();
          }
        }
      });
    }
   // else
  //  {
  //    requestPermission();
   // }
  //}

  private void setupMediaRecorder() {
    mediaRecorder=new MediaRecorder();
    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
    mediaRecorder.setOutputFile(pathSave);
  }

  private void requestPermission()
  {
    ActivityCompat.requestPermissions(this,new String[]{
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.RECORD_AUDIO
    },REQUEST_PERMISSION_CODE);
  }
  //@Override
  public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
    switch(requestCode)
    {
      case REQUEST_PERMISSION_CODE: {
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
          Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
        else
          Toast.makeText(this,"Permossion Denied",Toast.LENGTH_SHORT).show();
      }
      break;
    }

  }

  private boolean checkPermissionFromDevice() {
    int write_external_storage_result=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
    int record_audio_result= ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);
    return write_external_storage_result==PackageManager.PERMISSION_GRANTED && record_audio_result==PackageManager.PERMISSION_GRANTED;}


}
