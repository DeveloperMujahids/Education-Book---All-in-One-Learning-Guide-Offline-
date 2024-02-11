package com.mujahid.computereducation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    TextView txt,txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        txt = findViewById(R.id.txt);
        txt1 = findViewById(R.id.txt1);

        manageEffect();
        manageEffect1();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(Splash_Screen.this, MainActivity.class));
                    finish();
                } catch (Exception e) {
                }
            }

        }; thread.start();



    }



    private void manageEffect() {
        ObjectAnimator anim = ObjectAnimator.ofInt(txt, "textColor", Color.BLACK, Color.GREEN,
                Color.BLUE);
        anim.setDuration(1500);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }

    private void manageEffect1() {
        ObjectAnimator anim = ObjectAnimator.ofInt(txt1, "textColor", Color.BLACK, Color.GREEN,
                Color.BLUE);
        anim.setDuration(1500);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }

}