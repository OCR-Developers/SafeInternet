package com.example.safeinternetwebview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.safeinternetwebview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UserProfileActivity";

    private TextView userProfileName;
    private FloatingActionButton delete_button,instruction_button,media_button;
    private CardView useraccount;

    boolean flag = true; // true if first icon is visible, false if second one is visible.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Log.d(TAG, "onCreate: started.");
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        userProfileName=findViewById(R.id.profileName);
        delete_button=findViewById(R.id.fab_delete_control);
        instruction_button=findViewById(R.id.fab_information);
        media_button=findViewById(R.id.fab_music_control);
        useraccount=findViewById(R.id.nameCardView);

        SharedPreferences sharedPreferences=getSharedPreferences("userData", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("usernameKEY")){
            String name=sharedPreferences.getString("usernameKEY",null);
            userProfileName.setText(name);
        }


        delete_button.setOnClickListener(this);
        instruction_button.setOnClickListener(this);
        media_button.setOnClickListener(this);
        useraccount.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_delete_control:
                SharedPreferences sharedPreferences=getSharedPreferences("userData",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                if (sharedPreferences.contains("usernameKEY")&&sharedPreferences.contains("userschoolKEY")&&sharedPreferences.contains("userageKEY")&&sharedPreferences.contains("userdistrictKEY")){
                    editor.clear();//remove all data
                    editor.commit(); // commit changes
                    Intent intent=new Intent(UserProfileActivity.this,RegistrationActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.fab_information:
                Intent informationIntent=new Intent(UserProfileActivity.this,InstructionActivity.class);
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
            case R.id.nameCardView:
                Intent gameIntent=new Intent(UserProfileActivity.this,GameActivity.class);
                startActivity(gameIntent);
                finish();
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
