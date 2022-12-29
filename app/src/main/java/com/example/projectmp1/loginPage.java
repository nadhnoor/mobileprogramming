package com.example.projectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.BreakIterator;

public class loginPage extends AppCompatActivity {

    EditText userId, password;
    Button login;
    Button signup;
    private View decorView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.notHaveAcc_text);

        //HIDE STATUS BAR AND ACTION BAR
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBar());
        });

        //BUTTON LOGIN --> EXECUTE VALIDATION DATA FROM INPUT CREDENTIALS DATA --> FROM REGISTER PAGE
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameId = userId.getText().toString();
                String passwordUser = password.getText().toString();
                //Validation Data
                if(usernameId.isEmpty() || passwordUser.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
                }else{
                    //Perform Query
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDAO userDAO = userDatabase.userDAO();
                    new Thread((new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDAO.login(usernameId, passwordUser);
                            if(userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                String name = userEntity.name;
                                startActivity(new Intent(loginPage.this, dashboard_Page.class).putExtra("name", name));
                            }

                        }
                    })).start();
                }

            }
        });

        //NOT HAVE ALREADY ACCOUNT
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginPage.this, registrasiPage.class));
            }
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