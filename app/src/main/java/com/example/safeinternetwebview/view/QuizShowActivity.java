package com.example.safeinternetwebview.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.safeinternetwebview.R;
import com.example.safeinternetwebview.common.Common;
import com.example.safeinternetwebview.model.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class QuizShowActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSerial,tvQuestion,tvFirstAnswer,tvSecondAnswer;
    DatabaseReference quizgetRef;
    int serial = 0;
    int cSerial = 0;
    int postion;
    long total_question = 0;

    //Preferences cache memory
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SharedPreferences cPreferences;
    SharedPreferences.Editor cEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_show);

        preferences=getSharedPreferences(Common.CATEGORY_SELECTED,MODE_PRIVATE);
        editor=getSharedPreferences(Common.CATEGORY_SELECTED,MODE_PRIVATE).edit();
        serial=preferences.getInt("position",1);


        cPreferences=getSharedPreferences(Common.CATEGORY_SELECTED,MODE_PRIVATE);
        cEditor=getSharedPreferences(Common.CATEGORY_SELECTED,MODE_PRIVATE).edit();
        cSerial=cPreferences.getInt("cSerial",0);


        //init all view
        init();



        get_total();


    }

    private void init()
    {
        tvSerial = findViewById(R.id.txt_quiznumber);
        tvQuestion = findViewById(R.id.txt_questions);
        tvFirstAnswer = findViewById(R.id.txt_firstans);
        tvSecondAnswer = findViewById(R.id.txt_seconans);
        tvFirstAnswer.setOnClickListener(this);
        tvSecondAnswer.setOnClickListener(this);

    }
    private void get_total() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Common.CATEGORY_SELECTED);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                total_question = dataSnapshot.getChildrenCount();
                //progressDialog.dismiss();
                updateQuestion(serial);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateQuestion(final int serial){
        //serial = serial + 1;
        if(serial <= total_question){

        }
        quizgetRef = FirebaseDatabase.getInstance().getReference().child(Common.CATEGORY_SELECTED).child(String.valueOf(serial));
        Log.e("msg", Common.CATEGORY_SELECTED);
        quizgetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists()){
                    final Quiz getQuiz =dataSnapshot.getValue(Quiz.class);
                    tvSerial.setText(serial + "/" + total_question);
                    String  answer = (getQuiz.getAnswer());
                    String  question = (getQuiz.getQuestion());
                    String  optionA = (getQuiz.getOption1());
                    String  optionB = (getQuiz.getOption2());

                    tvQuestion.setText(question);
                    tvFirstAnswer.setText(optionA);
                    tvSecondAnswer.setText(optionB);

                    if(answer.equals(optionA))
                        postion = 1;
                    else if(answer.equals(optionB))
                        postion = 2;
                }else {

                    Intent intent = new Intent(QuizShowActivity.this,QuizItemActivity.class);
                    Toast.makeText(QuizShowActivity.this, "quiz completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra(Common.CATEGORY_SELECTED,serial);
                    intent.putExtra("chapter",Common.CATEGORY_SELECTED);
                    Log.e("rabbi",String.valueOf(serial));
                    Log.e("rabbi",Common.CATEGORY_SELECTED);
                    editor.clear().apply();
                    startActivity(intent);
                    finish();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId()){

            case R.id.txt_firstans:
                changeColor(1, 1);
                break;
            case R.id.txt_seconans:
                changeColor(2, 2);
        }


    }
    private void changeColor(int option, final int ans) {


        //Do something after 100ms
        switch (option) {

        }
        if (postion == ans) {


            switch (postion) {

            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                if (ans == postion) {

                    showRightDialog("");

                } else {

                    showWrongDialog("wrong answer");


                }
            }
        }, 3000);


    }


    public void showRightDialog(String msg){

        final Dialog dialog = new Dialog(QuizShowActivity.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.right_ans_dialoug_layout);

        CardView dialogButton =  dialog.findViewById(R.id.card_next);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serial++;
                editor.putInt("position",serial);
                editor.apply();
                Intent intent=new Intent(getApplicationContext(),QuizShowActivity.class);
                intent.putExtra("text",Common.CATEGORY_SELECTED);
                startActivity(intent);
                finish();

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showWrongDialog(String msg){
        final Dialog dialog = new Dialog(QuizShowActivity.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.wrong_ans_dialog_layout);

        TextView text = (TextView) dialog.findViewById(R.id.text_carefulnotice);
        text.setText(msg);
        FloatingActionButton dialogButton =  dialog.findViewById(R.id.card_refresh);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(QuizShowActivity.this,QuizItemActivity.class);
        intent.putExtra(Common.CATEGORY_SELECTED,serial);
        intent.putExtra("chapter",Common.CATEGORY_SELECTED);
        Log.e("rabbi",String.valueOf(serial));
        Log.e("rabbi",Common.CATEGORY_SELECTED);
        startActivity(intent);
    }
}
