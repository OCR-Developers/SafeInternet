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

import com.example.safeinternetwebview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SafeInternetIntroductionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SafeInternetIntroductio";

    private FloatingActionButton start_button,back_arrow_button,info_button,music_button,download_certificate_button;
    boolean flag = true; // true if first icon is visible, false if second one is visible.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_internet_introduction);
        Log.d(TAG, "onCreate: started.");

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);



        info_button=findViewById(R.id.fab_information);
        music_button=findViewById(R.id.fab_music_control);
        download_certificate_button=findViewById(R.id.fab_download_certificate);
        start_button=findViewById(R.id.fab_start);

        info_button.setOnClickListener(this);
        music_button.setOnClickListener(this);
        download_certificate_button.setOnClickListener(this);
        start_button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_start:

                //to read data
                SharedPreferences sharedPreferences=getSharedPreferences("userData", Context.MODE_PRIVATE);
                if ((sharedPreferences.contains("usernameKEY") && sharedPreferences.contains("userschoolKEY"))){
                    String name=sharedPreferences.getString("usernameKEY","Data did not found");
                    String school=sharedPreferences.getString("userschoolKEY","Data did not found");
                    Intent UserProfileIntent=new Intent(SafeInternetIntroductionActivity.this,UserProfileActivity.class);
                    startActivity(UserProfileIntent);
                }else {
                    Intent RegistrationIntent=new Intent(SafeInternetIntroductionActivity.this,RegistrationActivity.class);
                    startActivity(RegistrationIntent);
                }
                break;
            case R.id.fab_music_control:
                if(flag){

                    music_button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.mute));
                    flag = false;

                }else if(!flag){

                    music_button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.speaker));
                    flag = true;

                }
                break;
            case R.id.fab_download_certificate:
                break;
            case R.id.fab_information:
                Intent informationIntent=new Intent(SafeInternetIntroductionActivity.this,InstructionActivity.class);
                startActivity(informationIntent);
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
