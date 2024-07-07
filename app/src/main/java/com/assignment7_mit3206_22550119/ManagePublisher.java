package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagePublisher extends AppCompatActivity {

    Button button;
    private DBManager dbManager;

    EditText editText1, editText2, editText3;
    Button addpublish;
    Button clearpub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_publisher);

        button = findViewById(R.id.btnback4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminMenu();
            }
        });

        dbManager= new DBManager(this);

        dbManager.open();

        addpublish = findViewById(R.id.btnaddpub);
        clearpub =  findViewById(R.id.btndeletepub);
        editText1 =  findViewById(R.id.txtPubName);
        editText2 =  findViewById(R.id.txtpublisheradd);
        editText3 = findViewById(R.id.txtpubcontact);

        addpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()||
                        editText3.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fields can't be Null", Toast.LENGTH_SHORT).show();
                    valid = false;
                }
                if(valid) {

                    String Name = editText1.getText().toString();
                    String Address = editText2.getText().toString();
                    String Phone = editText3.getText().toString();


                    dbManager.insert("insert into Publisher values('" + Name + "','" + Address + "'," +
                            "'" + Phone + "')");
                    Toast.makeText(ManagePublisher.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                    Log.e("first","Inserted");
                    dbManager.close();
                }

                else
                {
                    Toast.makeText(ManagePublisher.this, "Error in Publisher Adding", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clearpub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText1.getText().clear();
                editText2.getText().clear();
                editText3.getText().clear();

                Toast.makeText(getApplicationContext(),
                        "Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AdminMenu(){
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
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

    public void Lending(){
        Intent intent = new Intent(this, Lending.class);
        startActivity(intent);
    }

    public void MemberMenu(){
        Intent intent = new Intent(this, MemberMenu.class);
        startActivity(intent);
    }
}
