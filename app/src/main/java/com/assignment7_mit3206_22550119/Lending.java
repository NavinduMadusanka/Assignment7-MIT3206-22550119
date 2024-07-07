package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Lending extends AppCompatActivity {

    Button button;
    private DBManager dbManager;

    EditText editText1, editText2, editText3, editText4, editText5;
    Button addLendingBook;
    Button clearLendingBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lending);

        button = findViewById(R.id.btnback6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminMenu();
            }
        });

        dbManager = new DBManager(this);

        dbManager.open();

        addLendingBook = findViewById(R.id.btnAddLend);
        clearLendingBook = findViewById(R.id.btnDeleteLend);
        editText1 = findViewById(R.id.txtBranchIDBook);
        editText2 = findViewById(R.id.txtCardNoBook);
        editText3 = findViewById(R.id.txtDateout);
        editText4 = findViewById(R.id.txtDaeDue);
        editText5 = findViewById(R.id.txtDateReturn);

        addLendingBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty() ||
                        editText3.getText().toString().isEmpty() || editText4.getText().toString().isEmpty() ||
                        editText5.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fields can't be Null", Toast.LENGTH_SHORT).show();
                    valid = false;
                }
                if (valid) {

                    String BranchID = editText1.getText().toString();
                    String CardNo = editText2.getText().toString();
                    String DateOut = editText3.getText().toString();
                    String DateDue = editText4.getText().toString();
                    String DateReturned = editText5.getText().toString();

                    dbManager.insert("insert into BookLoan values(?,'" + BranchID + "','" + CardNo + "'," +
                            "'" + DateOut + "','" + DateDue + "','" + DateReturned + "')");
                    Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                    Log.e("first", "Inserted");
                    dbManager.close();
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Branch Adding", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void AdminMenu() {
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
    }

    public void MainActivity() {
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

    public void SearchBooks() {
        Intent intent = new Intent(this, SearchBooks.class);
        startActivity(intent);
    }

    public void ManageBooks() {
        Intent intent = new Intent(this, ManageBooks.class);
        startActivity(intent);
    }

    public void ManagePublisher() {
        Intent intent = new Intent(this, ManagePublisher.class);
        startActivity(intent);
    }

    public void ManageBranch() {
        Intent intent = new Intent(this, ManageBranch.class);
        startActivity(intent);
    }

    public void MemberMenu(){
        Intent intent = new Intent(this, MemberMenu.class);
        startActivity(intent);
    }
}
