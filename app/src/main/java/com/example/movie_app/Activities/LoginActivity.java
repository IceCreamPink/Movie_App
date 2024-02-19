package com.example.movie_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movie_app.R;

public class LoginActivity extends AppCompatActivity {
    private EditText usertext, userpass;
    private Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
    }

    private void initview() {
        usertext=findViewById(R.id.useredit);
        userpass=findViewById(R.id.userpass);
        loginbtn=findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(view -> {
            if (usertext.getText().toString().isEmpty() ||userpass.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, "Tolong isi username and password😊", Toast.LENGTH_SHORT).show();
            }else if(usertext.getText().toString().equals("admin") &&  userpass.getText().toString().equals("admin")){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }else {
                Toast.makeText(LoginActivity.this, "Username atau Password anda salah🙏", Toast.LENGTH_SHORT).show();
            }
        });
    }
}