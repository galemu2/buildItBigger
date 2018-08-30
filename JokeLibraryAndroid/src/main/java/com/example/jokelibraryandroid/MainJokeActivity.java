package com.example.jokelibraryandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainJokeActivity extends AppCompatActivity {

    public static final String PASSED_JOKE = "joke passed between activities";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_joke);

        TextView textView = findViewById(R.id.textView_joke);
        Intent intent = getIntent();
        if(intent!=null){
            String joke = intent.getStringExtra(PASSED_JOKE);
            textView.setText(joke);
        }
    }
}
