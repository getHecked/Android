package com.arjun.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button childButton;
    private Button cameraButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        childButton = findViewById(R.id.childButton);
        childButton.setBackgroundColor(getColor(R.color.colorAccent));
        cameraButton = findViewById(R.id.cameaButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num = Math.random();
                if(num>0.5){
                    textView.setText(R.string.more_than);
                }
                else {
                    textView.setText(R.string.less_than);
                }
            }
        });

        childButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChildActivity.class);
                startActivity(intent);
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Camera_Activity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
