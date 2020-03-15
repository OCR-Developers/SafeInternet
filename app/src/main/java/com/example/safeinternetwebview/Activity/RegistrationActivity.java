package com.example.safeinternetwebview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.safeinternetwebview.View.ViewDialog;
import com.example.safeinternetwebview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegistrationActivity";

    private EditText nameEditText,schoolEditText;
    private Spinner ageSpinner,districtSpinner;
    private FloatingActionButton submitButton,mediaButton,instructionButton;

    boolean flag = true; // true if first icon is visible, false if second one is visible.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Log.d(TAG, "onCreate: started.");
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);



        InitializeWidget();


        submitButton.setOnClickListener(this);
        mediaButton.setOnClickListener(this);
        instructionButton.setOnClickListener(this);

    }


    private void InitializeWidget() {
        nameEditText=findViewById(R.id.nameEdittext);
        schoolEditText=findViewById(R.id.schoolEditText);
        ageSpinner=findViewById(R.id.ageSpinner);
        districtSpinner=findViewById(R.id.districtSpinner);
        submitButton=findViewById(R.id.fab_start);
        mediaButton=findViewById(R.id.fab_music_control);
        instructionButton=findViewById(R.id.fab_information);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_start:
                String name=nameEditText.getText().toString().trim();
                String school=schoolEditText.getText().toString().trim();
                String age=ageSpinner.getSelectedItem().toString();
                String district=districtSpinner.getSelectedItem().toString();
                if (name.equals("") || school.equals("")){
                    ViewDialog alert=new ViewDialog();
                    alert.showDialog(RegistrationActivity.this,"Error Invalid user");
                }else {
                    //writing data
                    SharedPreferences preferences=getSharedPreferences("userData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("usernameKEY",name);
                    editor.putString("userschoolKEY",school);
                    editor.putString("userageKEY",age);
                    editor.putString("userdistrictKEY",district);
                    editor.apply();


                    Intent startGameIntent=new Intent(RegistrationActivity.this,GameActivity.class);
                    startActivity(startGameIntent);
                    Log.d(TAG, "onClick: "+name+school+age+district);
                    finish();
                }
                break;
            case R.id.fab_music_control:
                if(flag){

                    mediaButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.mute));
                    flag = false;

                }else if(!flag){

                    mediaButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.speaker));
                    flag = true;

                }

                break;
            case R.id.fab_information:
                Intent instructionIntent=new Intent(RegistrationActivity.this,InstructionActivity.class);
                startActivity(instructionIntent);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
