package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private static int SPlASH_DURATION=3000;
Animation topanim,bottomanim;
ImageView fightCorona;
TextView title,titleSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        fightCorona=findViewById(R.id.imageView);
        title=findViewById(R.id.textView);
        titleSlogan=findViewById(R.id.textView1);
        fightCorona.setAnimation(topanim);
        title.setAnimation(bottomanim);
        titleSlogan.setAnimation(bottomanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPlASH_DURATION);
    }
}
