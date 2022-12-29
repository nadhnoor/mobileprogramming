package com.example.projectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class dashboard_Page extends AppCompatActivity {

    TextView nama_user;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_page);

        //GETTER-SETTER THE STRING DATA FROM DATABASE USER TO INPUT THE NAME USER
        nama_user = findViewById(R.id.name);
        String name = getIntent().getStringExtra("name");
        nama_user.setText(name);

        //HIDE STATUS BAR AND ACTION BAR
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBar());
        });



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