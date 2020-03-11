package com.example.safeinternetwebview;

import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class QuizShowActivity extends AppCompatActivity {

    TextView questions,fristans,secondans,quiznumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_show);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Find All ID
        findAllid();



    }

    private void findAllid() {
        questions=findViewById(R.id.txt_questions);
        fristans=findViewById(R.id.txt_firstans);
        secondans=findViewById(R.id.txt_seconans);
        quiznumber=findViewById(R.id.txt_quiznumber);
    }
}
