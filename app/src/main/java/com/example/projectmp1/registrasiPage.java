package com.example.projectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registrasiPage extends AppCompatActivity {
    Button signin;
    EditText userId, password, name;
    Button register;
    private View decorView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_page);
        name = findViewById(R.id.name);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        register = findViewById(R.id.btn_register);

        //HIDE STATUS BAR AND ACTION BAR
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBar());
        });

        //BUTTON SIGIN
        signin = findViewById(R.id.btn_signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alreadyHaveacc = new Intent(getApplicationContext(),loginPage.class);
                startActivity(alreadyHaveacc);
            }
        });


        //BUTTON REGISTER --> EXECUTE VALIDATE INSERT DATA
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating User Entity
                UserEntity userEntity = new UserEntity();
                userEntity.setName(name.getText().toString());
                userEntity.setUserId(userId.getText().toString());
                userEntity.setPassword(password.getText().toString());
                if (validateInput(userEntity)){
                    //Do insert operation
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDAO userDAO = userDatabase.userDAO();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Register User
                            userDAO.RegisterUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"User Registered!", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).start();

                }else{
                    Toast.makeText(getApplicationContext(),"Fill all fields!",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    //CREATE INPUT VALIDATION
    private Boolean validateInput(UserEntity userEntity){
        if(userEntity.getName().isEmpty() |
           userEntity.getPassword().isEmpty() |
           userEntity.getUserId().isEmpty()){
           return false;
        }
        return true;
    }


    //HIDE STATUS BAR AND ACTION BAR
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