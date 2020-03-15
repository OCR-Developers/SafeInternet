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

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GameActivity";

    FloatingActionButton instruction_button,media_button;
    boolean flag = true; // true if first icon is visible, false if second one is visible.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "onCreate: started.");
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /// rft

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        instruction_button=findViewById(R.id.fab_information);
        media_button=findViewById(R.id.fab_music_control);


        instruction_button.setOnClickListener(this);
        media_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_information:
                Intent informationIntent=new Intent(GameActivity.this,InstructionActivity.class);
                startActivity(informationIntent);
                break;
            case R.id.fab_music_control:
                if(flag){

                    media_button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.mute));
                    flag = false;

                }else if(!flag){

                    media_button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.speaker));
                    flag = true;

                }
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            SharedPreferences sharedPreferences=getSharedPreferences("userData", Context.MODE_PRIVATE);
            if (sharedPreferences.contains("usernameKEY")&&sharedPreferences.contains("userschoolKEY")){
                startActivity(new Intent(GameActivity.this,UserProfileActivity.class));
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
