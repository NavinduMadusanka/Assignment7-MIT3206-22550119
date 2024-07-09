package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMenu extends AppCompatActivity {

    private Button logoutBtn, addingBtn, managePubBtn, branchBtn, lendingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        logoutBtn = findViewById(R.id.btnlogout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });

        addingBtn = findViewById(R.id.btnaddingB);
        addingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageBooks();
            }
        });

        managePubBtn = findViewById(R.id.btnmanagepub);
        managePubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagePublisher();
            }
        });

        branchBtn = findViewById(R.id.btnbranch);
        branchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageBranch();
            }
        });

        lendingBtn = findViewById(R.id.btnlending);
        lendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lending();
            }
        });
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

    public void Lending() {
        Intent intent = new Intent(this, Lending.class);
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

    public void MemberMenu() {
        Intent intent = new Intent(this, MemberMenu.class);
        startActivity(intent);
    }
}
