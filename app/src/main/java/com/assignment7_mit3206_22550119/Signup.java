package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    private Button bckButton;
    private DBManager dbManager;

    EditText eT1, eT2, eT3,eT4,eT5, eT6,eT7;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbManager= new DBManager(this);
        signUp = findViewById(R.id.btnSignUpS);
        eT1 = findViewById(R.id.txtFirstName);
        eT2 = findViewById(R.id.txtLastName);
        eT3 = findViewById(R.id.txtPhone);
        eT4 = findViewById(R.id.txtEmailAdd);
        eT5 = findViewById(R.id.txtaddress);
        eT6 = findViewById(R.id.txtUserName);
        eT7 = findViewById(R.id.txtpass);

        bckButton = findViewById(R.id.btnback);
        bckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                if (eT1.getText().toString().isEmpty() || eT2.getText().toString().isEmpty() || eT3.getText().toString().isEmpty()
                        || eT4.getText().toString().isEmpty()|| eT5.getText().toString().isEmpty() || eT6.getText().toString().isEmpty()
                        || eT7.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields can't be Null", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                if(valid) {
                    String FirstName = eT1.getText().toString();
                    String LastName = eT2.getText().toString();
                    String Phone = eT3.getText().toString();
                    String EmailAdd = eT4.getText().toString();
                    String Address = eT5.getText().toString();
                    String UserName = eT6.getText().toString();
                    String Password = eT7.getText().toString();


                    dbManager.open();

                    dbManager.insert("insert into Member values(?,'" + FirstName + "','" + LastName + "'," +
                            "'" + Phone + "','" + EmailAdd + "','" + Address + "','" + UserName + "','" + Password + "')");
                    Toast.makeText(getApplicationContext(), "Successfully Inserted All Data", Toast.LENGTH_SHORT).show();
                    Log.e("first","inserted");
                    dbManager.close();
                    Intent login = new Intent(getApplicationContext(), MemberMenu.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Error in Sign Up Process", Toast.LENGTH_SHORT).show();
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

    public  void MemberLogin(){
        Intent intent = new Intent(this, MemberLogin.class);
        startActivity(intent);
    }
}
