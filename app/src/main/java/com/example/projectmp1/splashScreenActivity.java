package com.example.projectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    private View decorView;


    //variables
    Animation logoAnimation, txtAnimation;
    ImageView logoImage;
    TextView namaApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //HIDE STATUS BAR AND NAVIGATION BAR
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBar());
        });

        //Animation
        logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        txtAnimation = AnimationUtils.loadAnimation(this, R.anim.txt_animation);

        //Hooks
        logoImage = findViewById(R.id.logo_app);
        namaApp = findViewById(R.id.nama_app);

        logoImage.setAnimation(logoAnimation);
        namaApp.setAnimation(txtAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBar());
        }
    }

    private int hideSystemBar(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

    }
}