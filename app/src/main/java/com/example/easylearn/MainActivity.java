package com.example.easylearn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_DENIED){
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, 0);
      }

        Button lecturerButton = (Button)findViewById(R.id.lecturerButton);
        lecturerButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, LecturerScreen.class));
          }
        });
        Button group1 = findViewById(R.id.group1);
        group1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, GroupScreen.class));
          }
        });
      Button group2 = findViewById(R.id.group2);
      group2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(MainActivity.this, GroupScreen.class));
        }
      });
      Button group3 = findViewById(R.id.group3);
      group3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(MainActivity.this, GroupScreen.class));
        }
      });
      Button group4 = findViewById(R.id.group4);
      group4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(MainActivity.this, GroupScreen.class));
        }
      });
      Button group5 = findViewById(R.id.group5);
      group5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(MainActivity.this, GroupScreen.class));
        }
      });
      Button group6 = findViewById(R.id.group6);
      group6.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(MainActivity.this, GroupScreen.class));
        }
      });
    }
}
