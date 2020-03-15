package com.example.safeinternetwebview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.safeinternetwebview.R;
import com.example.safeinternetwebview.View.BackgroundSoundService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StartGameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "StartGameActivity";

    private MediaPlayer player;
    private FloatingActionButton fabInformation,fabMusicControl,fabPlayStart,download_certificate_button;
    boolean flag = true; // true if first icon is visible, false if second one is visible.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_game);
        Log.d(TAG, "onCreate: started.");

//        if (player==null){
//            player=MediaPlayer.create(this,R.raw.sound);
//            player.start();
//        }



        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fabInformation=findViewById(R.id.fab_information);
        fabMusicControl=findViewById(R.id.fab_music_control);
        fabPlayStart=findViewById(R.id.fab_start);
        download_certificate_button=findViewById(R.id.fab_download_certificate);

        fabPlayStart.setOnClickListener(this);
        fabMusicControl.setOnClickListener(this);
        fabInformation.setOnClickListener(this);
        download_certificate_button.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_information:
                Intent Instructionintent=new Intent(StartGameActivity.this,InstructionActivity.class);
                startActivity(Instructionintent);
                break;
            case R.id.fab_music_control:
                if(flag){

                    fabMusicControl.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.mute));
                    flag = false;
                    if (player!=null){
                        player=MediaPlayer.create(this,R.raw.sound);
                        player.stop();
                    }


                }else if(!flag){

                    fabMusicControl.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.speaker));
                    flag = true;
                    if (player==null){
                        player=MediaPlayer.create(this,R.raw.sound);
                        player.start();
                    }


//                    Intent intent = new Intent(StartGameActivity.this, BackgroundSoundService.class);
//                    startService(intent);

                }
                break;
            case R.id.fab_start:
                Intent intent=new Intent(StartGameActivity.this,SafeInternetIntroductionActivity.class);
                startActivity(intent);
                break;
        }
    }

}
