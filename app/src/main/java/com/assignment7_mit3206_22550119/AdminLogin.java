package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    private Button button;
    EditText UserName, Password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        button = findViewById(R.id.btnback1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });

        UserName = findViewById(R.id.txtUserName1);
        Password = findViewById(R.id.txtpass1);
        login = findViewById(R.id.login1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserName.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fields can't be Null", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (UserName.getText().toString().equals("user") && Password.getText().toString().equals("user123")) {
                        Toast.makeText(getApplicationContext(), "Successfully Logged as Admin", Toast.LENGTH_SHORT).show();
                        Intent libAdmin = new Intent(getApplicationContext(), AdminMenu.class);
                        startActivity(libAdmin);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void DatabaseHelper() {
        Intent intent = new Intent(this, DatabaseHelper.class);
        startActivity(intent);
    }

    public void DBManager() {
        Intent intent = new Intent(this, DBManager.class);
        startActivity(intent);
    }

    public void MemberMenu(){
        Intent intent = new Intent(this, MemberMenu.class);
        startActivity(intent);
    }

    public void Lending(){
        Intent intent = new Intent(this, Lending.class);
        startActivity(intent);
    }
}
