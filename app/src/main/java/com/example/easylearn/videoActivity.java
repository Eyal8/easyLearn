package com.example.easylearn;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;


public class videoActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;

    VideoView result_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button click = (Button)findViewById(R.id.videorec);
        result_video = (VideoView)findViewById(R.id.videoView);

        click.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakeVideoIntent(v);
            }
        });

    }

    public void dispatchTakeVideoIntent(View v) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            result_video.setVideoURI(videoUri);
//            result_video.setZOrderOnTop(true);
            MediaController mc = new MediaController(videoActivity.this);
            mc.setAnchorView(result_video);
            result_video.setMediaController(mc);
            AudioManager mAudioManager;
            mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
//            result_video.start();
        }
    }
}
