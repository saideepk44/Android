package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView scores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scores = findViewById(R.id.id_finalScore);
        Intent intentFromMain = getIntent();
        String message = intentFromMain.getStringExtra(MainActivity.LAUNCH_EXTRA);
        //Toast.makeText(MainActivity3.this, message, Toast.LENGTH_SHORT).show();
        scores.setText(message);

    }
}