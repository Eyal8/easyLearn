package com.example.easylearn;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class beforePaintActivity extends AppCompatActivity {

  private paint paint;
  final int REQUEST_PERMISSION_CODE=1000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_paint);

    if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
      (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED )){
      ActivityCompat.requestPermissions(this,new String[]{
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
      },REQUEST_PERMISSION_CODE);
    }

    paint=(paint)findViewById(R.id.canvas);
  }

  public void clearCanvas(View v){
    paint.clearCanvas();
  }
}
