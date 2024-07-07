package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MemberLogin extends AppCompatActivity {

    private Button bckButton;
    EditText UserName, Password;
    Button login;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        bckButton = findViewById(R.id.btnback2);
        bckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });
        dbManager = new DBManager(this);
        dbManager.open();

        UserName = findViewById(R.id.txtUserName2);
        Password = findViewById(R.id.txtpass2);
        login = findViewById(R.id.login2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserName.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fields can't be Null", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean valid;

                    if(valid = true)
                    {
                        Cursor cursor = dbManager.Select("select * from Member where UserName='" + UserName.getText() +
                                "' AND Password='" + Password.getText() + "'");

                        if (cursor.moveToNext()) {

                            Toast.makeText(MemberLogin.this, "Successfully Logged", Toast.LENGTH_SHORT).show();
                            Intent Member = new Intent(getApplicationContext(), MemberMenu.class);
                            startActivity(Member);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                        }

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
}
