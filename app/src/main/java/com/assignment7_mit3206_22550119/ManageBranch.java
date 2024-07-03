package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageBranch extends AppCompatActivity {

    Button button;
    private DBManager dbManager;

    EditText editText1, editText2, editText3;
    Button addbranch;
    Button clearbranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_branch);

        button = findViewById(R.id.btnback5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminMenu();
            }
        });

        dbManager= new DBManager(this);

        dbManager.open();

        addbranch = findViewById(R.id.btnBranchAdd);
        clearbranch =  findViewById(R.id.btnBrachDelete);
        editText1 =  findViewById(R.id.txtBrachId);
        editText2 =  findViewById(R.id.txtBrachName);
        editText3 = findViewById(R.id.txtbranchadd);

        addbranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()||
                        editText3.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fields can't be null", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                if(valid) {

                    String BranchID = editText1.getText().toString();
                    String BranchName = editText2.getText().toString();
                    String BranchAddress = editText3.getText().toString();


                    dbManager.insert("insert into Branch values('" + BranchID + "','" + BranchName + "'," +
                            "'" + BranchAddress + "')");
                    Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                    Log.e("first","Inserted");
                    dbManager.close();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Error in Branch Adding", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clearbranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText1.getText().clear();
                editText2.getText().clear();
                editText3.getText().clear();

                Toast.makeText(getApplicationContext(),
                        "Your Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AdminMenu(){
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
    }
}
