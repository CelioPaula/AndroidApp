package com.example.androidproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidproject.R;

public class LauncherActivity extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        btn = findViewById(R.id.btnMenu);
        animateScreen();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, MainMenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        }
        });
    }

    private void animateScreen(){
        LinearLayout l1 = findViewById(R.id.layoutUp);
        LinearLayout l2 = findViewById(R.id.layoutDown);
        final LinearLayout l3 = findViewById(R.id.layoutLogo);

        Animation uptodown_l1 = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        final Animation uptodown_l3 = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        Animation downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        l1.setAnimation(uptodown_l1);
        l2.setAnimation(downtoup);
        downtoup.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                l3.setVisibility(View.VISIBLE);
                l3.setAnimation(uptodown_l3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        uptodown_l3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) { blink(btn);}

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
    }

    private void blink(final Button btn){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 800;
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(btn.getVisibility() == View.VISIBLE){
                            btn.setVisibility(View.INVISIBLE);
                        }else{
                            btn.setVisibility(View.VISIBLE);
                        }
                        blink(btn);
                    }
                });
            }
        }).start();
    }
}
