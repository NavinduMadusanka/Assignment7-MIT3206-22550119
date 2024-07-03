package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });

        button = findViewById(R.id.btnadminlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminLogin();
            }
        });

        button = findViewById(R.id.btnmemlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberLogin();
            }
        });
    }

    public void Signup(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public  void MemberLogin(){
        Intent intent = new Intent(this, MemberLogin.class);
        startActivity(intent);
    }

    public  void AdminLogin(){
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }
}