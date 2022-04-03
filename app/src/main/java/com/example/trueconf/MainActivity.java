package com.example.trueconf;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Animation animation =new Animation(this);


        animation.initHelloTextView(findViewById(R.id.helloTextView));
        animation.initContainer(findViewById(R.id.container));
        animation.setText("");
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
}