package com.example.safeinternetwebview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.safeinternetwebview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InstructionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "InstructionActivity";

    private FloatingActionButton back_arrow,instruction_buuton,media_button;
    boolean flag = true; // true if first icon is visible, false if second one is visible.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        Log.d(TAG, "onCreate: started.");

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        instruction_buuton=findViewById(R.id.fab_information);
        media_button=findViewById(R.id.fab_music_control);

        instruction_buuton.setOnClickListener(this);
        media_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_information:
                //toggle music control
                break;
            case R.id.fab_music_control:
                //control music sound
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

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
